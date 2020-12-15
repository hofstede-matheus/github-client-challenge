package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import org.koin.core.component.KoinComponent

class PublicRepositoriesViewModel(val repository: RepositoriesRepository) : ViewModel(), KoinComponent {

    private val _publicRepositories = MutableLiveData<List<PublicRepository>>()

    val publicRepositories: LiveData<List<PublicRepository>>
        get() = _publicRepositories


    fun getPublicRepositoriesList() {

    }


}