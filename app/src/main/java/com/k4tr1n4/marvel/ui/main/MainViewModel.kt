package com.k4tr1n4.marvel.ui.main

import androidx.databinding.Bindable
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val repository: MainRepository
) : BindingViewModel() {
    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
}