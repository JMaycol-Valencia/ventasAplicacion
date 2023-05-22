package com.example.ventasapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PedidosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PedidosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var codigoPedido: EditText
    lateinit var codigoProducto: EditText
    lateinit var nombreCliente: EditText
    lateinit var celular: EditText
    lateinit var cantidad: EditText
    lateinit var fechaPedido: EditText
    lateinit var validacion: EditText
    lateinit var precioTotal: EditText
    lateinit var mensaje: TextView

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

        val view = inflater.inflate(R.layout.fragment_pedidos, container, false)
        codigoPedido = view.findViewById(R.id.Codigo)
        codigoProducto = view.findViewById(R.id.Producto)
        nombreCliente = view.findViewById(R.id.Nombre)
        celular = view.findViewById(R.id.Celular)
        cantidad = view.findViewById(R.id.Cantidad)
        fechaPedido = view.findViewById(R.id.DatePedi)
        validacion = view.findViewById(R.id.Validacion)
        precioTotal = view.findViewById(R.id.Total)
        mensaje = view.findViewById(R.id.Mensaje)

        val guardarButton = view.findViewById<Button>(R.id.GuardarBt)
        guardarButton.setOnClickListener {
            guardarDatos(view)

        }

        val listarButton = view.findViewById<Button>(R.id.ListarBt)
        listarButton.setOnClickListener {
            listarDatos(view)
        }

        val borrarButton = view.findViewById<Button>(R.id.BorrarBt)
        borrarButton.setOnClickListener {
            borrarDatos(view)
        }

        val actualizarButton = view.findViewById<Button>(R.id.ActualizarBt)
        actualizarButton.setOnClickListener {
            actualizarDatos(view)
        }



        // Inflate the layout for this fragment
        return view
    }


   private fun guardarDatos(view:View) {
        val db = BaseDatos(requireContext()) // Usar requireContext() en lugar de "this" para obtener el contexto del fragmento
        val pedido = Pedido()
        if (codigoPedido.text.toString().isNotEmpty() && codigoProducto.text.toString().isNotEmpty() && nombreCliente.text.toString().isNotEmpty() && celular.text.toString().isNotEmpty() && cantidad.text.toString().isNotEmpty() && fechaPedido.text.toString().isNotEmpty() && validacion.text.toString().isNotEmpty() && precioTotal.text.toString().isNotEmpty()){
            pedido.codigoPedido = codigoPedido.text.toString().toInt()
            pedido.codigoProducto = codigoProducto.text.toString().toInt()
            pedido.nombreCliente = nombreCliente.text.toString()
            pedido.celular = celular.text.toString().toInt()
            pedido.cantidad = cantidad.text.toString().toInt()
            pedido.fechaPedido = celular.text.toString()
            pedido.validacion = cantidad.text.toString()
            pedido.precioTotal = celular.text.toString().toDouble()
            db.insertarPedido(pedido)
        }
        Toast.makeText(requireContext(), "Guardado Exitoso", Toast.LENGTH_LONG).show()
    }


    private fun listarDatos(view: View) {
        val db = BaseDatos(requireContext())
        if (codigoPedido.text.toString().isNotEmpty()) {
            val datosL = db.listarPedidos()
            val mensajeTexto = StringBuilder()

            for (i in 0 until datosL.size) {
                mensajeTexto.append("Codigo Pedido: " + datosL[i].codigoPedido + "Codigo Producto: " + datosL[i].codigoProducto + " Nombre Cliente: " + datosL[i].nombreCliente + " Celular: " + datosL[i].celular + " Cantidad: " + datosL[i].cantidad + " Fecha de Pedido : " + datosL[i].fechaPedido + " Validadcion: " + datosL[i].validacion + " Precio Total: " + datosL[i].precioTotal + "\n")
            }

            val intent = Intent(requireContext(), listaPedido::class.java)
            intent.putExtra("mensaje", mensajeTexto.toString())
            startActivity(intent)
        }
    }


    private fun borrarDatos(view:View) {
        val db = BaseDatos(requireContext()) // Usar requireContext() en lugar de "this" para obtener el contexto del fragmento
        if (codigoPedido.text.toString().isNotEmpty()) {
            db.borrarPedido(codigoPedido.text.toString())
        }
        Toast.makeText(requireContext(), "Se Borro el Producto", Toast.LENGTH_LONG).show()
    }

    private fun actualizarDatos(view:View) {
        val db = BaseDatos(requireContext()) // Usar requireContext() en lugar de "this" para obtener el contexto del fragmento
        if (codigoPedido.text.toString().isNotEmpty() && codigoProducto.text.toString().isNotEmpty() && nombreCliente.text.toString().isNotEmpty() && celular.text.toString().isNotEmpty() && cantidad.text.toString().isNotEmpty() && fechaPedido.text.toString().isNotEmpty() && validacion.text.toString().isNotEmpty() && precioTotal.text.toString().isNotEmpty()) {
            db.actualizarPedido(
                        codigoPedido.text.toString(),
                        codigoProducto.text.toString().toInt(),
                        nombreCliente.text.toString(),
                        celular.text.toString().toInt(),
                        cantidad.text.toString().toInt(),
                        fechaPedido.text.toString(),
                        validacion.text.toString(),
                        precioTotal.text.toString().toDouble()
            )
        }
        Toast.makeText(requireContext(), "Se actualizo el Pedido", Toast.LENGTH_LONG).show()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PedidosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PedidosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}