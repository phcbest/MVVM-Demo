package org.phcbest.mvvm_demo.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import org.phcbest.mvvm_demo.MVVMApplication

/**
 * 初始化注入器
 */
class AppInjector {

    companion object {
        fun init(mvvmApplication: MVVMApplication) {
            DaggerAppComponent.builder().application(mvvmApplication).build()
                .inject(mvvmApplication)
            mvvmApplication.registerActivityLifecycleCallbacks(object :
                Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

                }

                override fun onActivityStarted(activity: Activity) {
                }

                override fun onActivityResumed(activity: Activity) {
                }

                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                }


            })
        }

        private fun handleActivity(activity:Activity) {
//            if (activity)
        }
    }
}