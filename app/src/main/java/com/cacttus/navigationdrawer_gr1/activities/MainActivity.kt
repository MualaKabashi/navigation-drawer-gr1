package com.cacttus.navigationdrawer_gr1.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.cacttus.navigationdrawer_gr1.R
import com.cacttus.navigationdrawer_gr1.databinding.ActivityMainBinding
import com.cacttus.navigationdrawer_gr1.fragments.HomeFragment
import com.cacttus.navigationdrawer_gr1.fragments.ProfileFragment
import com.cacttus.navigationdrawer_gr1.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var settingsFragment: SettingsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToggleAndBindLayout()
        setMenuIcon()
        createFragments()
        switchFragment()
    }


    private fun switchFragment() {
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeItem -> {
                    closeDrawerIfOpen()
                    setCurrentFragment(homeFragment)
                }

                R.id.profileItem -> {
                    closeDrawerIfOpen()
                    setCurrentFragment(profileFragment)
                }

                R.id.settings -> {
                    closeDrawerIfOpen()
                    setCurrentFragment(settingsFragment)
                }
            }
            true
        }
    }


    private fun createFragments() {
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        settingsFragment = SettingsFragment()
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
        }.commit()
    }

    private fun setMenuIcon() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setToggleAndBindLayout() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun closeDrawerIfOpen() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}
