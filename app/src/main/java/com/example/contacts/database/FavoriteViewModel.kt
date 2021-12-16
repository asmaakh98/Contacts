package com.example.contacts.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contacts_management.database.Contact
import com.example.contacts_management.database.ContactDatabase
import com.example.contacts_management.database.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Favorite>>
    private val repository: FavoriteRepository

    init {
        val favoriteDao = FavoriteDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        readAllData = repository.readAllData
    }

    fun addFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(favorite)
        }
    }
    fun deleteAllFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFavorite()
        }
    }

    }

