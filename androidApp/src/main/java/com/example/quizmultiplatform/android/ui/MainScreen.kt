package com.example.quizmultiplatform.android.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizmultiplatform.android.MyApplicationTheme

@Composable
fun MainScreen(
    onGameClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        MenuButton("Play game", onGameClick)
        MenuButton("Settings", onSettingsClick)
    }
}

@Preview(showBackground = true)
@Composable
fun MenuButtonPreview() {
    Surface {
        MyApplicationTheme {
            MainScreen({}, {})
        }
    }
}

@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val buttonPaddingAnimate by animateDpAsState(if (isExpanded) 50.dp else 0.dp)
    val buttonColorAnim by animateColorAsState(if (isExpanded) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary)

    Button(
        onClick = {
            isExpanded = !isExpanded
            onClick()
        },
        modifier = Modifier.padding(all = buttonPaddingAnimate),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColorAnim,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}