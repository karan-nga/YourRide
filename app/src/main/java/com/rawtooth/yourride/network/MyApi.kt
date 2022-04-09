package com.rawtooth.yourride.network

import com.rawtooth.yourride.model.ridemodel.RidesModel
import com.rawtooth.yourride.model.usermodel.User
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    companion object{
        const val BASE_URL = "https://assessment.api.vweb.app/"
    }
    @GET("user")
    suspend fun getUser():Response<User>
    @GET("rides")
   suspend fun getRides():Response<RidesModel>
}