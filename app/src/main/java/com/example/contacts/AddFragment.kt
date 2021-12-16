package com.example.contacts

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.database.FavoriteViewModel
import com.example.contacts_management.database.Contact
import com.example.contacts_management.database.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    lateinit var img:String
    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var favViewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
view.imageView2.setOnClickListener {
    pickImageFromGallery()
}
        view.saveContactBtn.setOnClickListener{
            insertDataToDatabase()
        }

        view.cancelAddContactBtn.setOnClickListener {
            mUserViewModel.deleteAllContacts()
            favViewModel.deleteAllFavorite()
        }


        return view


}
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // binding.imageView.setImageURI(data?.data)
            //  Toast.makeText(this,""+data?.data,Toast.LENGTH_LONG).show()
            img=data?.data.toString()
            // val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data)
            // val path = saveImage(bitmap)
            Toast.makeText(requireContext(), "Image Saved!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun insertDataToDatabase() {
        val firstName = editTextName.text.toString()
        val nick = editTextNickname.text.toString()
        val phone = editTextPhone.text.toString()
        val email= editTextEmailAddress.text.toString()
        val occupation=editTextOccupation.text.toString()
        val nationality=editTextNationality.text.toString()
        val location=editTextLocation.text.toString()

        if(inputCheck(firstName, nick, phone, email, occupation, nationality, location)){
            // Create User Object
            val contact = Contact(0, firstName, nick, phone,email, occupation, nationality, location,img)
            // Add Data to Database
            mUserViewModel.addContact(contact)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            // findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, nickname: String, phone: String,email: String, occupation: String,nationality: String,location: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(nickname)&& TextUtils.isEmpty(phone)&& TextUtils.isEmpty(email)&& TextUtils.isEmpty(occupation)&& TextUtils.isEmpty(nationality)&& TextUtils.isEmpty(location))
    }

}