package com.example.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.database.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    private lateinit var mUserViewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        val adapter = FavoriteAdapter()
        val recyclerView = view.favrecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //  viewPager.adapter = activity?.let { PageAdapter(it.supportFragmentManager) }
//
        mUserViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { favorite ->
            adapter.setData(favorite)
        })


        return view
    }


}
