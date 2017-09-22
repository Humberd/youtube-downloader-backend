package pl.humberd.youtube

import io.reactivex.Observable
import mu.KotlinLogging
import org.springframework.stereotype.Service
import pl.humberd.youtube.retrofit.api.YoutubeApi
import javax.annotation.PostConstruct

data class PlaylistItemsWrapper(
        val nextPageToken: String,
        val videoIds: List<String>
)

@Service
class PlaylistRetriever(val playlistService: YoutubeApi) {

    private var logger = KotlinLogging.logger {  };
    @PostConstruct
    fun foo() {
        logger.warn { "dupa" }
        println("DUPA")
        getAllPlaylistItems(playlistId = "PLvFEJbMqWahVwOTF0cqnpemb_xZmn5Nmw",
                apiKey = "AIzaSyDdUNZ4UXfB_YTxzT-AsuGTGa4GfuFMHeg")
                .blockingSubscribe {
                    println(it)
                    println(it.size)
                }
    }

    fun getAllPlaylistItems(playlistId: String,
                            apiKey: String): Observable<ArrayList<String>> {
        return Retriever(playlistService).getAllPlaylistItems(playlistId, apiKey)
    }

    class Retriever(val playlistService: YoutubeApi) {
        private var nextPageToken = ""
        private val playlistVideoIds = ArrayList<String>()

        fun getPageOfPlaylistItems(playlistId: String,
                                   apiKey: String,
                                   pageToken: String): Observable<PlaylistItemsWrapper> {
            return playlistService.getPlaylistItems(
                    playlistId = playlistId,
                    apiKey = apiKey,
                    pageToken = pageToken)
                    .map { resonse ->
                        val videoIds = ArrayList<String>(resonse.items.size)
                        resonse.items.forEach { playListItem ->
                            videoIds.add(playListItem.snippet.resourceId.videoId)
                        }

//                    println("$pageToken and ${resonse.nextPageToken}")

                        PlaylistItemsWrapper(resonse.nextPageToken ?: "", videoIds)
                    }
        }

        fun getAllPlaylistItems(playlistId: String,
                                apiKey: String): Observable<ArrayList<String>> {
            return Observable.defer { getPageOfPlaylistItems(playlistId, apiKey, nextPageToken) }
                    .doOnEach { nextPageToken = it.value?.nextPageToken ?: "" }
                    .doOnEach { it.value?.videoIds?.let { it1 -> playlistVideoIds.addAll(it1) } }
                    .repeatUntil {
                        println(nextPageToken)
                        nextPageToken.isEmpty()
                    }
                    .map { playlistVideoIds }
        }
    }

}
