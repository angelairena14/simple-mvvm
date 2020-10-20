package com.example.moneymanager

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