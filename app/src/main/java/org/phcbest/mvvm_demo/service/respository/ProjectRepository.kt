package org.phcbest.mvvm_demo.service.respository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.phcbest.mvvm_demo.service.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 单例
 */
@Singleton
class ProjectRepository {
    private lateinit var gieHubService: GitHubService

    private val TAG = "ProjectRepository"

    /**
     * 方法中的参数是由提供者提供，当然也通过依赖注入框架调用
     */
    @Inject
    constructor(gieHubService: GitHubService) {
        this.gieHubService = gieHubService
    }

    /**
     * 获得项目列表
     */
    fun getProjectList(userId: String): LiveData<List<Project>> {
        val data = MutableLiveData<List<Project>>()

        gieHubService.getProjectList(userId).enqueue(object : Callback<List<Project>> {
            override fun onResponse(
                call: Call<List<Project>>?,
                response: Response<List<Project>>?
            ) {
                Log.i(TAG, "onResponse: 获得项目列表${response?.body().toString()} ")
                data.value = response?.body()
            }

            override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                data.value = null
            }
        })
        return data
    }


    /**
     * 获得项目详情
     */
    fun getProjectDetails(userId: String, projectName: String): LiveData<Project> {
        val data = MutableLiveData<Project>()

        gieHubService.getProjectDetails(userId, projectName).enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {
                Log.i(TAG, "onResponse: 获得项目详情${response?.body()} ")
                simulateDelay()
                data.value = response?.body()
            }

            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                data.value = null
            }
        })
        return data
    }

    /**
     * 模拟延迟
     */
    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}