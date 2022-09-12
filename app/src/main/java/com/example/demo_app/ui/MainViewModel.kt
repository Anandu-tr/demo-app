package com.example.demo_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo_app.data.model.DemoResponse
import com.example.demo_app.data.repository.Repository
import com.example.demo_app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _demoData: MutableLiveData<Resource<DemoResponse>> = MutableLiveData()
    val demoData: LiveData<Resource<DemoResponse>> = _demoData

    fun getData(){
        _demoData.value = Resource.loading()
        viewModelScope.launch {
            _demoData.postValue(repository.getData())
        }
    }
}