package com.example.mounikadhanraj.digitalturbinetest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mounikadhanraj.digitalturbinetest.model.AdDetails
import com.example.mounikadhanraj.digitalturbinetest.repository.AdRepository
import com.example.mounikadhanraj.digitalturbinetest.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdViewModel(private val adsRepository:AdRepository) :ViewModel() {

    val adResponse = MutableLiveData<Resource<List<AdDetails>>>()

    init{
        fetchAds()
    }

    private fun fetchAds(){
        viewModelScope.launch (Dispatchers.IO)
        {
            try{

                val adsfromapi = adsRepository.getAdResponse()
                adResponse.postValue(Resource.success(adsfromapi.ad))
            }
            catch (e:Exception)
            {
                adResponse.postValue(Resource.error(e.toString(),null))
            }
        }
    }

    fun getAdsList(): LiveData<Resource<List<AdDetails>>> {
        return adResponse
    }
}