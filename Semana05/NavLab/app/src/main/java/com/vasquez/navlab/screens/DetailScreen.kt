package com.vasquez.navlab.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasquez.navlab.model.StudentRepository
import com.vasquez.navlab.ui.components.StudentAvatar
import com.vasquez.navlab.ui.theme.PurpleCardBackground
import com.vasquez.navlab.ui.theme.PurpleDarkHeader
import com.vasquez.navlab.ui.theme.PurpleIconBox
import com.vasquez.navlab.ui.theme.PurpleLightBackground
import com.vasquez.navlab.ui.theme.PurpleMedium
import com.vasquez.navlab.ui.theme.PurplePrimary
import com.vasquez.navlab.ui.theme.PurpleTextSubtitle
import com.vasquez.navlab.ui.theme.PurpleTextTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, itemId: Int) {
    val student = StudentRepository.getStudentById(itemId)

    Scaffold(
        containerColor = PurpleLightBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Expediente Académico",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleTextTitle
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = PurpleTextTitle
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleLightBackground
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Banner + Overlapping Avatar Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    PurpleDarkHeader,
                                    PurpleMedium
                                )
                            ),
                            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                        )
                )

                StudentAvatar(
                    name = student.name,
                    size = 96.dp,
                    borderThickness = 4.dp,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Student Name and Career
            Text(
                text = student.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PurpleTextTitle,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = student.career,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = PurpleMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Details Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PurpleCardBackground),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    DetailRowItem(
                        icon = Icons.Default.AssignmentInd,
                        label = "ID Estudiantil",
                        value = student.studentCode
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DetailRowItem(
                        icon = Icons.Default.Email,
                        label = "Correo Electrónico",
                        value = student.email
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DetailRowItem(
                        icon = Icons.Default.School,
                        label = "Facultad",
                        value = student.faculty
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color(0xFFD6CBE3)
                    )

                    Text(
                        text = "Biografía",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleTextTitle
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = student.bio,
                        fontSize = 14.sp,
                        color = Color(0xFF49454F),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DetailRowItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(PurpleIconBox, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = PurplePrimary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = PurpleTextSubtitle
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = PurpleTextTitle
            )
        }
    }
}
