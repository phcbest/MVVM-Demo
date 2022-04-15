package org.phcbest.mvvm_demo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import org.phcbest.mvvm_demo.MVVMApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModel::class])
interface AppComponent {

    /**
     * 者接口是用于创建构造器的
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mvvmApplication: MVVMApplication?)
}