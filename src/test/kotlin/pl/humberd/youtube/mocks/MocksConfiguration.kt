package pl.humberd.youtube.mocks

import com.google.gson.Gson
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import pl.humberd.youtube.retrofit.api.YoutubeApi


@TestConfiguration
class MocksConfiguration {

    @Bean
    fun mockYoutubeApi(gson: Gson): YoutubeApi {
        return YoutubeApiMock(gson)
    }
}