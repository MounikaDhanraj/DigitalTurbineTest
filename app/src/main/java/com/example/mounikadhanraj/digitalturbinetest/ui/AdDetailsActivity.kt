package com.example.mounikadhanraj.digitalturbinetest.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mounikadhanraj.digitalturbinetest.R
import com.example.mounikadhanraj.digitalturbinetest.model.AdDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ad_details.*
import kotlinx.android.synthetic.main.item.*

class AdDetailsActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_AD="AD"

         fun createIntent(context: Context, adDetails: AdDetails): Intent
        {
            val intent = Intent(context, AdDetailsActivity::class.java)
            intent.putExtra(EXTRA_AD,adDetails)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_details)

        val adDetails = intent.getParcelableExtra<AdDetails>(EXTRA_AD) as AdDetails

        Picasso.get()
                .load(adDetails.productThumbnail)
                .into(image)

        productname.text = adDetails.productName

        rating.text = adDetails.rating

        product_description.text = adDetails.productDescription

    }
}