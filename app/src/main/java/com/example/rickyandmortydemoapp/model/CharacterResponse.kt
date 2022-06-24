package com.example.rickyandmortydemoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResponse(
    val info: Info?=null,
    val results: List<CharacterItem>
):Parcelable

@Parcelize
data class Info(
    val count: Int=0,
    val next: String?="",
    val pages: Int?=0,
    val prev: String?=""
):Parcelable