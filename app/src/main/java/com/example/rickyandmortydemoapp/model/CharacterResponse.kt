package com.example.rickyandmortydemoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResponse(
    val info: Info,
    val results: ArrayList<CharacterItem>
):Parcelable

@Parcelize
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
):Parcelable