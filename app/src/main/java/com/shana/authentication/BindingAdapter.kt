package com.shana.authentication

import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.shana.authentication.data.remoteDataSource.Resource

@BindingAdapter("visibility")
fun visible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("progressBarVisibility")
fun progressBarVisible(progressBar: ProgressBar, status: Resource<*>) {
    progressBar.visibility = if (status is Resource.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["editTextError", "errorMassage"],requireAll = false)
fun EditText.editTextError(isValid: Boolean, errorMassage:String) {
    if (!isValid) {
        this.error = errorMassage
    } else {
        this.error = null
    }
}



@BindingAdapter("enable")
fun enable(view: View, isEnabled: Boolean) {
    view.isEnabled = isEnabled
    view.alpha = if (isEnabled) 1f else 0.5f
}