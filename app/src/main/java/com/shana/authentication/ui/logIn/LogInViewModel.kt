package com.shana.authentication.ui.logIn

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.shana.authentication.base.BaseViewModel
import com.shana.authentication.data.remoteDataSource.Resource
import com.shana.authentication.data.remoteDataSource.remoteData.LoginResponse
import com.shana.authentication.data.repository.AuthRepository
import com.shana.authentication.isPassWordValid
import com.shana.authentication.isUserNameValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

    @Bindable
    val userName = MutableLiveData<String>()

    @Bindable
    val passWord = MutableLiveData<String>()

    private val _isProgressBarVisible = MutableLiveData<Boolean>()
    val isProgressBarVisible: LiveData<Boolean>
        get() = _isProgressBarVisible

    val isDataValid = Transformations.map(userName) { username1 ->
        Transformations.map(passWord) { passWord1 ->
            isPassWordValid(passWord1) && isUserNameValid(username1)
        }

    }


    private val _logInResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val logInResponse: LiveData<Resource<LoginResponse>>
        get() = _logInResponse

    val accessToken = authRepository.accessToken

    init {
        _isProgressBarVisible.value = false
    }

    fun logIn() = viewModelScope.launch {
        _isProgressBarVisible.value = true
        _logInResponse.value = authRepository.login(userName.value!!, passWord.value!!)
        _isProgressBarVisible.value = false
    }

    fun saveAuthToken(token:String)= viewModelScope.launch{
        authRepository.saveAuthToken(token)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}