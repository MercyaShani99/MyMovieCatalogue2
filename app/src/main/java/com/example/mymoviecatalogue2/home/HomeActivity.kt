package com.example.mymoviecatalogue2.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymoviecatalogue2.databinding.ActivityHomeBinding
import com.example.mymoviecatalogue2.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tab.setupWithViewPager(activityHomeBinding.viewPager)

        supportActionBar?.title = "Movie Catalogue"

        supportActionBar?.elevation = 0f

        activityHomeBinding.favButton.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}