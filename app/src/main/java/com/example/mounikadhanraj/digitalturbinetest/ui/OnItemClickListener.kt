package com.example.mounikadhanraj.digitalturbinetest.ui

import com.example.mounikadhanraj.digitalturbinetest.model.AdDetails

interface OnItemClickListener {

    fun onitemClickedListener(adDetails: AdDetails, position:Int)
}