package com.example.nomina

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class ReciboNominaActivity : AppCompatActivity() {

    private lateinit var lblNumRecibo: TextView
    private lateinit var lblNombre: TextView
    private lateinit var txtHorasNormal: EditText
    private lateinit var txtHorasExtra: EditText
    private lateinit var rgPuesto:RadioGroup
    private lateinit var rdbPuesto1: RadioButton
    private lateinit var rdbPuesto2: RadioButton
    private lateinit var rdbPuesto3: RadioButton
    private lateinit var lblSubtotal: TextView
    private lateinit var lblImpuesto: TextView
    private lateinit var lblTotal: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    var reciboNomina = ReciboNomina(0,"Nombre",0.0,0.0,1,0.16)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recibo_nomina)
        iniciarComponentes()
        btnCalcular.setOnClickListener { calcular() }
        btnLimpiar.setOnClickListener { limpiar() }
        btnRegresar.setOnClickListener { regresar() }
    }

    @SuppressLint("SetTextI18n")
    private fun iniciarComponentes(){
        lblNumRecibo = findViewById(R.id.lblNumRecibo)
        lblNombre = findViewById(R.id.lblNombre)
        txtHorasNormal = findViewById(R.id.txtHorasNormal)
        txtHorasExtra = findViewById(R.id.txtHorasExtra)
        rdbPuesto1 = findViewById(R.id.rdbPuesto1)
        rdbPuesto2 = findViewById(R.id.rdbPuesto2)
        rdbPuesto3 = findViewById(R.id.rdbPuesto3)
        lblSubtotal = findViewById(R.id.lblSubtotal)
        lblImpuesto = findViewById(R.id.lblImpuesto)
        lblTotal = findViewById(R.id.lblTotal)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)
        rgPuesto = findViewById(R.id.rgPuesto)
        val numeroRecibo = Random.nextInt(10000)
        val strNombre = intent.getStringExtra("strNombre").toString()
        lblNombre.text = "Nombre: $strNombre"
        lblNumRecibo.text = "Numero de Recibo: ${numeroRecibo.toString()}"

        reciboNomina.numRecibo = numeroRecibo
        reciboNomina.nombre = strNombre
    }

    private fun calcular(){
        val puesto = rgPuesto.checkedRadioButtonId
        if(txtHorasExtra.text.isEmpty() or txtHorasNormal.text.isEmpty() or (puesto == -1)){
            Toast.makeText(this.applicationContext, "Datos Requeridos", Toast.LENGTH_SHORT).show()
            return
        }

        when (puesto) {
            rdbPuesto1.id -> {
                reciboNomina.puesto = 1
            }
            rdbPuesto2.id -> {
                reciboNomina.puesto = 2
            }
            rdbPuesto3.id -> {
                reciboNomina.puesto = 3
            }
        }


        reciboNomina.horasTrabExtras = txtHorasExtra.text.toString().toDouble()
        reciboNomina.horasTrabNormal = txtHorasNormal.text.toString().toDouble()

        val subtotal = String.format("%.3f",reciboNomina.calcularSubtotal())
        val impuesto = String.format("%.3f",reciboNomina.calcularImpuesto())
        val total = String.format("%.3f", reciboNomina.calcularTotal())
        lblSubtotal.text = "Subtotal: $subtotal MXN"
        lblImpuesto.text = "Impuesto: $impuesto MXN"
        lblTotal.text = "Total a pagar: $total MXN"

    }

    private fun limpiar(){
        rgPuesto.clearCheck()
        txtHorasExtra.text.clear()
        txtHorasNormal.text.clear()
        lblSubtotal.text = "Subtotal"
        lblImpuesto.text = "Impuesto"
        lblTotal.text = "Total a Pagar"
        txtHorasNormal.requestFocus()
    }

    private fun regresar(){
        val confirmar = AlertDialog.Builder(this)
        confirmar.setTitle("Nomina")
        confirmar.setMessage("¿ Desea Regresar ?")
        confirmar.setPositiveButton("Confirmar"){_,_->finish()}
        confirmar.setNegativeButton("Cancelar"){_,_->}.show()
    }
}