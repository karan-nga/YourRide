package com.rawtooth.yourride

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rawtooth.yourride.adapter.ViewPagerAdapter
import com.rawtooth.yourride.databinding.ActivityMainBinding
import com.rawtooth.yourride.fragments.MainFragment
import com.rawtooth.yourride.model.ridemodel.RidesModelItem
import com.rawtooth.yourride.network.MyApi
import com.rawtooth.yourride.network.RetrofitObj
import com.rawtooth.yourride.reposiotry.RidesRepo
import com.rawtooth.yourride.viewmodel.MainViewModel
import com.rawtooth.yourride.viewmodel.MainViewModelFactory

var listMain: List<RidesModelItem>? = null
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

//    //    lateinit var recyclerAdapter: Adapter
//    lateinit var countryListRecyclerview: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("create","MainActivity")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabs()
//        countryListRecyclerview=findViewById(R.id.countryListRecyclerview)
//        countryListRecyclerview.layoutManager = LinearLayoutManager(this)
//        recyclerAdapter = Adapter(this)
//       countryListRecyclerview.adapter =recyclerAdapter
        val apiServices = RetrofitObj.retrofit.create(MyApi::class.java)
        val repo = RidesRepo(apiServices)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repo)).get(MainViewModel::class.java)
        //user
        mainViewModel.userLive.observe(this) {
            Log.d("code", it.toString())
            binding.mainToolbar.customtoolbarText.text = it.name
            Glide.with(this).load(it.url).apply(RequestOptions.circleCropTransform())
                .into(binding.mainToolbar.customtoolbarImage)
        }
        //Rides


        mainViewModel.ridesLive.observe(this, Observer {

            mainViewModel.commputeDistance()
            if (it != null) {
                listMain = it

            }
            Log.d("code", it.toString())
        })


    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(getMainFragment("Home"), "Home")
        adapter.addFragment(getMainFragment("Upcoming"), "Upcoming")
        adapter.addFragment(getMainFragment("Past"), "Past")

        binding.viewPager.adapter = adapter
        binding.tab.setupWithViewPager(binding.viewPager)

    }

    private fun getMainFragment(title: String): Fragment {
        val bundle = Bundle()
        bundle.putString("KEY_TITLE", title)
        val fragment = MainFragment()
        fragment.arguments = bundle
        return fragment
    }

}