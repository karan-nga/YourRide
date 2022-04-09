package com.rawtooth.yourride.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rawtooth.yourride.model.ridemodel.RidesModel
import com.rawtooth.yourride.model.usermodel.User
import com.rawtooth.yourride.reposiotry.RidesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val ridesDetail:RidesRepo):ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            ridesDetail.getUser()
        }
        viewModelScope.launch(Dispatchers.IO) {
            ridesDetail.getRides()
        }

    }
    val ridesLive:LiveData<RidesModel>
    get() = ridesDetail.ridess
    val userLive:LiveData<User>
    get() = ridesDetail.userData

    fun commputeDistance() {
        for (rides in ridesLive.value!!) {
            rides.findClosest(userLive.value!!.station_code)
        }
        Collections.sort(ridesLive.value!!)

    }

}