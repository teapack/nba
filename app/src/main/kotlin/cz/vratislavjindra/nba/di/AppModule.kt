package cz.vratislavjindra.nba.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cz.vratislavjindra.nba.common.network.data.api.PlayerApi
import cz.vratislavjindra.nba.common.network.data.api.TeamApi
import cz.vratislavjindra.nba.common.player.data.PlayerRemoteDataSource
import cz.vratislavjindra.nba.common.player.data.PlayerRepositoryImpl
import cz.vratislavjindra.nba.common.player.domain.PlayerRepository
import cz.vratislavjindra.nba.common.team.data.TeamLogoRepositoryImpl
import cz.vratislavjindra.nba.common.team.data.TeamRemoteDataSource
import cz.vratislavjindra.nba.common.team.data.TeamRepositoryImpl
import cz.vratislavjindra.nba.common.team.domain.TeamLogoRepository
import cz.vratislavjindra.nba.common.team.domain.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providePlayerRepository(
        remoteDataSource: PlayerRemoteDataSource,
    ): PlayerRepository {
        return PlayerRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(
        remoteDataSource: TeamRemoteDataSource,
    ): TeamRepository {
        return TeamRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTeamLogoRepository(): TeamLogoRepository {
        return TeamLogoRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providePlayerApi(retrofit: Retrofit): PlayerApi {
        return retrofit.create(PlayerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTeamApi(retrofit: Retrofit): TeamApi {
        return retrofit.create(TeamApi::class.java)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("https://www.balldontlie.io/api/")
            .client(okHttpClient)
            .build()
    }
}
