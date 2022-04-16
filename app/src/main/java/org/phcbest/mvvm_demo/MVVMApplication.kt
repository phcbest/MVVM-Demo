package org.phcbest.mvvm_demo

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.phcbest.mvvm_demo.di.AppInjector
import javax.inject.Inject

/**
 * dagger 2.23 版本后,将Has*Injector替换为了HasAndroidInjector
 */
class MVVMApplication : Application(), HasAndroidInjector {

    private val TAG = "MVVMApplication"

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}