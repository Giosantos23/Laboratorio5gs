package com.example.laboratorio6

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface servicioApi {
    @GET
    fun getUrbanAreas(@Url url:String):Response<areasUrban>


}