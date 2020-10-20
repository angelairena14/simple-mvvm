package com.example.moneymanager.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneymanager.BaseApplication
import com.example.moneymanager.di.APIComponent
import com.example.moneymanager.model.PostInfo
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    var postInfoMutableList : MutableLiveData<List<PostInfo>> = MutableLiveData()
    @Inject
    lateinit var retrofit : Retrofit
    var disposables = Disposables.empty()
    init {
        var apiComponent : APIComponent = BaseApplication().getMyComponent()
        apiComponent.inject(this)
    }

    fun fetchPostInfoList() : LiveData<List<PostInfo>> {
        var apiService : APIService = retrofit.create(APIService::class.java)
        disposables = apiService
            .makeRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("data_is_1","data_")
                var postInfoList = it
                postInfoMutableList.value = postInfoList
            },{
                Log.i("data_is_2","${Gson().toJson(it)}")
            })
        return postInfoMutableList
    }

    fun detatch(){
        if (!disposables.isDisposed) disposables.dispose()
    }
}