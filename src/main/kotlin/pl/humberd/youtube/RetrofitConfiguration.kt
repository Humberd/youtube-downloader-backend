package pl.humberd.youtube

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class RetrofitConfiguration {
    @Bean
    fun createYoutubeRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Bean
    fun createRetrofitService(retrofit: Retrofit): YoutubePlaylistItemsInterface {
        return retrofit.create(YoutubePlaylistItemsInterface::class.java)
    }
}