package com.rawtooth.yourride.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rawtooth.yourride.adapter.Adapter
import com.rawtooth.yourride.databinding.FragmentMainBinding
import com.rawtooth.yourride.listMain


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    lateinit var recyclerAdapter: Adapter
    lateinit var title: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragtime", "frag created")
        binding = FragmentMainBinding.inflate(layoutInflater)
        setCall()
        title = arguments?.getString("KEY_TITLE") ?: "Null"
        return binding.root
    }

    private fun setCall() {
        Log.d("Fragtime", "adapter called")
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = Adapter(requireContext())
        binding.countryListRecyclerview.adapter = recyclerAdapter
        recyclerAdapter.setRideList(listMain)
        recyclerAdapter.notifyDataSetChanged()
    }

}

