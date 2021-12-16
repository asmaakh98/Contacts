package com.example.contacts

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.contacts_management.database.Contact
import kotlinx.android.synthetic.main.layout_list_item.view.*

class AllAdapter: RecyclerView.Adapter<AllAdapter.MyViewHolder>() {

    private var contactList = emptyList<Contact>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = contactList[position]
        val uri: Uri = Uri.parse(currentItem.profileImg)
        // val uri: Uri = Uri.parse("content://media/external/images/media/31")
        holder.itemView.firstname.text = currentItem.firstName
        // holder.itemView.lastname.text = currentItem.profileImg
       holder.itemView.personImage.load(uri)
        holder.itemView.rowLayout.setOnClickListener {
          val action=AllFragmentDirections.actionAllFragmentToUpdateFragment(currentItem)
           holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(contact: List<Contact>){
        this.contactList = contact
        notifyDataSetChanged()
    }
}