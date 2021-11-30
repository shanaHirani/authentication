package com.shana.authentication.ui.logIn

import android.content.Context
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shana.authentication.base.BaseViewModel
import com.shana.authentication.remoteDataSource.Resource
import com.shana.authentication.remoteDataSource.remoteData.LoginResponse
import com.shana.authentication.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {

    @Bindable
    val userName = MutableLiveData<String>()

    @Bindable
    val passWord = MutableLiveData<String>()

    private val _logInResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val logInResponse : LiveData<Resource<LoginResponse>>
        get() = _logInResponse

    fun logIn() = viewModelScope.launch{
        _logInResponse.value = authRepository.login(userName.value!!,passWord.value!!)
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}