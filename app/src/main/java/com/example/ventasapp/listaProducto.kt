package com.example.ventasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class listaProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_producto)

        val mensaje = intent.getStringExtra("mensaje")

        val mensajeTextView = findViewById<TextView>(R.id.Mensaje)
        mensajeTextView.text = mensaje
    }

}