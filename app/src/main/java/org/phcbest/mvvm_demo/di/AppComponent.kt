package org.phcbest.mvvm_demo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import org.phcbest.mvvm_demo.MVVMApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjection::class, AppModule::class, MainActivityModel::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun build(): AppComponent?
    }

    fun inject(mvvmApplication: MVVMApplication?)
}