package uz.dev.muhammadali.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

expect fun platform(): String

@Composable
fun HelloWord() {
    Scaffold {
        Icons.Default.CheckCircle
    }
}