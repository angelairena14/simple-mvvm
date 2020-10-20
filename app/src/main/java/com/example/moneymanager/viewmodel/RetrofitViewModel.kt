package com.example.moneymanager.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneymanager.model.PostInfo
import com.example.moneymanager.repository.RetrofitRepository

class RetrofitViewModel (retrofitRepository: RetrofitRepository) : ViewModel(){
    var retrofitRepository: RetrofitRepository = retrofitRepository
    var postInfoLiveData : LiveData<List<PostInfo>> = MutableLiveData()

    init {
        fetchPostFromRepository()
    }

    fun fetchPostFromRepository(){
        postInfoLiveData = retrofitRepository.fetchPostInfoList()
    }

    fun detatch(){
        retrofitRepository.detatch()
    }
}