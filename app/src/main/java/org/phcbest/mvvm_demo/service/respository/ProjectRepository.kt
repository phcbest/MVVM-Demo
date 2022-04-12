package org.phcbest.mvvm_demo.service.respository

import javax.inject.Inject
import javax.inject.Singleton

/**
 * 单例
 */
@Singleton
class ProjectRepository {
    private lateinit var gieHubService: GitHubService


    /**
     * 方法中的参数是由提供者提供
     */
    @Inject
    constructor(gieHubService: GitHubService) {
        this.gieHubService = gieHubService
    }


}