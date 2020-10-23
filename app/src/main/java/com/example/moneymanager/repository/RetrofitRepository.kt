package com.example.moneymanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneymanager.BaseApplication
import com.example.moneymanager.di.APIComponent
import com.example.moneymanager.model.PostInfo
import com.example.moneymanager.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    private var state : SingleLiveEvent<RetrofitState> = SingleLiveEvent()
    var postInfoMutableList : MutableLiveData<ArrayList<PostInfo>> = MutableLiveData()
    var createPostList : MutableLiveData<CreatePost> = MutableLiveData()
    @Inject
    lateinit var retrofit : Retrofit
    var apiService : APIService
    var disposables = Disposables.empty()

    init {
        var apiComponent : APIComponent = BaseApplication().getMyComponent()
        apiComponent.inject(this)
        apiService = retrofit.create(APIService::class.java)
    }

    fun fetchPostInfoList() : LiveData<ArrayList<PostInfo>> {
        state.value = RetrofitState.Loading(true)
        disposables = apiService
            .makeRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var postInfoList = it
                postInfoMutableList.value = postInfoList
                state.value = RetrofitState.Success("success")
            },{
                state.value = RetrofitState.Error("failed")
            })
        return postInfoMutableList
    }

    fun createPost(title :String, subtitle : String) : LiveData<CreatePost> {
        createPostList.value = CreatePost(title,subtitle)
        return createPostList
    }

    fun detatch(){
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun getUiState() = state
}

sealed class RetrofitState {
    data class Loading(var state : Boolean = false) : RetrofitState()
    data class Error(var message : String) : RetrofitState()
    data class Success(var message : String) : RetrofitState()
}

data class CreatePost(var title : String, var subtitle: String)