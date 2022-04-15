package org.phcbest.mvvm_demo.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.phcbest.mvvm_demo.view.ui.ProjectFragment
import org.phcbest.mvvm_demo.view.ui.ProjectListFragment

@Module
abstract class FragmentBuildersModule {
    /**
     * 提供Android 项目Fragment
     */
    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment?

    @ContributesAndroidInjector
    abstract fun contributeProjectListFragment(): ProjectListFragment?
}