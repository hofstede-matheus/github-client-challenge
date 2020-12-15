package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hofstedematheus.githubclientchallenge.data.core.Result
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class PublicRepositoriesViewModel(val repository: RepositoriesRepository) : ViewModel(), KoinComponent {

    private val _publicRepositories = MutableLiveData<List<PublicRepository>>()
    private val _error = MutableLiveData<String>()
    private val _isFetchingData = MutableLiveData(true)

    val publicRepositories: LiveData<List<PublicRepository>>
        get() = _publicRepositories
    val error: LiveData<String>
        get() = _error
    val isFetchingData: LiveData<Boolean>
        get() = _isFetchingData

    fun getPublicRepositoriesList() {
        viewModelScope.launch(Dispatchers.Main) {
            _isFetchingData.value = true
            val result = withContext(Dispatchers.Default) {
                repository.getPublicRepositories(
                    (0..1000).random()
                )
            }

            when(result) {
                is Result.Success -> _publicRepositories.value = result.value
                is Result.Error -> _error.value = result.message
            }
            _isFetchingData.value = false
        }
    }
}