package com.example.rickyandmortydemoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterItem(
    val created: String? = "",
    val episode: List<String>? = null,
    val gender: String? = "",
    val id: Int? = 0,
    val image: String? = "",
    val location: Location? = null,
    val name: String? = "",
    val origin: Origin? = null,
    val species: String? = "",
    val status: String? = "",
    val type: String? = "",
    val url: String? = ""
) : Parcelable

@Parcelize
data class Location(
    val name: String? = "",
    val url: String? = ""
) : Parcelable

@Parcelize
data class Origin(
    val name: String? = "",
    val url: String? = ""
) : Parcelable