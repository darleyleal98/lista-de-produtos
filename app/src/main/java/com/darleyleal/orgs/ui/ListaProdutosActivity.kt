package com.darleyleal.orgs.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.darleyleal.orgs.R
import com.darleyleal.orgs.adapter.ListaProdutosAdapter
import com.darleyleal.orgs.data.dao.ProdutosDAO
import com.darleyleal.orgs.databinding.ActivityListaProdutosBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
    }

    override fun onResume() {
        super.onResume()
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