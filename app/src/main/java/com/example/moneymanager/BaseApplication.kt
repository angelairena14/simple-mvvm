package com.example.moneymanager

import android.app.Application
import android.content.Context
import com.example.moneymanager.di.APIComponent
import com.example.moneymanager.di.ApiModule
import com.example.moneymanager.di.DaggerAPIComponent
import com.example.moneymanager.repository.APIURL.Companion.BASE_URL
import javax.inject.Inject

class BaseApplication : Application(){
    var ctx : Context? = null

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }

    fun getMyComponent(): APIComponent {
        return initDaggerComponent()
    }

    fun initDaggerComponent() : APIComponent{
        return DaggerAPIComponent
            .builder()
            .apiModule(ApiModule(BASE_URL))
            .build()
    }
}