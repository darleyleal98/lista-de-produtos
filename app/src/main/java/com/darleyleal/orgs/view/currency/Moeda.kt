package com.darleyleal.orgs.view.currency

import java.text.NumberFormat
import java.util.Locale

class Moeda {
    companion object {
        fun formatarParaPadraoBrasileiro(valor: Double?): String {
            val moedaFormatada =
                NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            return moedaFormatada.format(valor)
        }
    }
}