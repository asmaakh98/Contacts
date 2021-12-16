package com.example.contacts_management.database


import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: Contact)

    @Query(value="SELECT * FROM contact_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Contact>>

    @Update
    suspend fun updateUser(contact:Contact)


    @Query(value="DELETE FROM contact_table")
    suspend fun deleteAllContacts()
    @Query("SELECT * FROM contact_table WHERE firstName LIKE :searchQuery ")
    fun searchDatabase(searchQuery: String): LiveData<List<Contact>>
}