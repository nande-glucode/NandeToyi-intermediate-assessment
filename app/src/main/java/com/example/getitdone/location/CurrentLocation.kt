package com.example.getitdone.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CurrentLocation() {
    var locationText by remember { mutableStateOf("No location obtained :(") }
    var showPermissionResultText by remember { mutableStateOf(false) }
    var permissionResultText by remember { mutableStateOf("Permission Granted...") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Requesting location permission...",
            textAlign = TextAlign.Center
        )

        if (showPermissionResultText) {
            Text(text = permissionResultText, textAlign = TextAlign.Center)
            Text(text = locationText, textAlign = TextAlign.Center)
        }
    }
}