package com.shana.authentication.ui.home

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
    BaseViewModel() {

    private val _professors = MutableLiveData<Resource<List<Professor>>>(Resource.Start)
    val professors: LiveData<Resource<List<Professor>>>
        get() = _professors


    private fun getProfessors() = viewModelScope.launch {
        _professors.value = Resource.Loading
        _professors.value = userRepository.getProfessor()
    }

    init {
        getProfessors()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}