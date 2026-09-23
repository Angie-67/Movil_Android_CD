package com.vasquez.navlab.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasquez.navlab.model.StudentRepository
import com.vasquez.navlab.navigation.Screen
import com.vasquez.navlab.ui.components.StudentAvatar
import com.vasquez.navlab.ui.theme.PurpleCardBackground
import com.vasquez.navlab.ui.theme.PurpleLightBackground
import com.vasquez.navlab.ui.theme.PurpleMedium
import com.vasquez.navlab.ui.theme.PurpleTextSubtitle
import com.vasquez.navlab.ui.theme.PurpleTextTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val students = StudentRepository.students

    Scaffold(
        containerColor = PurpleLightBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Directorio de Alumnos",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(students) { student ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.Detail.createRoute(student.id)
                            )
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = PurpleCardBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StudentAvatar(
                            name = student.name,
                            size = 52.dp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = student.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = PurpleTextTitle
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = student.career,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = PurpleMedium
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Ver detalle",
                            tint = PurpleTextSubtitle
                        )
                    }
                }
            }
        }
    }
}
