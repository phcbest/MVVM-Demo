package org.phcbest.mvvm_demo.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.phcbest.mvvm_demo.view.ui.MainActivity

@Module
abstract class MainActivityModel {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}