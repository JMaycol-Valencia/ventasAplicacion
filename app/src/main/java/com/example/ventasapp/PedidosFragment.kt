package com.example.ventasapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var codigo: EditText
    lateinit var producto: EditText
    lateinit var nombre: EditText
    lateinit var celular: EditText
    lateinit var cantidad: EditText
    lateinit var datePedi: EditText
    lateinit var validacion: EditText
    lateinit var precio: EditText
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

        val view = inflater.inflate(R.layout.fragment_products, container, false)
        codigo = view.findViewById(R.id.Codigo)
        producto = view.findViewById(R.id.Producto)
        nombre = view.findViewById(R.id.Nombre)
        celular = view.findViewById(R.id.Celular)
        cantidad = view.findViewById(R.id.Stock)
        datePedi = view.findViewById(R.id.DatePedi)
        validacion = view.findViewById(R.id.Validacion)
        precio = view.findViewById(R.id.Precio)
        mensaje = view.findViewById(R.id.Mensaje)


        // Inflate the layout for this fragment
        return view
    }


    private fun guardarDatos(view:View) {
        val db = BaseDatos(requireContext()) // Usar requireContext() en lugar de "this" para obtener el contexto del fragmento
        val product = Producto()
        if (codigo.text.toString().isNotEmpty() && nombre.text.toString().isNotEmpty() && precio.text.toString().isNotEmpty() && stock.text.toString().isNotEmpty()) {
            product.codigo = codigo.text.toString().toInt()
            product.producto = productog.text.toString().toInt()
            product.nombre = nombre.text.toString()
            product.precio = precio.text.toString().toInt()
            product.stock = stock.text.toString().toInt()
            db.insertarDatos(product)
        }
        Toast.makeText(requireContext(), "Guardado Exitoso", Toast.LENGTH_LONG).show()
    }


    private fun listarDatos(view: View) {
        val db = BaseDatos(requireContext())
        if (codigo.text.toString().isNotEmpty()) {
            val datosL = db.listarDatos()
            val mensajeTexto = StringBuilder()

            for (i in 0 until datosL.size) {
                mensajeTexto.append("codigo: " + datosL[i].codigo + " nombre: " + datosL[i].nombre + " precio: " + datosL[i].precio + " stock: " + datosL[i].stock + "\n")
            }

            val intent = Intent(requireContext(), listaProducto::class.java)
            intent.putExtra("mensaje", mensajeTexto.toString())
            startActivity(intent)
        }
    }


    private fun borrarDatos(view:View) {
        val db = BaseDatos(requireContext()) // Usar requireContext() en lugar de "this" para obtener el contexto del fragmento
        if (codigo.text.toString().isNotEmpty()) {
            db.borrarDatos(codigo.text.toString())
        }
        Toast.makeText(requireContext(), "Se Borro el Producto", Toast.LENGTH_LONG).show()
    }

    private fun actualizarDatos(view:View) {
        val db = BaseDatos(requireContext()) // Usar requireContext() en lugar de "this" para obtener el contexto del fragmento
        if (nombre.text.toString().isNotEmpty() && precio.text.toString().isNotEmpty() && stock.text.toString().isNotEmpty() && codigo.text.toString().isNotEmpty()) {
            db.actualizar(
                codigo.text.toString(),
                nombre.text.toString(),
                precio.text.toString().toInt(),
                stock.text.toString().toInt()
            )
        }
        Toast.makeText(requireContext(), "Se actualizo el Producto", Toast.LENGTH_LONG).show()
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