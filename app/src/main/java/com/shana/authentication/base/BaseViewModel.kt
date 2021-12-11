package com.shana.authentication.base

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shana.authentication.Event
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


abstract class BaseViewModel(
    private val baseRepository: BaseRepository
): ViewModel(), Observable {

    private val _navigateToLogInFragment = MutableLiveData<Event<Boolean>>()
    val navigateToLogInFragment:LiveData<Event<Boolean>>
    get() = _navigateToLogInFragment


    fun logOut()= viewModelScope.launch{
      baseRepository.logOut()
      _navigateToLogInFragment.value = Event(true)
  }

}