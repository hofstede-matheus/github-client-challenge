package com.hofstedematheus.githubclientchallenge.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.data.constants.IS_FIRST_TIME_OPEN
import com.hofstedematheus.githubclientchallenge.data.constants.PREFERENCES_NAME
import com.hofstedematheus.githubclientchallenge.databinding.ActivityMainBinding
import com.hofstedematheus.githubclientchallenge.databinding.FragmentPublicRepositoriesBinding
import com.hofstedematheus.githubclientchallenge.ui.scenes.intro.AppIntroduction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAppIntroduction()
        setTheme(R.style.Theme_GithubClientChallenge)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            navController.graph
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupAppIntroduction() {
        if(getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getBoolean(IS_FIRST_TIME_OPEN, true)) startActivity(
            Intent(this, AppIntroduction::class.java)
        )
    }
}