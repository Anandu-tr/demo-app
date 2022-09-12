package com.example.demo_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
    }

    private fun setObserver() {
        viewModel.demoData.observe(this){
            when(it.status){
                Status.LOADING -> {

                }
                Status.SUCCESS -> {

                }
                Status.ERROR -> {

                }
            }
        }
    }
}