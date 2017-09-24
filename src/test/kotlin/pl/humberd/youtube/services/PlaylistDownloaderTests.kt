package pl.humberd.youtube.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
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
@TestPropertySource(locations = arrayOf("classpath:application.properties"))
class PlaylistDownloaderTests {
    @Autowired
    lateinit var playlistDownloader: PlaylistDownloader


    @Nested
    inner class getAllPlaylistItems {

        @Test
        @DisplayName("Should get videoIds from all pages")
        fun getAllPages() {
            val allPlaylistVideosIds = playlistDownloader.getAllPlaylistItems("", "")

            assertEquals(allPlaylistVideosIds.size, 5)
        }
    }

    @Nested
    inner class getPageOfPlaylistItems {

        @Test
        @DisplayName("Should get a first page with middlePageToken")
        fun getFirstPage() {
            val (nextPageToken, videoIds) = playlistDownloader.getPageOfPlaylistItems("", "", "")

            assertEquals(nextPageToken, "middlePageToken")
            assertEquals(videoIds.size, 2)
        }

        @Test
        @DisplayName("Should get a middle page with a lastPageToken")
        fun getMiddlePage() {
            val (nextPageToken, videoIds) = playlistDownloader.getPageOfPlaylistItems("", "", "middlePageToken")

            assertEquals(nextPageToken, "lastPageToken")
            assertEquals(videoIds.size, 2)
        }

        @Test
        @DisplayName("Should get a last page with no nextPageToken")
        fun getlastPage() {
            val (nextPageToken, videoIds) = playlistDownloader.getPageOfPlaylistItems("", "", "lastPageToken")

            assertEquals(nextPageToken, "")
            assertEquals(videoIds.size, 1)
        }
    }
}