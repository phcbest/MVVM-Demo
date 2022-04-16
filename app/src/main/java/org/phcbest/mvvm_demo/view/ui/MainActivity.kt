package org.phcbest.mvvm_demo.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.service.model.Project
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //添加列表Fragment
        if (savedInstanceState == null) {
            val projectListFragment = ProjectListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, projectListFragment, ProjectListFragment.TAG).commit()
        }
    }

    /**
     * 切换为显示详情的Fragment
     */
    fun show(project: Project) {
        //实例化Fragment的时候将project的name传递进去
        val projectFragment = ProjectFragment.forProject(project.name)
        supportFragmentManager.beginTransaction().addToBackStack("project")
            .replace(R.id.fragment_container, projectFragment, null).commit()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}