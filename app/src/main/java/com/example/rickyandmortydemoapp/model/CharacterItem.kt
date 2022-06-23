package com.example.rickyandmortydemoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterItem(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
):Parcelable
@Parcelize
data class Location(
    val name: String,
    val url: String
):Parcelable
@Parcelize
data class Origin(
    val name: String,
    val url: String
):Parcelable