package com.example.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: Repository):ViewModel()
{
    var iid=MutableLiveData<Long>(0)
    fun userExist(email:String,fname:String):LiveData<Int>
    {
        return repository.userExist(email,fname)
    }

    fun getId(email:String):LiveData<Long>
    {
        return repository.getId(email)
    }

    fun getUser(newId:Long):LiveData<Profile>
    {
        return repository.getUser(newId)
    }
    fun updateUser(profile: Profile)
    {
        viewModelScope.launch {
            repository.updateUser(profile)
        }
    }
    fun insertUser(profile: Profile)
    {
        viewModelScope.launch {
            repository.insertUser(profile)
        }
    }
    fun mailExist(email: String): LiveData<Int> {
        return repository.mailExist(email)
    }
}