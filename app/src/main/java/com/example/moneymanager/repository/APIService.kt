package com.example.moneymanager.repository

import com.example.moneymanager.model.PostInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    fun makeRequest(): Observable<ArrayList<PostInfo>>
}