package com.rawtooth.yourride.reposiotry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rawtooth.yourride.model.ridemodel.RidesModel
import com.rawtooth.yourride.model.ridemodel.RidesModelItem
import com.rawtooth.yourride.model.usermodel.User
import com.rawtooth.yourride.network.MyApi

class RidesRepo( val rides:MyApi) {
    private val ridesLiveData=MutableLiveData<RidesModel>()
    val ridess:LiveData<RidesModel> get() = ridesLiveData
    private val userLiveData= MutableLiveData<User>()
    val userData:LiveData<User> get() = userLiveData

    suspend fun getRides(){
        val result=rides.getRides()
        if(result.body() !=null){
            ridesLiveData.postValue(result.body())
        }
    }
    suspend fun getUser(){
        val result=rides.getUser()
        if(result.body() !=null){
            userLiveData.postValue(result.body())
        }
    }
}