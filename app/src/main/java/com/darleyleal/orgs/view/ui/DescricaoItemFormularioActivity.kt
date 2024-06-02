package com.darleyleal.orgs.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.darleyleal.orgs.databinding.DescricaoItemFormularioBinding
import com.darleyleal.orgs.model.Produto
import com.darleyleal.orgs.view.currency.Moeda

class DescricaoItemFormularioActivity : AppCompatActivity() {
    private lateinit var binding: DescricaoItemFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = DescricaoItemFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dados =
            intent.getParcelableExtra<Produto>("produtoSelecionado")

        binding.textNomeProduto.text =  dados?.nome
        binding.textDescricaoProduto.text =  dados?.descricao
        binding.buttonValorProduto.text =  Moeda.formatarParaPadraoBrasileiro(dados?.valor)
        binding.imagemProduto.load(dados?.imagem)
    }
}