package com.darleyleal.orgs.view.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.darleyleal.orgs.databinding.FormularioImagemBinding
import com.darleyleal.orgs.view.coil.CoilMethods.Companion.carregarImagem

class FormularioImagemDialog(
    private val context: Context,
) {
    private var url: String? = null
    fun mostrar(urlPadrao: String? = null, imagemCarregada: (imagem: String) -> Unit) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                imageFormulario.carregarImagem(it, context)
                editUrlImagem.setText(it)
            }

            buttonCarregar.setOnClickListener {
                url = editUrlImagem.text.toString()
                imageFormulario.carregarImagem(url, context)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url?.let {
                        imagemCarregada(it)
                    }
                }
                .setNegativeButton("Cancelar") { _, _ ->
                }.show()
        }
    }
}