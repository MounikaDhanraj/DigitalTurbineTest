package com.example.mounikadhanraj.digitalturbinetest.repository

import com.example.mounikadhanraj.digitalturbinetest.model.AdsList

interface AdRepository {

    suspend fun getAdResponse():AdsList
}