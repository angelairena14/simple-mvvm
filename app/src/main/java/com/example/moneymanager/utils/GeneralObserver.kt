package com.example.moneymanager.utils

import androidx.lifecycle.Observer

class GeneralObserver<T>(private val block: (T) -> Unit) : Observer<T> {
    override fun onChanged(data: T?) {
        data?.let(block)
    }
}