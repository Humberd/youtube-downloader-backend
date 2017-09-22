package pl.humberd.youtube.retrofit.api

import io.reactivex.Observable
import pl.humberd.youtube.models.PlayListItems
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("playlistItems")
    fun getPlaylistItems(@Query("part") part: String = "snippet",
                         @Query("maxResults") maxResults: Int = 50,
                         @Query("playlistId") playlistId: String,
                         @Query("key") apiKey: String,
                         @Query("pageToken") pageToken: String = ""): Observable<PlayListItems>
}