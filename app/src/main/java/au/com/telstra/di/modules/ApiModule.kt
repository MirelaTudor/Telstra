package au.com.telstra.di.modules

import au.com.telstra.BuildConfig
import au.com.telstra.data.remote.FactService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Module that provides all dependencies from the networking part.
 */
@Module
class ApiModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()))
            .client(httpClient.build())
            .build()
    }

    @Provides
    fun provideAuthService(retrofit: Retrofit): FactService =
        retrofit.create(FactService::class.java)
}
