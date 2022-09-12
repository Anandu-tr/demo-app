package com.example.demo_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_app.databinding.ActivityMainBinding
import com.example.demo_app.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getData()
        setObserver()
        binding.refresh.setOnRefreshListener { viewModel.getData() }
        binding.btRetry.setOnClickListener {
            binding.groupError.isVisible = false
            viewModel.getData()
        }
    }

    private fun setObserver() {
        viewModel.demoData.observe(this){
            when(it.status){
                Status.LOADING -> {
                    if (!binding.groupSuccess.isVisible)
                        binding.progressBar.isVisible = true
                    else
                        binding.refresh.isRefreshing = true
                }
                Status.SUCCESS -> it.data?.let { data ->
                    hideLoader()
                    binding.groupSuccess.isVisible = true
                    binding.toolbar.title = data.title
                    val rows = data.rows.filter { row-> !row.title?.trim().isNullOrBlank() }
                    binding.rvData.adapter = DemoListAdapter(this, rows)
                    val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
                    binding.rvData.addItemDecoration(divider)
                }
                Status.ERROR -> {
                    hideLoader()
                    binding.groupSuccess.isVisible = false
                    binding.groupError.isVisible = true
                }
            }
        }
    }

    private fun hideLoader(){
        binding.progressBar.isVisible = false
        binding.refresh.isRefreshing = false
    }
}