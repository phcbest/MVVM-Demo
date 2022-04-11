package org.phcbest.mvvm_demo.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter

class CustomBindingAdapter {
    companion object {
        //这个需要提供@JvmStatic,因为需要编译为Java能访问到的静态方法
        @JvmStatic
        @BindingAdapter("visibleGone")
        fun showHind(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}