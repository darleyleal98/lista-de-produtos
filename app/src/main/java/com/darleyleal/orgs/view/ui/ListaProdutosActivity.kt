package com.darleyleal.orgs.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.darleyleal.orgs.view.adapter.ListaProdutosAdapter
import com.darleyleal.orgs.services.dao.ProdutosDAO
import com.darleyleal.orgs.databinding.ActivityListaProdutosBinding

class ListaProdutosActivity : AppCompatActivity() {
    private val dao = ProdutosDAO()
    private lateinit var binding: ActivityListaProdutosBinding
    private val adapter = ListaProdutosAdapter(this, dao.listarProdutos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarRecyclerView()
        configurarFAB()

        adapter.onItemClick = {
            val intent = Intent(this, DescricaoItemFormularioActivity::class.java)
            intent.putExtra("produtoSelecionado", it)
            startActivity(intent)

            adapter.atualizarProdutos(dao.listarProdutos())
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.atualizarProdutos(dao.listarProdutos())
    }

    override fun onRestart() {
        super.onRestart()
        adapter.atualizarProdutos(dao.listarProdutos())
    }

    override fun onPause() {
        super.onPause()
        adapter.atualizarProdutos(dao.listarProdutos())
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.atualizarProdutos(dao.listarProdutos())
    }

    override fun onStop() {
        super.onStop()
        adapter.atualizarProdutos(dao.listarProdutos())
    }


    private fun configurarRecyclerView() {
        binding.activityListaProdutosRecyclerItems.adapter = adapter
    }

    private fun configurarFAB() {
        binding.activityListaProdutosFloatingActionButton.setOnClickListener {
            startActivity(Intent(this, FormularioProdutoActivity::class.java))
        }
    }
}