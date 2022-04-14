package org.phcbest.mvvm_demo.viewmodel

import android.util.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.phcbest.mvvm_demo.di.ViewModelSubComponent
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
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
        var creator = creators[modelClass]
        if (creator == null) {
            for (entry in creators.entries) {
                modelClass.isAssignableFrom(entry.key.javaClass)
                creator = entry.value
                break
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}