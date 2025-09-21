package com.example.retro_savings_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetroSavingsChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Box(contentAlignment = Alignment.Center) {
                        AdaptiveProgressRing(progress = 0.75f, deviceProfile = DeviceProfile.HIGH)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetroSavingsChallengeTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            AdaptiveProgressRing(progress = 0.75f, deviceProfile = DeviceProfile.HIGH)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewLowProfile() {
    RetroSavingsChallengeTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            AdaptiveProgressRing(progress = 0.75f, deviceProfile = DeviceProfile.LOW)
        }
    }
}
