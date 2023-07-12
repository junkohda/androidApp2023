package com.example.androidapp2023

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController


@Composable
fun DetailScreen(itemId: Int) {
    when (itemId) {
        1 -> createAgeAndGender()
        // ここにさらに多くのケースを追加することができます
        else -> "Unknown item"
    }
}

@Composable
fun createAgeAndGender() {
    val ageAndGender = AgeAndGender();
    ageAndGender.ageAndGenderChecker()
}