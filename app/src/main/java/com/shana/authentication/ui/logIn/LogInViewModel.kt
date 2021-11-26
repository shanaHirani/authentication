package com.shana.authentication.ui.logIn

import android.content.Context
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shana.authentication.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(@ApplicationContext private val context: Context): BaseViewModel() {

    @Bindable
    val userName = MutableLiveData<String>()

    @Bindable
    val passWord = MutableLiveData<String>()

    fun logIn(){
        Toast.makeText(context,"user: "+userName.value+" password: "+passWord.value, Toast.LENGTH_LONG).show()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}