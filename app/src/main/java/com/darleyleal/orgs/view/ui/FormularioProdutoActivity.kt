package com.darleyleal.orgs.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.darleyleal.orgs.databinding.ActivityFormularioProdutoBinding
import com.darleyleal.orgs.model.Produto
import com.darleyleal.orgs.services.dao.ProdutosDAO
import com.darleyleal.orgs.view.coil.CoilMethods.Companion.carregarImagem
import com.darleyleal.orgs.view.dialog.FormularioImagemDialog

class FormularioProdutoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioProdutoBinding
    private var url: String? = null
    private val dao = ProdutosDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Cadastrar novo produto"

        configurarBotaoSalvarDados()

        binding.imageInput.setOnClickListener {
            val dialog = FormularioImagemDialog(this)
            dialog.mostrar(url) { imagem ->
                url = imagem
                binding.imageInput.carregarImagem(url, this)
            }
        }
    }

    private fun configurarBotaoSalvarDados() {
        binding.activityFormularioProdutoButtonSalvar.setOnClickListener {
            criarNovosProdutos()
        }
    }

    private fun criarNovosProdutos() {
        val nome = binding.editUrlImagem.text.toString().trim()
        val descricao = binding.activityFormularioProdutoEditDescricao.text.toString().trim()
        val valor = binding.activityFormularioProdutoEditValor.text.toString().trim()
        val imagem = url

        if (nome.isEmpty() || descricao.isEmpty() || valor.isEmpty()) {
            Toast.makeText(this, "Os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
        } else {
            val produto = Produto(
                nome,
                descricao,
                valor.toDouble(),
                imagem
            )
            dao.adicionar(produto)
            finish()
        }
    }
}