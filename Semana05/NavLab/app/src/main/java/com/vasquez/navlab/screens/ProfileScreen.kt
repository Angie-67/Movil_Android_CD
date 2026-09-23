package com.vasquez.navlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vasquez.navlab.navigation.Screen
import com.vasquez.navlab.ui.components.StudentAvatar
import com.vasquez.navlab.ui.theme.PurpleDarkHeader
import com.vasquez.navlab.ui.theme.PurpleIconBox
import com.vasquez.navlab.ui.theme.PurpleLightBackground
import com.vasquez.navlab.ui.theme.PurpleMedium
import com.vasquez.navlab.ui.theme.PurplePrimary
import com.vasquez.navlab.ui.theme.PurpleTextSubtitle
import com.vasquez.navlab.ui.theme.PurpleTextTitle
import com.vasquez.navlab.ui.theme.RedLogoutBackground
import com.vasquez.navlab.ui.theme.RedLogoutText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val profileName = "Angieluz Vasquez Macalupu"
    val profileEmail = "angieluz.vasquez@tecsup.edu.pe"
    val profilePhone = "+51 987 654 321"
    val profileCareer = "Diseño y Desarrollo de Software"
    val profileCycle = "IV Ciclo"

    Scaffold(
        containerColor = PurpleLightBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Configuración de Perfil",
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
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                PurpleDarkHeader,
                                PurpleMedium
                            )
                        )
                    )
                    .padding(vertical = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    StudentAvatar(
                        name = profileName,
                        size = 88.dp,
                        borderThickness = 3.dp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = profileName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "INFORMACIÓN PERSONAL",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleMedium,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                ProfileInfoItem(
                    icon = Icons.Default.Person,
                    label = "Nombre Completo",
                    value = profileName
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProfileInfoItem(
                    icon = Icons.Default.Email,
                    label = "Correo",
                    value = profileEmail
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProfileInfoItem(
                    icon = Icons.Default.Phone,
                    label = "Teléfono",
                    value = profilePhone
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "ACADÉMICO",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleMedium,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                ProfileInfoItem(
                    icon = Icons.Default.School,
                    label = "Carrera",
                    value = profileCareer
                )

                Spacer(modifier = Modifier.height(8.dp))

                ProfileInfoItem(
                    icon = Icons.Default.CalendarToday,
                    label = "Ciclo Actual",
                    value = profileCycle
                )

                Spacer(modifier = Modifier.height(28.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = RedLogoutBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar Sesión",
                            tint = RedLogoutText,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cerrar Sesión",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = RedLogoutText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ProfileInfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(PurpleIconBox, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = PurplePrimary,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    color = PurpleTextSubtitle
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleTextTitle
                )
            }
        }
    }
}
