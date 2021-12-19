package com.shana.authentication.ui.logIn

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.shana.authentication.Event
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
    BaseViewModel(authRepository) {

    @Bindable
    val userName = MutableLiveData<String>()

    @Bindable
    val passWord = MutableLiveData<String>()

    val isDataValid = Transformations.map(userName) { username1 ->
        Transformations.map(passWord) { passWord1 ->
            isPassWordValid(passWord1) && isUserNameValid(username1)
        }

    }


    private val _logInResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData(Resource.Start)
    val logInResponse: LiveData<Resource<LoginResponse>>
        get() = _logInResponse


    private val _navigateToHomeFragment: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigateToHomeFragment: LiveData<Event<Boolean>>
        get() = _navigateToHomeFragment

    private val _apiError: MutableLiveData<Event<Resource.Failure>> = MutableLiveData()
    val apiError: MutableLiveData<Event<Resource.Failure>>
        get() = _apiError

    val accessToken = authRepository.accessToken


    fun logIn() = viewModelScope.launch {
        _logInResponse.value = Resource.Loading
        _logInResponse.value = authRepository.login(userName.value!!, passWord.value!!)
        when (val result = _logInResponse.value) {
            is Resource.Success -> {
                saveAuthToken(result.value.token)
                _navigateToHomeFragment.value = Event(true)
                passWord.value = ""
            }
            is Resource.Failure -> {
                _apiError.value = Event(result)
            }
            else -> {}
        }

    }

    private fun saveAuthToken(token: String) = viewModelScope.launch {
        authRepository.saveAuthToken(token)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}