package com.example.nomina

class ReciboNomina(private var numRecibo: Int, private var nombre: String, private var horasTrabNormal: Float, private var horasTrabExtras: Float, private var puesto: Int, private var impuestoPorc: Float) {

    fun calcularSubtotal(): Double {

        var pagoBase = 200.00

        when (this.puesto) {
            1 -> {
                pagoBase *= 1.20
            }
            2 -> {
                pagoBase *= 1.50
            }
            3 -> {
                pagoBase *= 2
            }
        }

        val pagoExtras = pagoBase * 2


        return (pagoBase * this.horasTrabNormal) + (pagoExtras * this.horasTrabExtras)

    }

    fun calcularImpuesto(): Double {
        val subtotal = this.calcularSubtotal()
        return subtotal * this.impuestoPorc
    }

    fun calcularTotal(): Double {
        val subtotal = this.calcularSubtotal()
        val impuesto = this.calcularImpuesto()
        return subtotal - impuesto


    }
}