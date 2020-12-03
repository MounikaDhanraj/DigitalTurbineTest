package com.example.mounikadhanraj.digitalturbinetest.api


import com.example.mounikadhanraj.digitalturbinetest.model.AdsList
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("getAds?id=236&password=OVUJ1DJN&siteId=10777" +
            "&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10")
    suspend fun  getAdResponse(): AdsList
}