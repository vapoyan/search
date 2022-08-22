package app.coinfo.feature.search.di

import android.content.Context
import app.coinfo.feature.search.common.Constants.COINGECKO_URL
import app.coinfo.feature.search.data.local.SearchPreferences
import app.coinfo.feature.search.data.local.SearchPreferencesImpl
import app.coinfo.feature.search.data.remote.CoingeckoApi
import app.coinfo.feature.search.data.repository.SearchRepositoryImpl
import app.coinfo.feature.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SearchModule {

    @Provides
    @Singleton
    fun providesCoingeckoApi(): CoingeckoApi = Retrofit.Builder()
        .baseUrl(COINGECKO_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoingeckoApi::class.java)

    @Provides
    @Singleton
    fun providesSearchRepository(
        api: CoingeckoApi
    ): SearchRepository = SearchRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesSearchPreferences(
        @ApplicationContext appContext: Context
    ): SearchPreferences = SearchPreferencesImpl(appContext)
}