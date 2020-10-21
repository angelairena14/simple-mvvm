package com.example.moneymanager

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(baseApplication: BaseApplication){
    var base : BaseApplication = baseApplication
    @Provides
    fun provideBaseApplication() : BaseApplication {
        return base
    }
}