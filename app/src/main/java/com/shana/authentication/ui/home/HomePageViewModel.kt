package com.shana.authentication.ui.home

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shana.authentication.Event
import com.shana.authentication.base.BaseViewModel
import com.shana.authentication.data.domainData.Professor
import com.shana.authentication.data.remoteDataSource.Resource
import com.shana.authentication.data.remoteDataSource.remoteData.ProfessorListResponse
import com.shana.authentication.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel(userRepository) {

    private val _professors = MutableLiveData<Resource<List<Professor>>>(Resource.Start)
    val professors: LiveData<Resource<List<Professor>>>
        get() = _professors

    private val _textString = MutableLiveData<String>()
    val textString : LiveData<String>
    get() = _textString

    private val _apiError: MutableLiveData<Event<Resource.Failure>> = MutableLiveData()
    val apiError: MutableLiveData<Event<Resource.Failure>>
        get() = _apiError


    fun getProfessorsInfo() = viewModelScope.launch {
        _professors.value = Resource.Loading
        _professors.value = userRepository.getProfessor()
        when (val result = _professors.value) {
            is Resource.Success -> {
                _textString.value = result.value.toString()
            }
            is Resource.Failure -> {
                _apiError.value = Event(result)
            }
            else->{}
        }
    }

    init {
        getProfessorsInfo()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}