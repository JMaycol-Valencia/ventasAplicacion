package com.example.ventasapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsultaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsultaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var editTextNombreCliente: EditText
    private lateinit var editTextCodigoPedido: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val view = inflater.inflate(R.layout.fragment_consulta, container, false)*/

        /*editTextNombreCliente = view.findViewById(R.id.editTextNombreCliente)
        editTextCodigoPedido = view.findViewById(R.id.editTextCodigoPedido)

        val buttonConsultarPedidos: Button = view.findViewById(R.id.buttonConsultarPedidos)
        val buttonConsultarPedido: Button = view.findViewById(R.id.buttonConsultarPedido)


        val consultarPorNombreButton = view.findViewById<Button>(R.id.buttonConsultarPedidos)
        consultarPorNombreButton.setOnClickListener {
            val nombreCliente = editTextNombreCliente.text.toString()
            if (nombreCliente.isNotEmpty()) {
                listarPedidosPorNombreCliente(nombreCliente)
            } else {
                Toast.makeText(requireContext(), "Ingresa un nombre de cliente", Toast.LENGTH_SHORT).show()
            }
        }

        val consultarPorCodigoButton = view.findViewById<Button>(R.id.buttonConsultarPedido)
        consultarPorCodigoButton.setOnClickListener {
            val codigoPedido = editTextCodigoPedido.text.toString()
            if (codigoPedido.isNotEmpty()) {
                obtenerPedidoPorCodigo(codigoPedido)
            } else {
                Toast.makeText(requireContext(), "Ingresa un código de pedido", Toast.LENGTH_SHORT).show()
            }
        }
          */
        return inflater.inflate(R.layout.fragment_consulta, container, false)
    }

/*
    private fun listarPedidosPorNombreCliente(nombreCliente: String) {
        val db = BaseDatos(requireContext())
        val cursor = db.listarPedidosPorNombreCliente(nombreCliente)
        mostrarResultadoConsulta(cursor)
    }

    private fun obtenerPedidoPorCodigo(codigoPedido: String) {
        val db = BaseDatos(requireContext())
        val cursor = db.obtenerPedidoPorCodigo(codigoPedido)
        mostrarResultadoConsulta(cursor)
    }

    @SuppressLint("Range")
    private fun mostrarResultadoConsulta(cursor: Cursor?) {
        val mensajeTexto = StringBuilder()

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val codigoPedido = cursor.getInt(cursor.getColumnIndex("CodigoPedido"))
                val codigoProducto = cursor.getInt(cursor.getColumnIndex("CodigoProducto"))
                val nombreCliente = cursor.getString(cursor.getColumnIndex("NombreCliente"))
                val celular = cursor.getInt(cursor.getColumnIndex("Celular"))
                val cantidad = cursor.getInt(cursor.getColumnIndex("Cantidad"))
                val fechaPedido = cursor.getString(cursor.getColumnIndex("FechaPedido"))
                val validacion = cursor.getString(cursor.getColumnIndex("Validacion"))
                val precioTotal = cursor.getDouble(cursor.getColumnIndex("PrecioTotal"))

                mensajeTexto.append("Código Pedido: $codigoPedido\n")
                mensajeTexto.append("Código Producto: $codigoProducto\n")
                mensajeTexto.append("Nombre Cliente: $nombreCliente\n")
                mensajeTexto.append("Celular: $celular\n")
                mensajeTexto.append("Cantidad: $cantidad\n")
                mensajeTexto.append("Fecha de Pedido: $fechaPedido\n")
                mensajeTexto.append("Validación: $validacion\n")
                mensajeTexto.append("Precio Total: $precioTotal\n\n")
            } while (cursor.moveToNext())

            cursor.close()
        } else {
            mensajeTexto.append("No se encontraron resultados")
        }

        val mensajeEditText = requireView().findViewById<EditText>(R.id.Mensaje)
        mensajeEditText.setText(mensajeTexto.toString())
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConsultaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConsultaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}