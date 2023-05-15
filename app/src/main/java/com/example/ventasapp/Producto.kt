package com.example.ventasapp

class Producto {
    var codigo:Int = 0
    var nombre:String = " "
    var precio:Int = 0
    var stock:Int = 0

    constructor(nombre:String, precio:Int, stock:Int){
        this.nombre = nombre
        this.precio = precio
        this.stock = stock
    }

    constructor(){

    }
}