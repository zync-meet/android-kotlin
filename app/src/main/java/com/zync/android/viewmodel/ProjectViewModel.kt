package com.zync.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zync.android.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: ProjectRepository) : ViewModel() {
    val projects = repository.allProjects
    
    fun fetchProjects() {
        viewModelScope.launch {
            repository.refreshProjects()
        }
    }
}
