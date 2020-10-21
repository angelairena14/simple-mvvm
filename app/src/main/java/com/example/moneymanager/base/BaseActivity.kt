package com.example.moneymanager.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moneymanager.utils.RxBus
import com.example.moneymanager.utils.busmodel.ApiBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity : AppCompatActivity() {
    var disposables = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposables = RxBus.listen(ApiBus::class.java)
        .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                showToast(it.code.toString())
            }, {})
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun showToast(message : String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}