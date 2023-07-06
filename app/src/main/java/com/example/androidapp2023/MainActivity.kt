package com.example.androidapp2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.androidapp2023.ui.theme.AndroidApp2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidApp2023Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "main_screen") {
                        composable("main_screen") { MainScreen(navController) }
                        composable("detail_screen/{itemId}", arguments = listOf(navArgument("itemId") { type = NavType.IntType })) { backStackEntry ->
                            DetailScreen(itemId = backStackEntry.arguments?.getInt("itemId"))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val items = List(20) { it }
    LazyColumn {
        items(items) { item ->
            Text(
                text = "Item $item",
                modifier = Modifier.clickable {
                    navController.navigate("detail_screen/$item")
                }
            )
        }
    }
}

@Composable
fun DetailScreen(itemId: Int?) {
    Text(text = "Detail Screen for item $itemId")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AndroidApp2023Theme {
        val navController = rememberNavController()
        MainScreen(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(itemId = 5)
}
