package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.data.repository.RepositoriesRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import com.hofstedematheus.githubclientchallenge.data.core.Result
import com.hofstedematheus.githubclientchallenge.data.constants.ERROR_INVALID_USER_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
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

    @Test
    fun `when getPublicRepositoriesList() is fetching, then _isFetchingData should be true, and shoud be false when done fetching`() {
        // Arrange
        val publicRepositoriesList = listOf<PublicRepository>(
            mockk()
        )

        val result = Result.Success(publicRepositoriesList)

        coEvery { publicRepositoriesRepositoryMock.getPublicRepositories() } returns result

        // Assert
        viewModel.isFetchingData.value?.let { isFetchingData ->
            assert(isFetchingData)
        }

        // Act
        viewModel.getPublicRepositoriesList()

        // Assert
        viewModel.isFetchingData.value?.let { isFetchingData ->
            assert(!isFetchingData)
        }
    }

    @Test
    fun `when searchPublicRepositoryByName() succeeds, then publicRepositories should update`() {

        // Arrange
        val publicRepository: PublicRepository =
            mockk()

        val result = Result.Success(publicRepository)

        coEvery { publicRepositoriesRepositoryMock.searchPublicRepositoryByName("") } returns result

        // Act
        viewModel.searchPublicRepositoryByName("")

        // Assert
        viewModel.publicRepositories.value?.let { publicRepositories ->
            assert(publicRepositories.isNotEmpty())
            assert(publicRepositories.size == 1)
        }
    }

    @Test
    fun `when searchPublicRepositoryByName() find no repository, then error should update`() {

        val result = Result.Error("")

        coEvery { publicRepositoriesRepositoryMock.searchPublicRepositoryByName("") } returns result

        // Act
        viewModel.searchPublicRepositoryByName("")

        // Assert
        viewModel.error.value?.let { error ->
            assert(error.isNotBlank())
        }
    }

    @Test
    fun `when given name is invalid on searchPublicRepositoryByName(), then error should update`() {

        val result = Result.Error(ERROR_INVALID_USER_NAME)

        coEvery { publicRepositoriesRepositoryMock.searchPublicRepositoryByName("") } returns result

        // Act
        viewModel.searchPublicRepositoryByName("")

        // Assert
        viewModel.error.value?.let { error ->
            assert(error.isNotBlank())
            assert(error == ERROR_INVALID_USER_NAME)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

}