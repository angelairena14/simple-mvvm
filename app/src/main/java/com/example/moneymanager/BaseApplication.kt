package com.example.moneymanager

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.moneymanager.di.APIComponent
import com.example.moneymanager.di.ApiModule
import com.example.moneymanager.di.DaggerAPIComponent
import com.example.moneymanager.repository.Constants.Companion.BASE_URL
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class BaseApplication : Application(){
    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }

    fun getMyComponent(): APIComponent {
        return initDaggerComponent()
    }

    fun getContext(): Context? {
        var context : Context? = null
        context = ctx
        return context
    }

    fun initDaggerComponent() : APIComponent{
        return DaggerAPIComponent
            .builder()
            .apiModule(ApiModule(BASE_URL))
            .build()
    }
}