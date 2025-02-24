package com.example.kcerosapp.navegacion.addDish

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import coil3.compose.AsyncImage
import com.example.kcerosapp.modelos.Plato
import com.example.kcerosapp.ui.theme.KcerosAppTheme
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDishDialog
            (onDismiss: () -> Unit,
             onDishAdded: (Plato) -> Unit,
             ) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
        }
    )

    BasicAlertDialog(
        onDismissRequest = onDismiss,

        content = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (selectedImageUri != null) {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Seleccionar imagen",
                            modifier = Modifier

                                .size(200.dp)
                                .clickable {

                                    launcher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts
                                                .PickVisualMedia
                                                .ImageOnly
                                        )
                                    )

                                }
                        )

                        Text("No se ha seleccionado ninguna imagen",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = cantidad,
                        onValueChange = { cantidad = it },
                        label = { Text("Cantidad") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = precio,
                        onValueChange = { precio = it },
                        label = { Text("Precio") }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = onDismiss) {
                            Text("Cancelar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            val newDish = Plato(
                                nombre = nombre,
                                descripcion = descripcion,
                                precio = precio.toDoubleOrNull() ?: 0.0,
                                imagen = selectedImageUri.toString(),
                                stock = cantidad.toIntOrNull() ?: 0,
                                calificacion = 0.0
                            )
                            onDishAdded(newDish)
                        }) {
                            Text("Guardar")
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun AddDishDialogPreview() {
    KcerosAppTheme {
        var dishes by remember { mutableStateOf(emptyList<Plato>()) }
        var showDialog by remember { mutableStateOf(false) }
        AddDishDialog(
            onDismiss = { showDialog = false },
            onDishAdded = { newDish ->
                dishes = dishes + newDish
                showDialog = false
            }
        )
    }
}
