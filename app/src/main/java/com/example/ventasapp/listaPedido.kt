package com.example.ventasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class listaPedido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pedido)

        val mensaje = intent.getStringExtra("mensaje")

        val mensajeTextView = findViewById<TextView>(R.id.Mensaje)
        mensajeTextView.text = mensaje
    }
}