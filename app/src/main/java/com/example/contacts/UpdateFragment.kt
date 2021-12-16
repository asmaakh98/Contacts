package com.example.contacts

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contacts.database.Favorite
import com.example.contacts.database.FavoriteViewModel
import com.example.contacts_management.database.Contact
import com.example.contacts_management.database.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var favViewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

       view.Name.setText(args.currentContact.firstName)
        view.Nickname.setText(args.currentContact.lastName)
        view.email.setText(args.currentContact.email)
        view.occupation.setText(args.currentContact.occupation)
        view.phone.setText(args.currentContact.phone)

        view.edit_contact_fab.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)
return  view
}
    private fun updateItem(){
        val firstName = Name.text.toString()
        val nick = Nickname.text.toString()
        val phone = phone.text.toString()
        val email=email.text.toString()
        val occupation=occupation.text.toString()
val nationality=args.currentContact.nationality
        val location = args.currentContact.location
        val  img=args.currentContact.profileImg
        if(inputCheck(firstName, nick, phone, email,occupation)){
            // Create User Object
            val updatedUser = Contact(args.currentContact.id, firstName, nick, phone, email, occupation, nationality, location, img)
            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_allFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, phone:String, email:String,  occupation:String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && phone.isEmpty() && email.isEmpty() && occupation.isEmpty() )
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fav) {
            addTofav()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun addTofav(){
        val id=args.currentContact.id
        val name=args.currentContact.firstName
        val img=args.currentContact.profileImg
        val fav= Favorite(id,name,img)
        favViewModel.addFavorite(fav)
        Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
    }
}