package com.k4tr1n4.marvel.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.k4tr1n4.marvel.R
import com.k4tr1n4.marvel.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    internal val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            vm = viewModel
        }
    }
}