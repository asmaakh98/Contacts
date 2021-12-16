package com.example.contacts.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName="favorite_table")
data class Favorite(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val profileImg: String
)