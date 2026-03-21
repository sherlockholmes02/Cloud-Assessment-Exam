package com.example.cloudassessmentexam.presentation.features.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cloudassessmentexam.R
import com.example.cloudassessmentexam.core.network.CoroutineErrorHandler
import com.example.cloudassessmentexam.data.User
import com.example.cloudassessmentexam.data.models.BaseUiState
import com.example.cloudassessmentexam.presentation.design_system.composables.CloudTopAppBar
import com.example.cloudassessmentexam.presentation.design_system.composables.UserItem

@Composable
fun UsersScreen(
    navigateToUserDetails: (User) -> Unit,
    usersViewModel: UsersViewModel = hiltViewModel()
) {
    val usersState by usersViewModel.usersFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        usersViewModel.loadUsers(object : CoroutineErrorHandler {
            override fun onError(message: String) {}
        })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = colorResource(R.color.backgroundColor),
        topBar = {
            CloudTopAppBar(navigationIcon = Icons.Default.Person, onActionButtonClick = {})
        }) { innerPadding ->

        when (val state = usersState) {
            is BaseUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            strokeWidth = 3.dp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(R.string.loading_users),
                            color = colorResource(R.color.primaryAccent)
                        )
                    }
                }
            }

            is BaseUiState.Success -> {
                LazyColumn(
                    contentPadding = innerPadding,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.data) { user ->
                        UserItem(user, navigateToUserDetails = navigateToUserDetails)
                    }
                }
            }

            is BaseUiState.Failure -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CloudOff,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = colorResource(R.color.error)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = state.errorMessage,
                            textAlign = TextAlign.Center,
                            color = colorResource(R.color.gray)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primaryColor)),
                            onClick = {
                                usersViewModel.loadUsers(object : CoroutineErrorHandler {
                                    override fun onError(message: String) {}
                                })
                            }
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Retry")
                        }
                    }
                }
            }

            is BaseUiState.Uninitialized -> BaseUiState.Uninitialized
        }

    }
}