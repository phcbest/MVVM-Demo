package org.phcbest.mvvm_demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import org.phcbest.mvvm_demo.service.model.Project

class ProjectListViewModel : AndroidViewModel {

    lateinit var projectListObservable: LiveData<List<Project>>

    constructor(application: Application) : super(application) {
        
    }
}