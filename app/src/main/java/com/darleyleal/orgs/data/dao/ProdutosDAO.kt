package com.darleyleal.orgs.data.dao

import com.darleyleal.orgs.data.model.Produto

class ProdutosDAO {
    fun adicionar(produto: Produto) {
        produtos.add(produto)
    }

    fun listarProdutos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        val produtos = mutableListOf<Produto>()
    }
}