package com.example.retro_savings_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retro_savings_challenge.ui.screens.DashboardScreen
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

import com.example.retro_savings_challenge.ui.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as RetroSavingsApp
        val viewModelFactory = ViewModelFactory(
            application.dashboardRepository,
            application.deviceProfile
        )

        setContent {
            RetroSavingsChallengeTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DashboardScreen(factory = viewModelFactory)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetroSavingsChallengeTheme(darkTheme = true) {
        DashboardScreen()
    }
}
