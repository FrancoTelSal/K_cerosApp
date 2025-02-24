package com.example.kcerosapp.navegacion.dishList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kcerosapp.modelos.Plato
import com.example.kcerosapp.navegacion.addDish.AddDishDialog
import com.example.kcerosapp.navegacion.dishList.componentes.DishListItem
import com.example.kcerosapp.ui.theme.KcerosAppTheme

@Composable
fun DishList(modifier: Modifier = Modifier) {
    var platos by remember { mutableStateOf(emptyList<Plato>()) }
    var showDialog by remember { mutableStateOf(false) }
    var dishToEdit by remember { mutableStateOf<Plato?>(null) }

    //Scaffold para el manejo de los items y
    // agregar un boton flotante,
    // el cual se usara para agregar los platillos
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
                dishToEdit = null // Reinicia dishToEdit al agregar un nuevo platillo
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar platillo")
            }
        }
    ) { paddingValues ->
        Column( //Columna usada para el manejo del dialog
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (showDialog) {
                AddDishDialog(
                    onDismiss = { showDialog = false },
                    onDishAdded = { newDish ->
                        platos = platos + newDish
                        showDialog = false
                    }
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "PLATO")
                        Text(text = "INFORMACIÓN")
                        Text(text = "PRECIO", modifier = Modifier
                            .align(Alignment.CenterVertically))
                    }
                }
                items(platos) { plato ->
                    DishListItem(plato = plato)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DishListPreview() {
    KcerosAppTheme {
        DishList()
    }
}
