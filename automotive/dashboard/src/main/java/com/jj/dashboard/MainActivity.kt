package com.jj.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jj.dashboard.ui.theme.AutomotiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutomotiveTheme {

    }
}