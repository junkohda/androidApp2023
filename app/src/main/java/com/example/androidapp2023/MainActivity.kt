package com.example.androidapp2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        composable(
                            "detail_screen/{itemId}",
                            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            DetailScreen(itemId = backStackEntry.arguments?.getInt("itemId") ?: -1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val customStrings = listOf("人判定", "じゃんけん（一発勝負）", "FizzBuzz", "お釣り金額", "偶数表示", "掛け算表", "最大公約数")

    val tableData = (1..customStrings.size).mapIndexed { index, _ ->
        index to customStrings[index]
    }
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.background(Color.Gray)
        ) {
            TableCell(text = "Number", weight = column1Weight)
            TableCell(text = "単元", weight = column2Weight)
        }

        tableData.map {
            val (id, text) = it
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("detail_screen/${id+1}") }
            ) {
                TableCell(text = "${id+1}", weight = column1Weight)
                TableCell(text = text, weight = column2Weight)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
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
