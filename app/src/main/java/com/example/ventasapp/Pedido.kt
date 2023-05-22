package com.example.ventasapp

class Pedido {
    var codigoPedido: Int = 0
    var codigoProducto: Int = 0
    var nombreCliente: String = ""
    var celular: Int = 0
    var cantidad: Int = 0
    var fechaPedido: String = ""
    var validacion: String = ""
    var precioTotal: Double = 0.0

    constructor(codigoProducto:Int,nombreCliente: String,celular: Int, cantidad: Int, fechaPedido: String, validacion: String, precioTotal: Double ){
        this.codigoProducto = codigoProducto
        this.nombreCliente = nombreCliente
        this.celular = celular
        this.cantidad = cantidad
        this.fechaPedido = fechaPedido
        this.validacion = validacion
        this.precioTotal = precioTotal

    }

    constructor(){

    }
}