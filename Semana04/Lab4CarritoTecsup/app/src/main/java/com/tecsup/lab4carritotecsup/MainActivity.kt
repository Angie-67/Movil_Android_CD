package com.tecsup.lab4carritotecsup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tecsup.lab4carritotecsup.ui.theme.Lab4CarritoTecsupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4CarritoTecsupTheme {
                    PantallaCarrito()
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4CarritoTecsupTheme {
        PantallaCarrito()
    }
}