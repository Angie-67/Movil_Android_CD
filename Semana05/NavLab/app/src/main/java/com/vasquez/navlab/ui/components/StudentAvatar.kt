package com.vasquez.navlab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@Composable
fun StudentAvatar(
    name: String,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    borderThickness: Dp = 0.dp
) {
    val initials = name.split(" ")
        .filter { it.isNotBlank() }
        .mapNotNull { it.firstOrNull()?.toString() }
        .take(2)
        .joinToString("")
        .uppercase()

    val avatarGradients = listOf(
        listOf(Color(0xFF7E57C2), Color(0xFF4A148C)), // Deep Purple
        listOf(Color(0xFFEC407A), Color(0xFF880E4F)), // Pink / Magenta
        listOf(Color(0xFF42A5F5), Color(0xFF0D47A1)), // Ocean Blue
        listOf(Color(0xFF26A69A), Color(0xFF004D40)), // Teal
        listOf(Color(0xFFA1887F), Color(0xFF3E2723)), // Brown Warm Tone
        listOf(Color(0xFFAB47BC), Color(0xFF4A148C))  // Purple Violet
    )
    val colorIndex = abs(name.hashCode()) % avatarGradients.size
    val gradientColors = avatarGradients[colorIndex]

    Box(
        modifier = modifier
            .size(size)
            .then(
                if (borderThickness > 0.dp) {
                    Modifier.border(borderThickness, Color.White, CircleShape)
                } else Modifier
            )
            .clip(CircleShape)
            .background(Brush.linearGradient(gradientColors)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = Color.White,
            fontSize = (size.value * 0.38f).sp,
            fontWeight = FontWeight.Bold
        )
    }
}
