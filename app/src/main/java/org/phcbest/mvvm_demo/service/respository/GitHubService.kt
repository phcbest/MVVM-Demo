package org.phcbest.mvvm_demo.service.respository

import org.phcbest.mvvm_demo.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    companion object {
        val HTTPS_API_GITHUB_URL: String = "https://api.github.com/"
    }

    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(
        @Path("user") user: String,
        @Path("reponame") projectName: String
    ): Call<Project>
}