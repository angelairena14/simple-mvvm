package com.example.moneymanager.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moneymanager.utils.RxBus
import com.example.moneymanager.utils.busmodel.ApiBus
import com.example.moneymanager.utils.busmodel.ConnectionBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity : AppCompatActivity() {
    var disposables = Disposables.empty()
    var disposablesConnection = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposables = RxBus.listen(ApiBus::class.java)
        .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showToast(it.code.toString())
            }, {})

        disposablesConnection = RxBus.listen(ConnectionBus::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (!it.isOnline) showToast("please check your internet connection")
            }, {})
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposables.isDisposed) disposables.dispose()
        if (!disposablesConnection.isDisposed) disposables.dispose()
    }

    fun showToast(message : String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    fun isOnline(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}