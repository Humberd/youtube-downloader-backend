package pl.humberd.youtube.mocks

import com.google.gson.Gson
import io.reactivex.Observable
import pl.humberd.youtube.models.PlayListItems
import pl.humberd.youtube.retrofit.api.YoutubeApi
import retrofit2.Call
import java.io.FileReader

open class YoutubeApiMock(var gson: Gson) : YoutubeApi {
    override fun getPlaylistItems(part: String,
                                  maxResults: Int,
                                  playlistId: String,
                                  apiKey: String,
                                  pageToken: String): Observable<PlayListItems> {

        val fileName = when {
            pageToken.equals("") -> "src/test/resources/mocks/PlaylistItems.firstPage.json"
            pageToken.equals("middlePageToken") -> "src/test/resources//mocks/PlaylistItems.middlePage.json"
            pageToken.equals("lastPageToken") -> "src/test/resources//mocks/PlaylistItems.lastPage.json"
            else -> throw IllegalArgumentException("Invalid pageToken")
        }

        return Observable.just(gson.fromJson(FileReader(fileName), PlayListItems::class.java))
    }

}