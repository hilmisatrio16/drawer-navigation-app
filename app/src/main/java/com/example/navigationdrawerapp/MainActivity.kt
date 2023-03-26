package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.navigationdrawerapp.databinding.ActivityMainBinding
import com.example.navigationdrawerapp.fragment.AccountFragment
import com.example.navigationdrawerapp.fragment.LogoutFragment
import com.example.navigationdrawerapp.fragment.MessageFragment
import com.example.navigationdrawerapp.fragment.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open,R.string.nav_close)
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setFragment(MessageFragment(), "Message")
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_account -> setFragment(AccountFragment(), it.title.toString())
                R.id.nav_message -> setFragment(MessageFragment(), it.title.toString())
                R.id.nav_settings -> setFragment(SettingsFragment(), it.title.toString())
                else -> setFragment(LogoutFragment(),  it.title.toString())
            }
            true
        }
    }

    private fun setFragment(fragment : Fragment, title : String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
        binding.myDrawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}