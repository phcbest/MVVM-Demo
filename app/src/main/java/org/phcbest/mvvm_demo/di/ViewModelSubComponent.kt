package org.phcbest.mvvm_demo.di

import dagger.Subcomponent
import org.phcbest.mvvm_demo.viewmodel.ProjectListViewModel

@Subcomponent
interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun projectListViewModel(): ProjectListViewModel
//  todo   fun projectViewModel(): ProjectViewModel
}