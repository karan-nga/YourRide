package com.rawtooth.yourride.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObj {
      val retrofit = Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(MyApi.BASE_URL)
          .build()

}