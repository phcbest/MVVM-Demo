package org.phcbest.mvvm_demo.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.databinding.FragmentProjectDetailsBinding
import org.phcbest.mvvm_demo.di.Injectable
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.viewmodel.ProjectViewModel
import javax.inject.Inject


class ProjectFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentProjectDetailsBinding


    companion object {
        const val KEY_PROJECT_ID = "project_id"
        private const val TAG = "ProjectFragment"

        fun forProject(projectID: String?): ProjectFragment {
            val projectFragment = ProjectFragment()
            val args = Bundle()
            args.putString(KEY_PROJECT_ID, projectID)
            projectFragment.arguments = args
            return projectFragment
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ProjectViewModel::class.java)
        Log.i(TAG, "onActivityCreated  ProjectID: ${arguments?.getString(KEY_PROJECT_ID)!!}")
        //获得project name
        viewModel.setProjectID(arguments?.getString(KEY_PROJECT_ID)!!)
        binding.projectViewModel = viewModel
        binding.isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        /**
         * 项目发生变化更新ui的操作
         */
        viewModel.getObservableProject()
            .observe(this.viewLifecycleOwner, object : Observer<Project> {
                override fun onChanged(t: Project?) {
                    if (t != null) {
                        binding.isLoading = false
                        viewModel.setProject(t)
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_project_details,
            container,
            false
        )
        return binding.root
    }
}