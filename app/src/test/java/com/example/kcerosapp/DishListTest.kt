package com.example.kcerosapp

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.kcerosapp.modelos.Plato // Importa tu clase Dish
import com.example.kcerosapp.navegacion.dishList.DishList
import org.junit.Rule
import org.junit.Test

class DishListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun agregarPlato_actualizaLista() {
        // 1. Configuración inicial
        val platosIniciales = emptyList<Plato>()
        composeTestRule.setContent {
            DishList(modifier = Modifier, platos = platosIniciales)
        }

        // 2. Simulación de la interacción del usuario
        val nuevoPlato = Plato(
            nombre = "Encebollado",
            descripcion = "Sabor Ecuatoriano",
            precio = 10.0,
            imagen = "",
            stock = 20,
            calificacion = 0.0
        )
        composeTestRule.onNodeWithText("Agregar platillo").performClick() // Abre el diálogo
        composeTestRule.onNodeWithText("Nombre").performTextInput(nuevoPlato.nombre)
        composeTestRule.onNodeWithText("Descripción").performTextInput(nuevoPlato.descripcion)
        composeTestRule.onNodeWithText("Cantidad").performTextInput(nuevoPlato.stock.toString())
        composeTestRule.onNodeWithText("Precio").performTextInput(nuevoPlato.precio.toString())

        composeTestRule.onNodeWithText("Guardar").performClick() // Guarda el plato

        // 3. Verificación de los resultados
        composeTestRule.onNodeWithText(nuevoPlato.nombre).assertIsDisplayed() // Verifica que el plato se muestra en la lista
    }
}