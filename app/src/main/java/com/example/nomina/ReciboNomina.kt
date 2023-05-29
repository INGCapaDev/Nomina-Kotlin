package com.example.nomina

class ReciboNomina( var numRecibo: Int,  var nombre: String,  var horasTrabNormal: Double,  var horasTrabExtras: Double,  var puesto: Int,  var impuestoPorc: Double) {

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