package com.example.ventasapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: androidx.viewpager.widget.ViewPager
    /*lateinit var nombre: EditText
    lateinit var precio: EditText
    lateinit var stock: EditText
    lateinit var codigo: EditText
    lateinit var mensaje:TextView*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        /*nombre = findViewById(R.id.Nombre)
        precio = findViewById(R.id.Precio)
        stock = findViewById(R.id.Stock)
        codigo = findViewById(R.id.Codigo)
        mensaje = findViewById(R.id.Mensaje)*/

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(ProductsFragment(), "Productos")
        adapter.addFragment(PedidosFragment(), "Pedidos")
        adapter.addFragment(ConsultaFragment(), "Consultas")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)



    }

    class ViewPagerAdapter(fragmentManager: androidx.fragment.app.FragmentManager) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList = mutableListOf<Fragment>()
        private val titleList = mutableListOf<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }
    }

    /*fun guardarDatos(view:View){
        var bd = BaseDatos(this)
        var product = Producto()
        if (nombre.text.toString().length>0 && precio.text.toString().length>0 && stock.text.toString().length>0){
            product.nombre = nombre.text.toString()
            product.precio = precio.text.toString().toInt()
            product.stock = stock.text.toString().toInt()
            bd.insertarDatos(product)
        }
    }

    fun listarDatos(view: View){
        var bd = BaseDatos(this)
        if(codigo.text.toString().length>0){
            var datosL=bd.listarDatos()
            for (i in 0..datosL.size-1){
                mensaje.append("codigo: " + datosL.get(i).codigo + " nombre: "+ datosL.get(i).nombre + " precio: " + datosL.get(i).precio + " stock: " + datosL.get(i).stock)
            }
        }
    }

    fun borrarDatos(view: View){
        var db = BaseDatos(this)
        if(codigo.text.toString().length>0){
            db.borrarDatos(codigo.text.toString())
        }
    }

    fun actualizarDatos(view: View){
        var db = BaseDatos(this)
        if (nombre.text.toString().length>0 && precio.text.toString().length>0 && stock.text.toString().length>0 && codigo.text.toString().length>0){
            db.actualizar(codigo.text.toString(), nombre.text.toString(),precio.text.toString().toInt(),stock.text.toString().toInt())
        }
    }*/
}