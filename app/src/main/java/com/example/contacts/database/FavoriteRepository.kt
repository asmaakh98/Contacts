package com.example.contacts.database

import androidx.lifecycle.LiveData
import com.example.contacts_management.database.Contact
import com.example.contacts_management.database.ContactDao

class   FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val readAllData: LiveData<List<Favorite>> =favoriteDao.readAllData()

    suspend fun addFavorite(favorite: Favorite){
        favoriteDao.addFavorite(favorite)
    }
    suspend fun deleteAllFavorite(){
        favoriteDao.deleteAllContacts()
    }


}