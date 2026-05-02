// ManhwaReader.kt - Aniyomi Extension for Manhwa Reader
// Connects to local Manhwa Reader PHP backend

package eu.kanade.tachiyomi.animeextension.en.manhwareader

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.AnimesPage
import eu.kanade.tachiyomi.animesource.model.SAnime
import eu.kanade.tachiyomi.animesource.model.SEpisode
import eu.kanade.tachiyomi.animesource.model.Video
import eu.kanade.tachiyomi.animeextension.ParsedAnimeHttpSource
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject

class ManhwaReader : ParsedAnimeHttpSource() {

    override val name = "Manhwa Reader"
    override val baseUrl = "http://192.168.1.209/manhwa-api/api"
    override val lang = "en"
    override val id: Long = 123456789 // Unique ID for this source

    // =========================== Popular Anime ============================
    override fun popularAnimeRequest(page: Int): okhttp3.Request {
        return GET("$baseUrl/manhwa.php")
    }

    override fun popularAnimeParse(response: Response): AnimesPage {
        val json = response.body!!.string()
        val jsonObject = JSONObject(json)
        val animes = mutableListOf<SAnime>()

        if (jsonObject.optBoolean("success", false)) {
            val dataArray = jsonObject.getJSONArray("data")
            for (i in 0 until dataArray.length()) {
                val item = dataArray.getJSONObject(i)
                animes.add(SAnime.create().apply {
                    url = item.getInt("id").toString()
                    title = item.getString("title")
                    thumbnail_url = item.optString("cover_image", "")
                })
            }
        }

        return AnimesPage(animes, false)
    }

    // =========================== Search ============================
    override fun searchAnimeRequest(page: Int, query: String, filters: AnimeFilterList): okhttp3.Request {
        return GET("$baseUrl/manhwa.php?search=$query")
    }

    override fun searchAnimeParse(response: Response): AnimesPage {
        return popularAnimeParse(response) // Same parsing logic
    }

    // =========================== Anime Details ============================
    override fun animeDetailsRequest(anime: SAnime): okhttp3.Request {
        return GET("$baseUrl/manhwa.php?id=${anime.url}")
    }

    override fun animeDetailsParse(response: Response): SAnime {
        val json = response.body!!.string()
        val jsonObject = JSONObject(json)

        if (jsonObject.optBoolean("success", false)) {
            val dataArray = jsonObject.getJSONArray("data")
            if (dataArray.length() > 0) {
                val item = dataArray.getJSONObject(0)
                anime.apply {
                    title = item.getString("title")
                    thumbnail_url = item.optString("cover_image", "")
                    description = item.optString("description", "")
                    status = parseStatus(item.optString("status", ""))
                }
                return anime
            }
        }

        return anime
    }

    // =========================== Episode List ============================
    override fun episodeListRequest(anime: SAnime): okhttp3.Request {
        return GET("$baseUrl/chapters.php?manhwa_id=${anime.url}")
    }

    override fun episodeListParse(response: Response): List<SEpisode> {
        val json = response.body!!.string()
        val jsonArray = JSONArray(json)
        val episodes = mutableListOf<SEpisode>()

        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            episodes.add(SEpisode.create().apply {
                url = item.getInt("id").toString()
                name = "Chapter ${item.getInt("chapter_number")}"
                episode_number = item.getInt("chapter_number").toFloat()
            })
        }

        return episodes
    }

    // =========================== Video List ============================
    override fun videoListRequest(episode: SEpisode): okhttp3.Request {
        return GET("$baseUrl/reader.php?chapter_id=${episode.url}")
    }

    override fun videoListParse(response: Response): List<Video> {
        val json = response.body!!.string()
        val jsonArray = JSONArray(json)
        val videos = mutableListOf<Video>()

        for (i in 0 until jsonArray.length()) {
            val imageUrl = jsonArray.getString(i)
            videos.add(Video(url = imageUrl, videoUrl = imageUrl))
        }

        return videos
    }

    // Helper function to parse status
    private fun parseStatus(statusStr: String): Int {
        return when (statusStr.lowercase()) {
            "ongoing" -> SAnime.ONGOING
            "completed" -> SAnime.COMPLETED
            "cancelled" -> SAnime.CANCELLED
            "hiatus" -> SAnime.ON_HIATUS
            else -> SAnime.UNKNOWN
        }
    }
}
