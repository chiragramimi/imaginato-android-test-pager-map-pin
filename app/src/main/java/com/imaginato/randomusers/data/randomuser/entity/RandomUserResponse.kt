package com.imaginato.randomusers.data.randomuser.entity

import android.os.Parcelable
import com.google.android.gms.maps.model.Marker
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class RandomUserResponse(
    @SerializedName("results")
    val results: ArrayList<RandomUserItem>
)

@Parcelize
data class RandomUserItem(
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("nat")
    val nat: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("dob")
    val dob: Dob? = null,
    @SerializedName("name")
    val name: Name? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("cell")
    val cell: String? = null,
    @SerializedName("picture")
    val picture: Picture? = null,
    var marker: @RawValue Marker? = null
) : Parcelable

@Parcelize
data class Location(
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("coordinates")
    val coordinates: Coordinates?
) : Parcelable

@Parcelize
data class Coordinates(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
) : Parcelable

@Parcelize
data class Dob(
    @SerializedName("date")
    val date: String?,
    @SerializedName("age")
    val age: Int?
) : Parcelable

@Parcelize
data class Picture(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
) : Parcelable

@Parcelize
data class Name(
    @SerializedName("last")
    val last: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("first")
    val first: String?
) : Parcelable
