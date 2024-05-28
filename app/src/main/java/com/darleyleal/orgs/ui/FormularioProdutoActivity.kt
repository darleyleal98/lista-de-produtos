package com.darleyleal.orgs.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.darleyleal.orgs.R
import com.darleyleal.orgs.data.dao.ProdutosDAO
import com.darleyleal.orgs.data.model.Produto
import com.darleyleal.orgs.databinding.ActivityFormularioProdutoBinding

class FormularioProdutoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormularioProdutoBinding
    private val dao = ProdutosDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarBotaoSalvarDados()
    }


    private fun configurarBotaoSalvarDados() {
        binding.activityFormularioProdutoButtonSalvar.setOnClickListener {
            criarNovosProdutos()
        }
    }

    private fun criarNovosProdutos() {
        val nome = binding.activityFormularioProdutoEditNome.text.toString().trim()
        val descricao = binding.activityFormularioProdutoEditDescricao.text.toString().trim()
        val valor = binding.activityFormularioProdutoEditValor.text.toString().trim()

        if (nome.isEmpty() || descricao.isEmpty() || valor.isEmpty()) {
            Toast.makeText(this, "Os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
        } else {
            val produto = Produto(nome, descricao, valor.toBigDecimal())
            dao.adicionar(produto)
            finish()
        }
    }
}