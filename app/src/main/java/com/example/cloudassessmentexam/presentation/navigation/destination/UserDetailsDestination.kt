package com.example.cloudassessmentexam.presentation.navigation.destination

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cloudassessmentexam.presentation.features.UserDetailsScreen
import com.example.cloudassessmentexam.presentation.navigation.NavigationDestination
import com.example.cloudassessmentexam.presentation.navigation.SharedViewModel

object UserDetailsDestination: NavigationDestination {
    override val route: String = "user_details_route"
    override val destination: String = "user_details_destination"
}

fun NavController.navigateToUserDetails() {
    this.navigate(UserDetailsDestination.route)
}

fun NavGraphBuilder.userDetailsScreen(
    sharedViewModel: SharedViewModel,
    navigateToUsers: () -> Unit,
) {
    composable(
        route = UserDetailsDestination.route
    ) {
        val user = sharedViewModel.selectedUser.collectAsState().value
        UserDetailsScreen(
            user = user,
            navigateToUsers = navigateToUsers
        )
    }
}