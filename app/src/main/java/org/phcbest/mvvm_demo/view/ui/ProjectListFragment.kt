package org.phcbest.mvvm_demo.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.databinding.FragmentProjectListBinding
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.view.adapter.ProjectAdapter
import org.phcbest.mvvm_demo.view.callback.ProjectClickCallback
import javax.inject.Inject

class ProjectListFragment : Fragment() {

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
        ViewModelProviders.of(this)
    }


    companion object {
        const val TAG = "ProjectListFragment"

    }
}