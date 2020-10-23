package com.example.moneymanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneymanager.model.PostInfo
import com.example.moneymanager.repository.RetrofitRepository
import com.example.moneymanager.repository.CreatePost

class RetrofitViewModel (retrofitRepository: RetrofitRepository) : ViewModel(){
    var retrofitRepository: RetrofitRepository = retrofitRepository

    fun fetchPostFromRepository() : LiveData<ArrayList<PostInfo>>{
        return retrofitRepository.fetchPostInfoList()
    }

    fun createPost(title : String, subtitle : String) :LiveData<CreatePost> {
        return retrofitRepository.createPost(title,subtitle)
    }

    fun detatch(){
        retrofitRepository.detatch()
    }
}