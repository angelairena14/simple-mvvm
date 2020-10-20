package com.example.moneymanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moneymanager.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
    }

    fun loadFragment(){
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_main,PostFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
