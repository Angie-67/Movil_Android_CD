package com.vasquez.navlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasquez.navlab.navigation.Screen
import com.vasquez.navlab.ui.theme.LightLavenderGradientEnd
import com.vasquez.navlab.ui.theme.PurpleDarkHeader
import com.vasquez.navlab.ui.theme.PurpleIconBox
import com.vasquez.navlab.ui.theme.PurplePrimary
import com.vasquez.navlab.ui.theme.PurpleTextSubtitle
import com.vasquez.navlab.ui.theme.PurpleTextTitle
import com.vasquez.navlab.ui.theme.RedLogoutText

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PurpleDarkHeader,
                        Color(0xFF8B6CB8),
                        LightLavenderGradientEnd
                    )
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Text(
                text = "Bienvenida,\nAngieluz Vasquez",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "¿Qué deseas gestionar hoy?",
                fontSize = 15.sp,
                color = Color.White.copy(alpha = 0.85f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Card 1: Directorio de Alumnos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.List.route) },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(PurpleIconBox, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Group,
                            contentDescription = "Directorio de Alumnos",
                            tint = PurplePrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "Directorio de Alumnos",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PurpleTextTitle
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Ver y gestionar estudiantes",
                            fontSize = 13.sp,
                            color = PurpleTextSubtitle
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card 2: Mi Perfil Académico
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Profile.route) },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(PurpleIconBox, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Mi Perfil Académico",
                            tint = PurplePrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "Mi Perfil Académico",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PurpleTextTitle
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Datos personales y progreso",
                            fontSize = 13.sp,
                            color = PurpleTextSubtitle
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom logout button
            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Cerrar Sesión",
                    tint = RedLogoutText,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cerrar Sesión Seguro",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RedLogoutText
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
