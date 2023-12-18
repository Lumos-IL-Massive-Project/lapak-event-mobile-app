package com.example.connectus.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.connectus.R
import com.example.connectus.databinding.ActivityMainBinding
import com.example.connectus.network.response.LoginData
import com.example.connectus.utils.AppPreferenceManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigationView, navController)

        val authCredential = appPreferenceManager.getAuthCredentials()
        val role = Gson().fromJson(authCredential, LoginData::class.java)?.role

        for (i in 0 until binding.bottomNavigationView.menu.size()) {
            val menuItem = binding.bottomNavigationView.menu.getItem(i)

            if (menuItem.itemId == R.id.business) {
                menuItem.isVisible = role == "event organizer"
            }
        }
    }
}