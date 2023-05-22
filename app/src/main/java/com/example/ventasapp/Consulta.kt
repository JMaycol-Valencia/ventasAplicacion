package com.example.ventasapp

import android.annotation.SuppressLint
import android.database.Cursor

class Consulta {
    var nombreCliente: String = ""
    var codigoPedido: String = ""
    // Agrega aquí las otras propiedades relevantes para tu consulta

    companion object {
        @SuppressLint("Range")
        fun fromCursor(cursor: Cursor): Consulta {
            val consulta = Consulta()
            consulta.nombreCliente = cursor.getString(cursor.getColumnIndex("NombreCliente"))
            consulta.codigoPedido = cursor.getString(cursor.getColumnIndex("CodigoPedido"))
            // Asigna aquí las otras propiedades relevantes para tu consulta

            return consulta
        }
    }
}
