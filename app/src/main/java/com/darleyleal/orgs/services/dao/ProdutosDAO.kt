package com.darleyleal.orgs.services.dao

import com.darleyleal.orgs.model.Produto

class ProdutosDAO {
    fun adicionar(produto: Produto) {
        produtos.add(produto)
    }

    fun listarProdutos(): List<Produto> {
        return produtos.toList()
    }

    fun removerProduto(index: Int) {
        produtos.removeAt(index)
    }

    companion object {
        val produtos = mutableListOf<Produto>()
    }
}