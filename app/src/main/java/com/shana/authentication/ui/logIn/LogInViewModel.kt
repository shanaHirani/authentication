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

    private val _isDataValid = MutableLiveData<Boolean>()
    val isDataValid: LiveData<Boolean>
        get() = _isDataValid


    private val _logInResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val logInResponse: LiveData<Resource<LoginResponse>>
        get() = _logInResponse

    init {
        _isProgressBarVisible.value = false
        _isDataValid.value = true

    }

    fun logIn() = viewModelScope.launch {
        _isProgressBarVisible.value = true
        _logInResponse.value = authRepository.login(userName.value!!, passWord.value!!)
        _isProgressBarVisible.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}