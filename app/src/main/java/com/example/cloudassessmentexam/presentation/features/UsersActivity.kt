package com.example.cloudassessmentexam.presentation.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.cloudassessmentexam.R
import com.example.cloudassessmentexam.data.User
import com.example.cloudassessmentexam.presentation.design_system.composables.CloudTopAppBar
import com.example.cloudassessmentexam.presentation.design_system.composables.UserItem

// TODO: Update when integrating with API
val users = listOf(
    User(
        id = "0001",
        name = "Dave",
        avatarURL = "https://avatarfiles.alphacoders.com/375/thumb-150-375473.jpeg",
        username = "davedecastro",
        displayName = "Darryl Dave de Castro",
        reputation = 9612,
        memberSince = "March 20, 2026"
    ),
    User(
        id = "0002",
        name = "Spiderman",
        avatarURL = "https://psn-rsc.prod.dl.playstation.net/psn-rsc/avatar/UP9000/CUSA02299_00-SPIDERMANAVATAR1_7BFBCC8CD9157D0D21F8_l.png",
        username = "peterparker",
        displayName = "Peter Parker",
        reputation = 9611,
        memberSince = "March 21, 2026"
    ), User(
        id = "0003",
        name = "Iron Man",
        avatarURL = "https://img.freepik.com/premium-psd/iron-man-marvel-portrait-as-social-media-avatar-template-mockup-white-background_985204-159861.jpg",
        username = "tonystark",
        displayName = "Tony Stark",
        reputation = 9613,
        memberSince = "March 22, 2026"
    ), User(
        id = "0004",
        name = "Alexander Hamilton",
        avatarURL = "Error",
        username = "alexanderhamilton",
        displayName = "Alexander Hamilton",
        reputation = 9614,
        memberSince = "March 23, 2026"
    )
)

@Composable
fun UsersScreen(
    navigateToUserDetails: (User) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = colorResource(R.color.backgroundColor),
        topBar = {
            CloudTopAppBar(navigationIcon = Icons.Default.Person, onActionButtonClick = {})
        }) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                UserItem(user, navigateToUserDetails = navigateToUserDetails)
            }
        }
    }
}