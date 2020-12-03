package com.example.mounikadhanraj.digitalturbinetest.repository

import com.example.mounikadhanraj.digitalturbinetest.api.APIClient

class AdRepositoryImpl:AdRepository {

    private val apiService = APIClient.apiService

    override suspend fun getAdResponse() = apiService.getAdResponse()
}