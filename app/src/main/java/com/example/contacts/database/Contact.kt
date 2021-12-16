package com.example.contacts_management.database

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity


import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName="contact_table")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val occupation: String,
    val nationality: String,
    val location: String,
    val profileImg: String
):Parcelable