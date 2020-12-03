package com.example.mounikadhanraj.digitalturbinetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mounikadhanraj.digitalturbinetest.repository.AdRepository

class AdsViewModelFactory (private val adsRepository: AdRepository):
        ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(AdViewModel::class.java)) {
            return AdViewModel(adsRepository) as T
        }

        throw IllegalArgumentException("Unable to construct viewModel")
    }

}