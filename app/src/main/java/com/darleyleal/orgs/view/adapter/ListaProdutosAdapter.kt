package com.darleyleal.orgs.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.darleyleal.orgs.databinding.ActivityItemsBinding
import com.darleyleal.orgs.model.Produto
import com.darleyleal.orgs.services.dao.ProdutosDAO
import com.darleyleal.orgs.view.coil.CoilMethods.Companion.carregarImagem
import com.darleyleal.orgs.view.currency.Moeda

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>,
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()
    var onItemClick: ((Produto) -> Unit)? = null
    private val dao = ProdutosDAO()

    class ViewHolder(private val binding: ActivityItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val nome = binding.activityItemsTextNome
        private val descricao = binding.activityItemsTextDescricao
        private val valor = binding.activityItemsTextValor

        fun vincular(produto: Produto, context: Context) {
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = Moeda.formatarParaPadraoBrasileiro(produto.valor)

            val params = binding.imageView.layoutParams as ConstraintLayout.LayoutParams

            /**Ajusta o card view caso o usuário não passe uma URL da imagem por parâmetro**/
            if (produto.imagem == null) {
                View.GONE
                params.matchConstraintPercentWidth = 0.0f
            }

            binding.imageView.carregarImagem(produto.imagem, context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityItemsBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.vincular(produtos[position], context)

        val item = produtos[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }

        holder.itemView.setOnLongClickListener {
            removerProduto(position, holder)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removerProduto(position: Int, holder: ViewHolder) : Boolean{
        AlertDialog.Builder(context)
            .setView(holder.itemView)
            .setPositiveButton("Confirmar") { _, _ ->

            }
            .setNegativeButton("Cancelar") { _, _ ->
            }.show()
        dao.removerProduto(position)
        produtos.removeAt(position)
        Toast.makeText(context, "Produto removido", Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    fun atualizarProdutos(listarProdutos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(listarProdutos)
        notifyDataSetChanged()
    }
}