package com.example.contacts.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contacts_management.database.Contact

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite:Favorite)

    @Query(value="SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Favorite>>

    @Update
    suspend fun updateUser(favorite:Favorite)

    @Query(value="DELETE FROM favorite_table")
    suspend fun deleteAllContacts()

}