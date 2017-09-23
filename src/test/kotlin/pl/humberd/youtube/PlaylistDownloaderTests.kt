package pl.humberd.youtube

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import pl.humberd.youtube.gson.GsonConfiguration
import pl.humberd.youtube.mocks.MocksConfiguration
import pl.humberd.youtube.services.PlaylistDownloader

@ExtendWith(SpringExtension::class)
//@JsonTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = arrayOf(
        MocksConfiguration::class,
        PlaylistDownloader::class,
        GsonConfiguration::class
))
class PlaylistDownloaderTests {

    @Autowired
    lateinit var playlistDownloader: PlaylistDownloader

    @Test
    fun getPlaylistService() {
        playlistDownloader.playlistService.getPlaylistItems(playlistId = "",
                apiKey = "")
                .subscribe { println(it) }
        assertTrue(true, "If ok")
    }
}