package com.k4tr1n4.marvel.ui.main

import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.k4tr1n4.core.data.repository.MainRepository
import com.k4tr1n4.core.model.Character
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BindingViewModel() {
    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)

    private val marvelFetchIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val marvelListFlow = marvelFetchIndex.flatMapLatest { page ->
        mainRepository.fetchMarvelList(
            page = page,
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { toastMessage = it}
        )
    }

    @get:Bindable
    val marvelList: List<Character>? by marvelListFlow.asBindingProperty(viewModelScope, emptyList())

    init{
        Log.d("Init", "Iniciando mainViewModel")
    }

    @MainThread
    fun fetchNextMarvelList() {
        if (!isLoading) {
            marvelFetchIndex.value++
        }
    }
}