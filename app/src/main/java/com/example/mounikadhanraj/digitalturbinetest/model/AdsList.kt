package com.example.mounikadhanraj.digitalturbinetest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "ads",strict = false)

data class AdsList  @JvmOverloads constructor(
    @field:ElementList(name = "ad",inline = true)
    var ad: List<AdDetails>?=null
):Parcelable