package org.phcbest.mvvm_demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.service.respository.ProjectRepository
import javax.inject.Inject

class ProjectListViewModel : AndroidViewModel {

    //UI可以直接观测该参数
    lateinit var projectListObservable: LiveData<List<Project>>

    /**
     * 将网络请求的类
     */
    @Inject
    constructor(
        projectRepository: ProjectRepository,
        application: Application
    ) : super(application) {
        this.projectListObservable = projectRepository.getProjectList("Google")
    }

}