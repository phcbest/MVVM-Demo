package org.phcbest.mvvm_demo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.phcbest.mvvm_demo.R
import org.phcbest.mvvm_demo.databinding.ProjectListItemBinding
import org.phcbest.mvvm_demo.service.model.Project
import org.phcbest.mvvm_demo.view.callback.ProjectClickCallback
import java.util.*

class ProjectAdapter(var projectClickCallback: ProjectClickCallback) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    private var projectList: List<Project>? = null

    /**
     * 设置项目列表
     */
    fun setProjectListParam(list: List<Project>) {
        if (this.projectList == null) {
            this.projectList = list
            notifyItemRangeChanged(0, list.size)
        } else {
            //计算两个list之间的差距
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@ProjectAdapter.projectList!!.size
                }

                override fun getNewListSize(): Int {
                    return list.size
                }

                //判断项目内容是否相等
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@ProjectAdapter.projectList!![oldItemPosition] == list[newItemPosition]
                }

                //判断数量是否相等
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val project = list[newItemPosition]
                    val old = list[oldItemPosition]
                    return project.id == old.id && Objects.equals(project.git_url, old.git_url)
                }
            })
            this.projectList = list
            result.dispatchUpdatesTo(this)
        }
    }


    class ViewHolder(bind: ProjectListItemBinding) : RecyclerView.ViewHolder(bind.root) {
        var binding = bind
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val projectListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.project_list_item,
            parent,
            false
        ) as ProjectListItemBinding
        //设置点击事件
        projectListItemBinding.callback = projectClickCallback
        return ViewHolder(projectListItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.project = projectList?.get(position)
        //将绑定的数据更新到UI上
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return projectList?.size ?: 0
    }


}