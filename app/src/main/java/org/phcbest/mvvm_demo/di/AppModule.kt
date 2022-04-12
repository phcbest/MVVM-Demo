package org.phcbest.mvvm_demo.di

import androidx.lifecycle.ViewModelProvider
import dagger.Provides
import org.phcbest.mvvm_demo.service.respository.GitHubService
import org.phcbest.mvvm_demo.viewmodel.ProjectViewModelFactory
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

    /**
     * 要求返回一个viewmodel提供者的工厂
     */
    @Singleton
    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        TODO()
//        return ProjectViewModelFactory()
    }

}