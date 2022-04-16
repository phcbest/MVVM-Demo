package org.phcbest.mvvm_demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.service.respository.ProjectRepository
import javax.inject.Inject

class ProjectListViewModel : AndroidViewModel {

    //UI可以直接观测该参数
    private lateinit var projectListObservable: LiveData<List<Project>>

    /**
     * 在构造的时候进行了网络请求,获得list
     */
    @Inject
    constructor(
        projectRepository: ProjectRepository,
        application: Application
    ) : super(application) {
        this.projectListObservable = projectRepository.getProjectList(application.getString(R.string.userid))
    }

    //UI可以直接观测该参数
    fun getProjectListObservable(): LiveData<List<Project>> {
        return projectListObservable
    }

}