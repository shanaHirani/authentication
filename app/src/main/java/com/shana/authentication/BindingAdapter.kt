package com.shana.authentication

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun visible(view: View, isVisible:Boolean){
    view.visibility = if(isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("enable")
fun enable(view: View, isEnabled:Boolean){
    view.isEnabled = isEnabled
    view.alpha = if(isEnabled) 1f else 0.5f
}