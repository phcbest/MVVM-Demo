package org.phcbest.mvvm_demo.view.callback

import org.phcbest.mvvm_demo.service.model.Project

interface ProjectClickCallback {
    fun onClick(project: Project)

}