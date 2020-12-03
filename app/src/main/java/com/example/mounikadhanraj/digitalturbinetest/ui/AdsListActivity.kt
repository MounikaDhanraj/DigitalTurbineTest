package com.example.mounikadhanraj.digitalturbinetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mounikadhanraj.digitalturbinetest.R
import com.example.mounikadhanraj.digitalturbinetest.model.AdDetails
import com.example.mounikadhanraj.digitalturbinetest.repository.AdRepositoryImpl
import com.example.mounikadhanraj.digitalturbinetest.ui.AdDetailsActivity.Companion.createIntent
import com.example.mounikadhanraj.digitalturbinetest.ui.viewmodel.AdViewModel
import com.example.mounikadhanraj.digitalturbinetest.ui.viewmodel.AdsViewModelFactory
import com.example.mounikadhanraj.digitalturbinetest.util.Status
import kotlinx.android.synthetic.main.activity_ads_list.*

class AdsListActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var viewModel: AdViewModel
    private lateinit var adListAdapter: AdListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads_list)

        setupViewModel()
        observeViewModel()
    }


    private fun setupViewModel() {

        showProgress()
        viewModel = ViewModelProvider(
            this,
            AdsViewModelFactory(
                AdRepositoryImpl()
            )
        ).get(AdViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.getAdsList().observe(this, Observer
        {
            when (it.status){

                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let  { questions->(showQuestionsList(questions))}
                }
                Status.ERROR->{
                    hideProgress()
                    (it.message?.let {errorMessage-> showErrorMessage(errorMessage) })
                    adListRecyclerView.visibility = View.GONE
                }
                Status.LOADING->{
                    showProgress()
                    adListRecyclerView.visibility = View.GONE
                }
            }
        })
    }

    private fun showQuestionsList(ads:List<AdDetails>)
    {
        adListRecyclerView.layoutManager = LinearLayoutManager(this)

        adListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                adListRecyclerView.context,
                (adListRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        adListRecyclerView.visibility = View.VISIBLE

        adListRecyclerView.setHasFixedSize(true)

        adListAdapter = AdListAdapter(this)

        adListAdapter.loadDetails(ads)

        adListRecyclerView.adapter = adListAdapter

    }

    private fun showErrorMessage(errorMessage: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_title)
            .setMessage(errorMessage)
            .setPositiveButton(R.string.dialog_button) { dialog, which -> dialog.dismiss() }
        builder.show()
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onitemClickedListener(adDetails: AdDetails, position: Int) {
        startActivity(createIntent(this,adDetails))
    }
}