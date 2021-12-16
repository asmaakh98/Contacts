package com.example.contacts

import androidx.fragment.app.Fragment


import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //Fragment Manger will pass the same value to the super class

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {

            0 -> {
                FavoriteFragment()
            }
            1 -> {
                AllFragment()
            }

            else -> return FavoriteFragment()

        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {

            0 -> "Favorites"
            1 -> "All"

            else -> return ""

        }
    }}
