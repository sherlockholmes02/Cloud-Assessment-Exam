package com.example.cloudassessmentexam.presentation.features.users

import com.example.cloudassessmentexam.core.network.CoroutineErrorHandler
import com.example.cloudassessmentexam.domain.entities.User
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.example.cloudassessmentexam.domain.use_cases.GetUsersUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: UsersViewModel
    private val getUsersUseCase: GetUsersUseCase = mock()
    private val coroutineErrorHandler: CoroutineErrorHandler = mock()

    private val fakeUsers = listOf(
        User(
            accountId = 10001,
            displayName = "Darryl Dave de Castro",
            profileImage = "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=256&d=identicon&r=PG",
            reputation = 1367468,
            location = "Auckland New Zealand",
            memberSince = 1221344553
        ),
        User(
            accountId = 10002,
            displayName = "Peter Parker",
            profileImage = "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=256&d=identicon&r=PG",
            reputation = 1367469,
            location = "New York",
            memberSince = 1221344553
        ),
        User(
            accountId = 10003,
            displayName = "Tony Stark",
            profileImage = "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=256&d=identicon&r=PG",
            reputation = 1367465,
            location = "California",
            memberSince = 1221344553
        ),
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UsersViewModel(getUsersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun successfullyLoadedUsers() = runTest {
        // Arrange
        whenever(getUsersUseCase.execute()).thenReturn(
            flow {
                emit(BaseUiState.Loading)
                emit(BaseUiState.Success(fakeUsers))
            }
        )

        // Collect to trigger
        val job = launch { viewModel.filteredUsersState.collect { } }

        // Act
        viewModel.loadUsers(coroutineErrorHandler)
        advanceUntilIdle()

        // Assert
        val state = viewModel.filteredUsersState.value
        assertTrue(state is BaseUiState.Success)
        val success = state as BaseUiState.Success
        assertEquals(fakeUsers, success.data)

        job.cancel()
    }

    @Test
    fun filterUsersBasedOnSearch() = runTest {
        // Arrange
        whenever(getUsersUseCase.execute()).thenReturn(
            flow { emit(BaseUiState.Success(fakeUsers)) }
        )

        val job = launch { viewModel.filteredUsersState.collect { } }

        // Act
        viewModel.loadUsers(coroutineErrorHandler)
        advanceUntilIdle()
        viewModel.onSearchQueryChange("Darryl Dave de Castro")
        advanceUntilIdle()

        // Assert
        val state = viewModel.filteredUsersState.value
        assertTrue(state is BaseUiState.Success)
        val success = state as BaseUiState.Success
        assertEquals(
            listOf(
                User(
                    accountId = 10001,
                    displayName = "Darryl Dave de Castro",
                    profileImage = "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=256&d=identicon&r=PG",
                    reputation = 1367468,
                    location = "Auckland New Zealand",
                    memberSince = 1221344553
                )
            ), success.data
        )

        job.cancel()
    }

    @Test
    fun filterUsersBasedOnSearchButBlank() = runTest {
        // Arrange
        whenever(getUsersUseCase.execute()).thenReturn(
            flow { emit(BaseUiState.Success(fakeUsers)) }
        )

        val job = launch { viewModel.filteredUsersState.collect { } }

        // Act
        viewModel.loadUsers(coroutineErrorHandler)
        advanceUntilIdle()
        viewModel.onSearchQueryChange("")
        advanceUntilIdle()

        // Assert
        val state = viewModel.filteredUsersState.value
        assertTrue(state is BaseUiState.Success)
        val success = state as BaseUiState.Success
        assertEquals(fakeUsers, success.data)

        job.cancel()
    }

    @Test
    fun failToLoadUsers() = runTest {
        whenever(getUsersUseCase.execute()).thenReturn(
            flow {
                emit(BaseUiState.Loading)
                emit(BaseUiState.Failure("Error", 400))
            }
        )

        val job = launch { viewModel.filteredUsersState.collect { } }

        // Act
        viewModel.loadUsers(coroutineErrorHandler)
        advanceUntilIdle()

        // Assert
        val state = viewModel.filteredUsersState.value
        assertTrue(state is BaseUiState.Failure)

        job.cancel()
    }
}