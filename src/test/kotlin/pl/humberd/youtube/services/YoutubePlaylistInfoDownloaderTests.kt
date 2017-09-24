package pl.humberd.youtube.services

import org.junit.jupiter.api.Assertions.*
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

@ExtendWith(SpringExtension::class)
//@JsonTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = arrayOf(
        MocksConfiguration::class,
        YoutubePlaylistInfoDownloader::class,
        GsonConfiguration::class
))
@TestPropertySource(locations = arrayOf("classpath:application.properties"))
class YoutubePlaylistInfoDownloaderTests {
    @Autowired
    lateinit var youtubePlaylistInfoDownloader: YoutubePlaylistInfoDownloader


    @Nested
    inner class getAllPlaylistItems {

        @Test
        @DisplayName("Should get videoIds from all pages")
        fun getAllPages() {
            val allPlaylistVideosIds = youtubePlaylistInfoDownloader.getAllPlaylistItems("", "")

            assertEquals(allPlaylistVideosIds.size, 5)
            allPlaylistVideosIds.forEach {
                assert(it.isNotEmpty())
            }
        }
    }

    @Nested
    inner class getPageOfPlaylistItems {

        @Test
        @DisplayName("Should get a first page with middlePageToken")
        fun getFirstPage() {
            val (nextPageToken, videoIds) = youtubePlaylistInfoDownloader.getPageOfPlaylistItems("", "", "")

            assertEquals(nextPageToken, "middlePageToken")
            assertEquals(videoIds.size, 2)
        }

        @Test
        @DisplayName("Should get a middle page with a lastPageToken")
        fun getMiddlePage() {
            val (nextPageToken, videoIds) = youtubePlaylistInfoDownloader.getPageOfPlaylistItems("", "", "middlePageToken")

            assertEquals(nextPageToken, "lastPageToken")
            assertEquals(videoIds.size, 2)
        }

        @Test
        @DisplayName("Should get a last page with no nextPageToken")
        fun getlastPage() {
            val (nextPageToken, videoIds) = youtubePlaylistInfoDownloader.getPageOfPlaylistItems("", "", "lastPageToken")

            assertEquals(nextPageToken, "")
            assertEquals(videoIds.size, 1)
        }
    }
}