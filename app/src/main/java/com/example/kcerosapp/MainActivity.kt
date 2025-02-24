package com.example.kcerosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kcerosapp.navegacion.dishList.DishList
import com.example.kcerosapp.ui.theme.*
import com.google.firebase.Firebase
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        enableEdgeToEdge()
        setContent {
            KcerosAppTheme {
                /*val navController = rememberNavController() // Controlador de navegación

                NavHost(navController = navController, startDestination = "pantalla1") {
                    composable("mainView") { MainView(modifier = Modifier, navController) }
                }*/
                DishList()
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = modifier.fillMaxSize()
        .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Kceros App",
            Modifier.padding(bottom = 20.dp))

        Button(onClick = {navController.navigate("dishList")}) {
            Text(modifier = Modifier.padding(25.dp),
                text = "Ir a la lista de platos")
        }

        Spacer(modifier = Modifier.padding(25.dp))

        Button(onClick = {navController.navigate("addDish")}) {
            Text(modifier = Modifier.padding(25.dp),
                text = "Ir a agregar un plato")
        }
    }
}

@Preview
@Composable
private fun MainViewPreview() {
    KcerosAppTheme {
        val navController = rememberNavController()
        MainView(modifier = Modifier, navController = navController)
    }
}