package com.example.contacts

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.contacts.database.Favorite
import kotlinx.android.synthetic.main.layout_list_item.view.*

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    private var favoriteList = emptyList<Favorite>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = favoriteList[position]
        val uri: Uri = Uri.parse(currentItem.profileImg)
        // val uri: Uri = Uri.parse("content://media/external/images/media/31")
        holder.itemView.firstname.text = currentItem.firstName

        // holder.itemView.lastname.text = currentItem.profileImg
        holder.itemView.personImage.load(uri)


    }

    fun setData(favorite: List<Favorite>) {
        this.favoriteList = favorite
        notifyDataSetChanged()
    }

}