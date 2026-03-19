package com.example.cloudassessmentexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.cloudassessmentexam.data.User
import com.example.cloudassessmentexam.design_system.composables.CloudTopAppBar
import com.example.cloudassessmentexam.design_system.composables.UserItem
import com.example.cloudassessmentexam.design_system.theme.CloudassessmentexamTheme

class MainActivity : ComponentActivity() {

    // TODO: Update when integrating with API
    val users = listOf(
        User(id = "10001 ", name = "Darryl Dave de Castro"),
        User(id = "10002", name = "Peter Parker"),
        User(id = "10003", name = "Tony Stark"),
        User(id = "10004", name = "Alexander Hamilton"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloudassessmentexamTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = colorResource(R.color.backgroundColor),
                    topBar = {
                        CloudTopAppBar()
                    }) { innerPadding ->
                    LazyColumn(
                        contentPadding = innerPadding,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(users) { user ->
                            UserItem(user)
                        }
                    }
                }
            }
        }
    }
}