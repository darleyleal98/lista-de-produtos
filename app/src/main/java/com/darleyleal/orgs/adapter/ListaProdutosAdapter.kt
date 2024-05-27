package com.darleyleal.orgs.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darleyleal.orgs.R
import com.darleyleal.orgs.data.model.Produto
import com.darleyleal.orgs.databinding.ActivityItemsBinding

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(binding: ActivityItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        private val nome = binding.activityItemsTextNome
        private val descricao = binding.activityItemsTextDescricao
        private val valor = binding.activityItemsTextValor

        fun vincular(produto: Produto) {
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = produto.valor.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityItemsBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vincular(produtos[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun atualizarProdutos(listarProdutos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(listarProdutos)
        notifyDataSetChanged()
    }
}