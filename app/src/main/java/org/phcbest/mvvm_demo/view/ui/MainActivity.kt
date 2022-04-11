package org.phcbest.mvvm_demo.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.service.model.Project

class MainActivity : AppCompatActivity() {

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
        val projectFragment = ProjectFragment.forProject(project.name)
        supportFragmentManager.beginTransaction().addToBackStack("project")
            .replace(R.id.fragment_container, projectFragment, null).commit()
    }
}