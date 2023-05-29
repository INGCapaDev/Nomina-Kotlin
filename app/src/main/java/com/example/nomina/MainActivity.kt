package com.example.nomina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var btnEntrar: Button
    private lateinit var btnSalir: Button
    private lateinit var txtNombre : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarComponentes()
        btnEntrar.setOnClickListener { ingresar() }
        btnSalir.setOnClickListener { cerrar() }
    }

    fun iniciarComponentes(){
        btnEntrar = findViewById(R.id.btnEntrar)
        btnSalir = findViewById(R.id.btnSalir)
        txtNombre = findViewById(R.id.txtNombre)
    }

    private fun ingresar(){
        if(txtNombre.text.toString().isEmpty()){
            Toast.makeText(this.applicationContext, "Nombre Requirido", Toast.LENGTH_SHORT).show()
            return
        }
        val strNombre = txtNombre.text.toString()
        val intent = Intent(this@MainActivity, ReciboNominaActivity::class.java)
        intent.putExtra("strNombre", strNombre)
        startActivity(intent)
    }

    private fun cerrar(){
        val confirmar = AlertDialog.Builder(this)
        confirmar.setTitle("Nomina")
        confirmar.setMessage("¿ Desea Salir ?")
        confirmar.setPositiveButton("Confirmar"){_,_->finish()}
        confirmar.setNegativeButton("Cancelar"){_,_->}.show()
    }
}