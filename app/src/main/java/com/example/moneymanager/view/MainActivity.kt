package com.example.moneymanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moneymanager.R
import com.example.moneymanager.databinding.ActivityMainBinding
import com.example.moneymanager.model.PostInfo
import com.example.moneymanager.view.adapter.PostListAdapter
import com.example.moneymanager.viewmodel.RetrofitViewModel
import com.example.moneymanager.viewmodel.RetrofitViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var retrofitViewModel: RetrofitViewModel
    lateinit var postListAdapter: PostListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initViewModel()
        initAdapter()
        setAdapter()
        fetchRetroInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        retrofitViewModel.detatch()
    }

    fun initViewModel(){
        var retrofitViewModelFactory = RetrofitViewModelFactory()
        retrofitViewModel = ViewModelProviders.of(this,retrofitViewModelFactory).get(RetrofitViewModel::class.java)
    }

    fun initAdapter(){
        postListAdapter = PostListAdapter(this)
    }

    fun setAdapter(){
        binding.rvPostList.adapter = postListAdapter
    }

    fun fetchRetroInfo(){
        retrofitViewModel.postInfoLiveData.observe(this,
            Observer<List<PostInfo>> { t ->
                t?.let {
                    postListAdapter.setAdapterList(t)
                }
            })
    }
}
