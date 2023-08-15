package com.k4tr1n4.marvel.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.skydoves.whatif.whatIfNotNullOrEmpty

object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text.whatIfNotNullOrEmpty {
            Toast.makeText(view.context, it, Toast.LENGTH_LONG).show()
        }
    }
}