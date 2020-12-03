package com.example.mounikadhanraj.digitalturbinetest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.text.DecimalFormat
import java.text.NumberFormat

@Parcelize
@Root(name = "ad", strict = false)
data class AdDetails @JvmOverloads constructor (

    @field: Element(name = "appId")
    var appId : String = "",

    @field: Element(name = "averageRatingImageURL", required = false)
    var averageRatingImageURL:String="",

    @field: Element(name = "productDescription", required = false)
    var productDescription:String="",

    @field: Element(name = "productName", required = false)
    var productName:String="",

    @field: Element(name = "productThumbnail", required = false)
    var productThumbnail:String="",

    @field: Element(name = "rating", required = false)
    var rating:String=""
   ):Parcelable