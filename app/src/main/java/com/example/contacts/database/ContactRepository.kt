package com.example.contacts_management.database


import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class   ContactRepository(private val contactDao: ContactDao) {

    val readAllData: LiveData<List<Contact>> =contactDao.readAllData()

    suspend fun addContact(contact: Contact){
        contactDao.addContact(contact)
    }
    suspend fun deleteAllContact(){
        contactDao.deleteAllContacts()
    }
    suspend fun updateContact(contact:Contact){
        contactDao.updateUser(contact)
    }
    fun searchDatabase(searchQuery: String): LiveData<List<Contact>> {
        return contactDao.searchDatabase(searchQuery)
    }
}