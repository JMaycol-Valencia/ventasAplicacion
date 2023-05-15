package com.example.ventasapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

var BD = "baseDatos"

class BaseDatos (contexto: Context): SQLiteOpenHelper(contexto,BD,null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        var sql="CREATE TABLE Producto(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(25), precio INTEGER, stock INTEGER)"
        db?.execSQL(sql)
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


}


