package org.phcbest.mvvm_demo.viewmodel

import android.util.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.phcbest.mvvm_demo.di.ViewModelSubComponent
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectViewModelFactory : ViewModelProvider.Factory {

    lateinit var creators: ArrayMap<Any, Callable<ViewModel>>


    @Inject
    constructor(viewModelSubComponent: ViewModelSubComponent) {
        creators = ArrayMap()
        creators[ProjectViewModel::class.java] =
            Callable<ViewModel> { viewModelSubComponent.projectViewModel() }
        creators[ProjectListViewModel::class.java] =
            Callable<ViewModel> { viewModelSubComponent.projectListViewModel() }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }

}