package com.example.kcerosapp.modelos

data class Plato(
                 val nombre: String,
                 val descripcion: String,
                 val precio: Double,
                 val imagen: String,
                 val stock: Int,
                 val calificacion: Double) {

    constructor() : this( "", "", 0.0, "", 0, 0.0)
}