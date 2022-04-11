package org.phcbest.mvvm_demo.service.model

import java.util.*

class Project {
    var id: Long = 0
    var name: String? = null
    var full_name: String? = null
    var owner: User? = null
    var html_url: String? = null
    var description: String? = null
    var url: String? = null
    var created_at: Date? = null
    var updated_at: Date? = null
    var pushed_at: Date? = null
    var git_url: String? = null
    var ssh_url: String? = null
    var clone_url: String? = null
    var svn_url: String? = null
    var homepage: String? = null
    var stargazers_count = 0
    var watchers_count = 0
    var language: String? = null
    var has_issues = false
    var has_downloads = false
    var has_wiki = false
    var has_pages = false
    var forks_count = 0
    var open_issues_count = 0
    var forks = 0
    var open_issues = 0
    var watchers = 0
    var default_branch: String? = null

    fun Project() {}

    fun Project(name: String?) {
        this.name = name
    }
}