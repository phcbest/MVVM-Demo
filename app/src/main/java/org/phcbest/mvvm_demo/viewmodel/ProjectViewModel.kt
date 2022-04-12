package org.phcbest.mvvm_demo.viewmodel

import android.app.Application
import android.util.Log
import androidx.arch.core.util.Function
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.service.respository.ProjectRepository
import javax.inject.Inject
import kotlin.math.log

class ProjectViewModel : AndroidViewModel {
    private val TAG = ProjectViewModel::class.java.name

    private val ABSENT = MutableLiveData<Project>()

    init {
        ABSENT.value = null
    }

    private lateinit var projectObserver: LiveData<Project>
    private lateinit var projectID: MutableLiveData<String>

    public var project = ObservableField<Project>()

    @Inject
    constructor(
        projectRepository: ProjectRepository,
        application: Application
    ) : super(application) {
        this.projectID = MutableLiveData()
        //委托生命周期，只有在活跃状态才会运行
        projectObserver =
            Transformations.switchMap(projectID, object : Function<String, LiveData<Project>> {
                override fun apply(input: String?): LiveData<Project> {
                    if (input?.isEmpty()!!) {
                        Log.i(TAG, "apply: projectViewModel 的项目Id不存在")
                        return ABSENT
                    }
                    Log.i(TAG, "apply: ProjectViewModel projectid is${projectID.value}")
                    return projectRepository.getProjectDetails("Google", projectID.value!!)
                }
            })
    }

    fun getObservableProject(): LiveData<Project> {
        return projectObserver
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    fun setProjectID(projectID: String) {
        this.projectID.value = projectID
    }


}