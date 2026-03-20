package com.example.cloudassessmentexam.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.cloudassessmentexam.presentation.navigation.destination.UsersDestination
import com.example.cloudassessmentexam.presentation.navigation.destination.navigateToUserDetails
import com.example.cloudassessmentexam.presentation.navigation.destination.userDetailsScreen
import com.example.cloudassessmentexam.presentation.navigation.destination.usersScreen

@Composable
fun NavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel = viewModel()
) {
    NavHost(navController, startDestination = UsersDestination.route) {
        usersScreen(
            navigateToUserDetails = { user ->
                sharedViewModel.selectUser(user)
                navController.navigateToUserDetails()
            },
        )
        userDetailsScreen(
            sharedViewModel =sharedViewModel,
            navigateToUsers = {
                navController.popBackStack()
            }
        )
    }
}