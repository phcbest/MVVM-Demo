package org.phcbest.mvvm_demo.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.databinding.FragmentProjectListBinding
import org.phcbest.mvvm_demo.di.Injectable
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.view.adapter.ProjectAdapter
import org.phcbest.mvvm_demo.view.callback.ProjectClickCallback
import org.phcbest.mvvm_demo.viewmodel.ProjectListViewModel
import javax.inject.Inject

/**
 * 实现了Injectable,设置为可注入的
 */
class ProjectListFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentProjectListBinding
    private lateinit var projectAdapter: ProjectAdapter


    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_project_list,
            container,
            false
        ) as FragmentProjectListBinding
        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.adapter = projectAdapter
        binding.isLoading = true
        return binding.root
    }

    /**
     * adapter item 的点击事件
     */
    private var projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            // 切换为项目Fragment
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //使用ViewModel提供者提供个ViewModel对象
        val viewModel =
            ViewModelProviders.of(
                this,
                viewModelProvider
            )[ProjectListViewModel::class.java]

        observeViewModel(viewModel)
    }

    /**
     * 观测ViewModel
     */
    private fun observeViewModel(viewModel: ProjectListViewModel) {
        //当观测到这个数据发生改变时，执行以下操作
        viewModel.getProjectListObservable()
            .observe(this.viewLifecycleOwner, object : Observer<List<Project>> {
                override fun onChanged(t: List<Project>?) {
                    if (t != null) {
                        binding.isLoading = false
                        projectAdapter.setProjectListParam(t)
                    }
                }
            })
    }


    companion object {
        const val TAG = "ProjectListFragment"

    }
}