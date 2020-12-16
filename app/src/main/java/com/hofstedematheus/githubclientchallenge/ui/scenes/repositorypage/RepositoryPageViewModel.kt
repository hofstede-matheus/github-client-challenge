package com.hofstedematheus.githubclientchallenge.ui.scenes.repositorypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository

class RepositoryPageViewModel : ViewModel() {
    private val _repository = MutableLiveData<PublicRepository>()

    val repository: LiveData<PublicRepository>
        get() = _repository

    fun updateRepository(repository: PublicRepository) {
        _repository.value = repository
    }
}