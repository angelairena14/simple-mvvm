package com.example.moneymanager.di

import com.example.moneymanager.AppModule
import com.example.moneymanager.repository.RetrofitRepository
import com.example.moneymanager.view.MainActivity
import com.example.moneymanager.viewmodel.RetrofitViewModel
import com.example.moneymanager.viewmodel.RetrofitViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ApiModule::class])
interface APIComponent {
    fun inject(retrofitRepository: RetrofitRepository)
    fun inject(retrofitViewModel: RetrofitViewModel)
    fun inject(retrofitViewModelFactory: RetrofitViewModelFactory)
}