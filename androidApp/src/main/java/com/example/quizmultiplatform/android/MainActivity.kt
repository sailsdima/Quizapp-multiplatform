package com.example.quizmultiplatform.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizmultiplatform.SDK
import com.example.quizmultiplatform.android.ui.MainScreen
import com.example.quizmultiplatform.data.db.DatabaseDriverFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val sdk = SDK(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Button(onClick = {
                    lifecycleScope.launch {
                        Toast.makeText(this@MainActivity, "id: ${sdk.addCategory()}", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Add category")
                }
                QuizAppMainGraph(rememberNavController())

            }
        }
    }
}

@Composable
fun QuizAppMainGraph(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen({}, {})
        }
        composable("gameScreen") {
            MainScreen({}, {})
        }
        composable("settingsScreen") {
            MainScreen({}, {})
        }
    }
}