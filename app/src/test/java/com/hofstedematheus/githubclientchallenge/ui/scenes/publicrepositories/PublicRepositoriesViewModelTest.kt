package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import java.util.ArrayList
import com.hofstedematheus.githubclientchallenge.data.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Rule
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
class PublicRepositoriesViewModelTest: KoinTest {

    val dispatcher = TestCoroutineDispatcher()


    val publicRepositoriesRepositoryMock = mockk<RepositoriesRepository>()
    lateinit var viewModel: PublicRepositoriesViewModel

    private val testModule = module(override = true) {
        single { publicRepositoriesRepositoryMock }
        viewModel {
            PublicRepositoriesViewModel(
                repository = get()
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        startKoin {
            modules(listOf(testModule))
        }
        viewModel = get()
    }

    @Test
    fun `when getPublicRepositoriesList() succeeds, then publicRepositories should update`() {
        // Arrange
        val publicRepositoriesList = listOf<PublicRepository>(
            mockk()
        )

        val result = Result.Success(publicRepositoriesList)

        coEvery { publicRepositoriesRepositoryMock.getPublicRepositories() } returns result

        // Act
        viewModel.getPublicRepositoriesList()

        // Assert
        viewModel.publicRepositories.value?.let { publicRepositories ->
            assert(publicRepositories.isNotEmpty())
        }
    }

    @Test
    fun `when getPublicRepositoriesList() fails, then error should update`() {

        // Arrange
        val result = Result.Error("Error")

        coEvery { publicRepositoriesRepositoryMock.getPublicRepositories() } returns result

        // Act
        viewModel.getPublicRepositoriesList()

        // Assert
        viewModel.error.value?.let { error ->
            assert(error.isNotBlank())
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

}