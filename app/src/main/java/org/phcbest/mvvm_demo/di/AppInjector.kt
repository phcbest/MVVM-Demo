package org.phcbest.mvvm_demo.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
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
                    handleActivity(activity)
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

        private fun handleActivity(activity: Activity) {
            //如果类继承了HasAndroidInjector
            if (activity is HasAndroidInjector) {
                //实现注入
                AndroidInjection.inject(activity)
            }
            //如果是Activity的实现
            if (activity is FragmentActivity) {
                //设置fragment的生命周期监听
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object :
                    FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        //如果fragment继承Injectable,实现注入
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true)
            }
        }
    }
}