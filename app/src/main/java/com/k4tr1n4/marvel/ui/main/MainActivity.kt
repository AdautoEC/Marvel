package com.k4tr1n4.marvel.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.k4tr1n4.marvel.R
import com.k4tr1n4.marvel.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @get:VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            vm = viewModel
            adapter = MarvelAdapter()
        }
    }
}