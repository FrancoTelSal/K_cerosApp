package com.example.kcerosapp.navegacion.dishList.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State.Empty.painter
import coil3.compose.rememberAsyncImagePainter
import com.example.kcerosapp.modelos.Plato
import com.example.kcerosapp.ui.theme.KcerosAppTheme

//Item que aparecera en la lista con informacion del platillo
@Composable
fun DishListItem(plato: Plato) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Condicion if en el que mostrara un icono en
            // caso de que no haya imagen del platillo
            if (plato.imagen == ""){
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                )
            } else{ //Si hay imagen se mostrara en el item
                AsyncImage(
                    model = plato.imagen,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            //Columna donde se mostrar la informacion del
            // platillo (Nombre, descripcion y cantidad)
            Column {
                Text(text = plato.nombre)
                Text(text = plato.descripcion)
                Text(text = "Cantidad: ${plato.stock}")
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$${plato.precio}")
        }
    }
}

@Preview
@Composable
private fun DishListItemPreview() {
    KcerosAppTheme { 
        DishListItem(Plato("Ensalada de sandía", "Refrescante ensalada con sandía y queso feta", 10.99,  "",0,0.0))
    }
}