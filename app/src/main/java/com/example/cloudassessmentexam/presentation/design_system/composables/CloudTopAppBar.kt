package com.example.cloudassessmentexam.presentation.design_system.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cloudassessmentexam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloudTopAppBar(
    title: String? = null,
    navigationIcon: ImageVector?,
    onNavigationButtonClick: (() -> Unit)? = null,
    onActionButtonClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title ?: stringResource(id = R.string.app_name),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationButtonClick?.invoke() ?: null },
                enabled = onNavigationButtonClick != null
            ) {
                Icon(
                    navigationIcon ?: Icons.Default.Person,
                    contentDescription = null,
                    tint = colorResource(R.color.white)
                )
            }
        },
        actions = {
            onActionButtonClick?.let {
                IconButton(onClick = { onActionButtonClick.invoke() }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.primaryColor),
            titleContentColor = colorResource(id = R.color.white),
            navigationIconContentColor = colorResource(id = R.color.white),
            actionIconContentColor = colorResource(id = R.color.white)
        )
    )
}
