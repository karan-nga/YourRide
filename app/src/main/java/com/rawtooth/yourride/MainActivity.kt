package com.rawtooth.yourride

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rawtooth.yourride.adapter.Adapter
import com.rawtooth.yourride.databinding.ActivityMainBinding
import com.rawtooth.yourride.model.ridemodel.RidesModelItem
import com.rawtooth.yourride.model.usermodel.User
import com.rawtooth.yourride.network.MyApi
import com.rawtooth.yourride.network.RetrofitObj
import com.rawtooth.yourride.reposiotry.RidesRepo
import com.rawtooth.yourride.viewmodel.MainViewModel
import com.rawtooth.yourride.viewmodel.MainViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel:MainViewModel
    lateinit var recyclerAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = Adapter(this)
        binding.countryListRecyclerview.adapter =recyclerAdapter
        val apiServices=RetrofitObj.retrofit.create(MyApi::class.java)
        val repo=RidesRepo(apiServices)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(repo)).get(MainViewModel::class.java)

        mainViewModel.ridesLive.observe(this, Observer {

            mainViewModel.commputeDistance()
            if(it!=null){
                recyclerAdapter.setRideList(it)
                recyclerAdapter.notifyDataSetChanged()
            }
            Log.d("code",it.toString())
        })
        mainViewModel.userLive.observe(this) {
            Log.d("code",it.toString())
            binding.mainToolbar.customtoolbarText.text = it.name
            Glide.with(this).load(it.url).apply(RequestOptions.circleCropTransform()).into(binding.mainToolbar.customtoolbarImage)
        }

    }


}