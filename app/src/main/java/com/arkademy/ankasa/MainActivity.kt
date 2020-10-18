package com.arkademy.ankasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.arkademy.ankasa.dashboard.explore.ExploreFragment
import com.arkademy.ankasa.dashboard.ProfileFragment
import com.arkademy.ankasa.dashboard.booking.BookingFragment
import com.arkademy.ankasa.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val booking = BookingFragment()
        val explore = ExploreFragment()
        val profile = ProfileFragment()
        val menu = bottom_navigation.menu
        menu.findItem(R.id.ic_explore).isChecked = true

        currentNavigation(explore)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.ic_booking -> currentNavigation(booking)
                R.id.ic_explore -> currentNavigation(explore)
                R.id.ic_profile -> currentNavigation(profile)
                else -> false
            }
        }
    }

    private fun currentNavigation(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
                .commit()
        }
        return true
    }
}