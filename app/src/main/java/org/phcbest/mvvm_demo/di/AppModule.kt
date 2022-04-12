package org.phcbest.mvvm_demo.di

import dagger.Provides
import org.phcbest.mvvm_demo.service.respository.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class AppModule {

    /**
     * 单例并且设置为依赖提供者
     */
    @Singleton
    @Provides
    fun provideGithubService(): GitHubService {
        return Retrofit.Builder().baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(GitHubService::class.java)

    }

}