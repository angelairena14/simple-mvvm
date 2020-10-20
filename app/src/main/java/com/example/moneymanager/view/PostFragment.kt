package com.example.moneymanager.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.moneymanager.R
import com.example.moneymanager.databinding.FragmentPostBinding
import com.example.moneymanager.model.PostInfo
import com.example.moneymanager.view.adapter.PostListAdapter
import com.example.moneymanager.viewmodel.RetrofitViewModel
import com.example.moneymanager.viewmodel.RetrofitViewModelFactory
import kotlinx.android.synthetic.main.fragment_post.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PostFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var retrofitViewModel: RetrofitViewModel
    lateinit var layoutPostBinding: FragmentPostBinding
    var fragmentView : View? = null
    lateinit var postListAdapter: PostListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layoutPostBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_post,container,false)
        fragmentView = layoutPostBinding.root
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setAdapter(view)
        fetchRetroInfo()
    }

    override fun onDetach() {
        super.onDetach()
        retrofitViewModel.detatch()
    }

    fun initViewModel(){
        var retrofitViewModelFactory = RetrofitViewModelFactory()
        retrofitViewModel = ViewModelProviders.of(this,retrofitViewModelFactory).get(RetrofitViewModel::class.java)
    }

    fun initAdapter(){
        postListAdapter = PostListAdapter(requireContext())
    }

    fun setAdapter(view: View){
        view.rv_post_list.adapter = postListAdapter
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
