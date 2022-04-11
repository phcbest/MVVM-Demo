package org.phcbest.mvvm_demo

import android.app.Application
import android.util.Log
import org.phcbest.mvvm_demo.di.AppInjector

class MVVMApplication : Application() {

    private val TAG = "MVVMApplication"

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }


}