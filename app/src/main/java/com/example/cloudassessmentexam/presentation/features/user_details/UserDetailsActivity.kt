package com.example.cloudassessmentexam.presentation.features.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PersonPinCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.cloudassessmentexam.R
import com.example.cloudassessmentexam.core.util.convertLongToDateString
import com.example.cloudassessmentexam.domain.entities.User
import com.example.cloudassessmentexam.presentation.design_system.composables.CloudTopAppBar

@Composable
fun UserDetailsScreen(
    user: User?,
    navigateToUsers: () -> Unit,
) {
    Scaffold(
        containerColor = colorResource(R.color.backgroundColor), topBar = {
            CloudTopAppBar(
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                title = stringResource(R.string.user_details),
                onNavigationButtonClick = {
                    navigateToUsers.invoke()
                })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(colorResource(R.color.backgroundColor))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//          Profile Section
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SubcomposeAsyncImage(
                        model = user?.profileImage,
                        loading = {
                            CircularProgressIndicator(
                                color = colorResource(R.color.primaryColor),
                            )
                        },
                        error = {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = null,
                                tint = colorResource(R.color.primaryColor)
                            )
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .border(
                                width = 4.dp,
                                color = colorResource(R.color.primaryColor),
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = user?.displayName ?: "",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "@${user?.accountId}",
                        fontSize = 16.sp,
                        color = colorResource(R.color.gray)
                    )
                }
            }

//          Details Card Section
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    InfoRow(
                        icon = Icons.Default.PersonPinCircle,
                        iconBg = colorResource(R.color.primaryColor),
                        label = "User ID",
                        value = user?.location
                    )

                    InfoRow(
                        icon = Icons.Default.EmojiEvents,
                        iconBg = colorResource(R.color.orange),
                        label = "Reputation",
                        value = "%,d".format(user?.reputation),
                        highlight = true
                    )

                    InfoRow(
                        icon = Icons.Default.DateRange,
                        iconBg = colorResource(R.color.green),
                        label = "Member Since",
                        value = convertLongToDateString(user?.memberSince ?: 0, "MMMM dd, yyyy")
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector, iconBg: Color, label: String, value: String?, highlight: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(iconBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon, contentDescription = null, tint = colorResource(R.color.white)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column {
            Text(
                text = label, fontSize = 14.sp, color = colorResource(R.color.gray)
            )

            if (highlight) {
                Surface(
                    shape = RoundedCornerShape(50), color = colorResource(R.color.orange)
                ) {
                    Text(
                        text = value ?: "", modifier = Modifier.padding(
                            horizontal = 12.dp, vertical = 6.dp
                        ), color = colorResource(R.color.white), fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                Text(
                    text = value ?: "", fontSize = 18.sp, fontWeight = FontWeight.Medium
                )
            }
        }
    }
}