package com.rickyslash.floydalbum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val name: String,
    val desc: String,
    val photo: String,
    val release: Int,
    val song: String
) : Parcelable