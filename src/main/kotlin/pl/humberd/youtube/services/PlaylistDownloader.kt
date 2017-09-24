package pl.humberd.youtube.services

import mu.KLogging
import org.springframework.stereotype.Service
import pl.humberd.youtube.models.PlaylistItemsWrapper
import pl.humberd.youtube.retrofit.api.YoutubeApi

@Service
class PlaylistDownloader(val playlistService: YoutubeApi) {
    companion object: KLogging()

    fun getPageOfPlaylistItems(playlistId: String,
                               apiKey: String,
                               pageToken: String): PlaylistItemsWrapper {

        val playlistItems = playlistService.getPlaylistItems(
                playlistId = playlistId,
                apiKey = apiKey,
                pageToken = pageToken)
                .blockingFirst()

        val videoIds = ArrayList<String>(playlistItems.items.size)

        playlistItems.items.forEach { playListItem ->
            videoIds.add(playListItem.snippet.resourceId.videoId)
        }

        return PlaylistItemsWrapper(playlistItems.nextPageToken ?: "", videoIds)
    }

    fun getAllPlaylistItems(playlistId: String,
                            apiKey: String): List<String> {

        var nextPageToken = "";
        val videoIds = arrayListOf<String>()

        do {
            val (currentNextPageToken, currentVideoIds) = getPageOfPlaylistItems(playlistId, apiKey, nextPageToken)
            videoIds.addAll(currentVideoIds)
            nextPageToken = currentNextPageToken
        } while (nextPageToken.isNotEmpty())
        return videoIds;
    }


}
