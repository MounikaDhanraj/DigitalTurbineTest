package com.example.mounikadhanraj.digitalturbinetest.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mounikadhanraj.digitalturbinetest.model.AdDetails
import com.example.mounikadhanraj.digitalturbinetest.model.AdsList
import com.example.mounikadhanraj.digitalturbinetest.repository.AdRepository
import com.example.mounikadhanraj.digitalturbinetest.ui.viewmodel.utils.TestCoroutineRule
import com.example.mounikadhanraj.digitalturbinetest.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)

class AdViewModelTest{

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private  lateinit var adObserver : Observer<Resource<List<AdDetails>>>

    @Mock
    private  lateinit var  adRepository: AdRepository

    @Before
    fun setUp() {

    }


    @Test
    fun viewModelTestError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            Mockito.doThrow(RuntimeException(errorMessage))
                .`when`(adRepository)
                .getAdResponse()

            val viewModel = AdViewModel(adRepository)
            viewModel.getAdsList().observeForever(adObserver)

            Mockito.verify(adRepository).getAdResponse()
            Mockito.verify(adObserver).onChanged(Resource.error(
                RuntimeException(errorMessage).toString(),
                null
            ))

            viewModel.getAdsList().removeObserver(adObserver)
        }
    }
}