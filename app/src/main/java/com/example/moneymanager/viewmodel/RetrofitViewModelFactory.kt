package com.example.moneymanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneymanager.BaseApplication
import com.example.moneymanager.di.APIComponent
import com.example.moneymanager.repository.RetrofitRepository
import javax.inject.Inject

class RetrofitViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var apiComponent: APIComponent
    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var apiComponent = BaseApplication().getMyComponent()
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)){
            return RetrofitViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}

