package com.rawtooth.yourride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rawtooth.yourride.reposiotry.RidesRepo

class MainViewModelFactory(private val ridesDetail: RidesRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(ridesDetail) as T
    }
}