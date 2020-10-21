package com.example.moneymanager.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.moneymanager.BaseApplication
import com.example.moneymanager.repository.Constants
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.lang.Exception

class HeaderInterceptor (var context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var requestBuilder = request.newBuilder()
            .addHeader("Authorization","Bearer "+ SharedPreferencesUtil.getToken(context))
        request = requestBuilder.build()

        var response= chain.proceed(request)
        var stringData = ""
        response.body()?.string()?.let { json ->
            stringData = json
        }
        val contentType = response.body()?.contentType()
        val body = ResponseBody.create(contentType,stringData)
        return response.newBuilder().body(body).build()
    }
}