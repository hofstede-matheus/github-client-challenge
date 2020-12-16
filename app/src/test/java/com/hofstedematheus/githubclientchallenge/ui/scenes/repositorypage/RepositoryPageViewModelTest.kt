package com.hofstedematheus.githubclientchallenge.ui.scenes.repositorypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get


@ExperimentalCoroutinesApi
class RepositoryPageViewModelTest: KoinTest {

    @Rule @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
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
        viewModel.repository.value?.let { repositoryFromViewModel ->
            assert(repositoryFromViewModel == repository)
        }
    }

}