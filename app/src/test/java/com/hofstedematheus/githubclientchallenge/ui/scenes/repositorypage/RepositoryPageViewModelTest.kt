package com.hofstedematheus.githubclientchallenge.ui.scenes.repositorypage

import com.hofstedematheus.githubclientchallenge.data.core.Result
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories.PublicRepositoriesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

@ExperimentalCoroutinesApi
class RepositoryPageViewModelTest: KoinTest {
    val dispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: RepositoryPageViewModel

    private val testModule = module(override = true) {
        viewModel {
            RepositoryPageViewModel()
        }
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        startKoin {
            modules(listOf(testModule))
        }
        viewModel = get()
    }

    @Test
    fun `when updateRepository() with repository, then _repository should update`() {

        // Arrange
        val repository: PublicRepository = mockk()

        // Act
        viewModel.updateRepository(repository)

        // Assert
        viewModel.repository.value?.let { repository ->
            assert(repository != null)
        }
    }

}