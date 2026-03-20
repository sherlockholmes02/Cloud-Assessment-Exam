package com.example.cloudassessmentexam.presentation.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.cloudassessmentexam.data.User
import com.example.cloudassessmentexam.presentation.features.UsersScreen
import com.example.cloudassessmentexam.presentation.navigation.NavigationDestination

object UsersDestination : NavigationDestination {
    override val route: String = "users_route"
    override val destination: String = "users_destination"
}

fun NavGraphBuilder.usersScreen(
    navigateToUserDetails: (User) -> Unit
) {
    composable(
        route = UsersDestination.route,
    ) {
        UsersScreen(
            navigateToUserDetails
        )
    }
}