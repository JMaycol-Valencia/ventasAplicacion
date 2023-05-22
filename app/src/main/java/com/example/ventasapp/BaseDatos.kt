package com.example.ventasapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

var BD = "baseDatos1"

class BaseDatos (contexto: Context): SQLiteOpenHelper(contexto,BD,null,1){


    override fun onCreate(db: SQLiteDatabase?) {
        /*var sql="CREATE TABLE Producto(codigo INTEGER PRIMARY KEY , nombre VARCHAR(25), precio INTEGER, stock INTEGER)"
        db?.execSQL(sql)*/

        val sqlProducto = "CREATE TABLE Producto(codigo INTEGER PRIMARY KEY, nombre VARCHAR(25), precio INTEGER, stock INTEGER)"
        val sqlPedido = "CREATE TABLE Pedido(codigoPedido INTEGER PRIMARY KEY, codigoProducto INTEGER, nombreCliente VARCHAR(25), celular VARCHAR(15), cantidad INTEGER, fechaPedido VARCHAR(10), validacion VARCHAR(50), precioTotal REAL)"
        val sqlConsulta = "CREATE TABLE Consulta(nombreCliente VARCHAR(25), nombreProducto VARCHAR(25), fechaProducto VARCHAR(10), cantidadPedidos INTEGER, precioTotal REAL)"

        db?.apply {
            execSQL(sqlProducto)
            execSQL(sqlPedido)
            execSQL(sqlConsulta)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertarDatos(producto: Producto):String{
        //prepara la base de datos para escribir
        var db = this.writableDatabase
        var contenedor = ContentValues()
        contenedor.put("nombre",producto.nombre)
        contenedor.put("precio",producto.precio)
        contenedor.put("stock",producto.stock)

        var resultado=db.insert("Producto", null,contenedor)
        if(resultado == -1.toLong()) {
            return "existio una falla en la base de datos"
        }else{
            return "Inserccion correcta"
        }
    }

    @SuppressLint("Range")
    fun listarDatos():MutableList<Producto> {
        var lista: MutableList<Producto> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Producto"
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var product = Producto()
                product.codigo = resultado.getString(resultado.getColumnIndex("codigo")).toInt()
                product.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                product.precio = resultado.getString(resultado.getColumnIndex("precio")).toInt()
                product.stock = resultado.getString(resultado.getColumnIndex("stock")).toInt()
                lista.add(product)
            } while (resultado.moveToNext())
        }
            resultado.close()
            db.close()
            return lista
    }

    fun actualizar(codigo:String,nombre:String,precio:Int,stock:Int)/*:String*/{
        val db= this.writableDatabase
        var contenedor = ContentValues()
        contenedor.put("nombre",nombre)
        contenedor.put("precio",precio)
        contenedor.put("stock",stock)
        db.update("Producto",contenedor,"codigo=?", arrayOf(codigo))
        /*var resultado = db.update("Producto",contenedor,"codigo=?", arrayOf(codigo))
        if (resultado > 0){
            return  "actualizcaion realizada"
        }else{
            return "no se realizo la actualizacion"
        }*/
    }

    fun borrarDatos(codigo: String){
        if (codigo.length>0) {
            val db = this.writableDatabase
            db.delete("Producto", "codigo=?", arrayOf(codigo))
            db.close()
        }
    }




    fun insertarPedido(pedido: Pedido): String{

        val db = this.writableDatabase
        val contenedor = ContentValues()

        contenedor.put("codigoProducto",pedido.codigoProducto)
        contenedor.put("nombreCliente",pedido.nombreCliente)
        contenedor.put("celular",pedido.celular)
        contenedor.put("cantidad",pedido.cantidad)
        contenedor.put("fechaPedido",pedido.fechaPedido)
        contenedor.put("validacion",pedido.validacion)
        contenedor.put("precioTotal",pedido.precioTotal)

        var resultado= db.insert("Pedido", null,contenedor)
        if(resultado == -1.toLong()) {
            return "existio una falla en la base de datos"
        }else{
            return "Inserccion correcta"
        }
    }

    @SuppressLint("Range")
    fun listarPedidos(): MutableList<Pedido> {
        val lista: MutableList<Pedido> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Pedido"
        val resultado = db.rawQuery(sql, null)

        if (resultado.moveToFirst()) {
            do {
                val pedido = Pedido()
                pedido.codigoPedido = resultado.getInt(resultado.getColumnIndex("codigoPedido").toInt())
                pedido.codigoProducto = resultado.getInt(resultado.getColumnIndex("codigoProducto").toInt())
                pedido.nombreCliente = resultado.getString(resultado.getColumnIndex("nombreCliente"))
                pedido.celular = resultado.getInt(resultado.getColumnIndex("celular").toInt())
                pedido.cantidad = resultado.getInt(resultado.getColumnIndex("cantidad").toInt())
                pedido.fechaPedido = resultado.getString(resultado.getColumnIndex("fechaPedido"))
                pedido.validacion = resultado.getString(resultado.getColumnIndex("validacion"))
                pedido.precioTotal = resultado.getDouble(resultado.getColumnIndex("precioTotal").toInt())
                lista.add(pedido)
            } while (resultado.moveToNext())
        }

        resultado.close()
        db.close()
        return lista
    }

    // Métodos de actualización para la tabla Pedido

    fun actualizarPedido(codigoPedido: String, codigoProducto:Int,nombreCliente: String,celular: Int, cantidad: Int, fechaPedido: String, validacion: String, precioTotal: Double){
        val db = this.writableDatabase
        val contenedor = ContentValues()
            contenedor.put("codigoProducto",codigoProducto)
            contenedor.put("nombreCliente",nombreCliente)
            contenedor.put("celular",celular)
            contenedor.put("cantidad",cantidad)
            contenedor.put("fechaPedido",fechaPedido)
            contenedor.put("validacion",validacion)
            contenedor.put("precioTotal",precioTotal)
            db.update("Pedido", contenedor, "codigoPedido = ?", arrayOf(codigoPedido))

/*        return if (resultado > 0) {
            "Actualización exitosa"
        } else {
            "No se pudo actualizar el pedido"
        }*/
    }

// Métodos de eliminación para la tabla Pedido


    fun borrarPedido(codigoPedido: String){
        if (codigoPedido.length>0) {
            val db = this.writableDatabase
            db.delete("Pedido", "codigo=?", arrayOf(codigoPedido))
            db.close()
        }
    }


    /*fun listarPedidosPorNombreCliente(nombreCliente: String): Cursor? {
        val db = this.writableDatabase
        val query = "SELECT * FROM Pedido WHERE NombreCliente = ?"
        val selectionArgs = arrayOf(nombreCliente)
        return db.rawQuery(query, selectionArgs)
    }

    fun obtenerPedidoPorCodigo(codigoPedido: String): Cursor? {
        val db = this.writableDatabase
        val query = "SELECT * FROM Pedido WHERE CodigoPedido = ?"
        val selectionArgs = arrayOf(codigoPedido)
        return db.rawQuery(query, selectionArgs)
    }
*/


}


