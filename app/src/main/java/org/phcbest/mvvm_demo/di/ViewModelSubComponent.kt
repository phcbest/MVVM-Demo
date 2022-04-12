package org.phcbest.mvvm_demo.di

import dagger.Subcomponent
import org.phcbest.mvvm_demo.viewmodel.ProjectListViewModel
import org.phcbest.mvvm_demo.viewmodel.ProjectViewModel

/**
 * 子组件
 */
@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun projectListViewModel(): ProjectListViewModel
    fun projectViewModel(): ProjectViewModel
}