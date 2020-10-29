package com.example.moneymanager.view

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.example.moneymanager.R
import com.example.moneymanager.base.BaseActivity
import com.example.moneymanager.databinding.ActivityMainBinding
import com.example.moneymanager.model.PostInfo
import com.example.moneymanager.repository.RetrofitState
import com.example.moneymanager.repository.CreatePost
import com.example.moneymanager.utils.GeneralObserver
import com.example.moneymanager.utils.RxBus
import com.example.moneymanager.utils.busmodel.ConnectionBus
import com.example.moneymanager.view.adapter.PostListAdapter
import com.example.moneymanager.viewmodel.RetrofitViewModel
import com.example.moneymanager.viewmodel.RetrofitViewModelFactory
import com.google.gson.Gson

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var retrofitViewModel: RetrofitViewModel
    lateinit var postListAdapter: PostListAdapter
    private var skeletonScreen: RecyclerViewSkeletonScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initSkeleton()
        initViewModel()
        setAdapter()
        fetchRetroInfo()
        initListener()
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
        retrofitViewModel.fetchPostFromRepository().observe(this, GeneralObserver(::setAdapter))
        handlingState()
    }

    fun setAdapter (model : ArrayList<PostInfo>){
        postListAdapter.setAdapterList(model)
    }

    fun initListener(){
        binding.btnGetToken.setOnClickListener {
            retrofitViewModel.createPost("POST TITLE","POS SUBTITLE").observe(
                this,
                GeneralObserver(::resultPost)
            )
            handlingState()
        }
    }

    fun resultPost(createPost : CreatePost){
        Log.i("createpost",Gson().toJson(createPost))
    }

    fun handleState(it : RetrofitState){
        when(it) {
            is RetrofitState.Error -> {
                isLoading(true)

            }
            is RetrofitState.Success -> {
                isLoading(false)
            }

            is RetrofitState.Loading -> isLoading(it.state)
        }
    }

    fun isLoading(state: Boolean){
        if (state){
            skeletonScreen?.show()
        } else {
            skeletonScreen?.hide()
        }
    }

    fun initSkeleton(){
        initAdapter()
        Log.i("called_","skeleton called")
        skeletonScreen = Skeleton.bind(binding.rvPostList)
            .adapter(postListAdapter)
            .shimmer(true)
            .frozen(false)
            .color(R.color.whitePure)
            .duration(1500)
            .count(10)
            .load(R.layout.item_post_skeleton)
            .show()
        skeletonScreen?.show()
    }

    fun handlingState(){
        retrofitViewModel.retrofitRepository.getUiState().observer(this, Observer {
            handleState(it)
        })
    }
}
