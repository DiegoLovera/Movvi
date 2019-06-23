package com.diegolovera.movvi.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.diegolovera.movvi.R
import com.diegolovera.movvi.utils.PreferenceUtils
import com.diegolovera.movvi.utils.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar
    private lateinit var mBottomNavigationView: BottomNavigationView
    private var mCurrentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadInitialTheme()
        setContentView(R.layout.activity_main)
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.dark_theme) {
            if (darkTheme()) {
                alternateTheme(false)
                saveThemeConfig(false)
            } else {
                alternateTheme(true)
                saveThemeConfig(true)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onBackPressed() {
        if (mCurrentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return mCurrentNavController?.value?.navigateUp() ?: false
    }

    private fun setupBottomNavigationBar() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation)
        val navGraphIds = listOf(
            R.navigation.popular,
            R.navigation.top,
            R.navigation.upcoming,
            R.navigation.search
        )

        val controller = mBottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        mBottomNavigationView.setOnNavigationItemReselectedListener {
            mCurrentNavController.value.currentDestination.
        }

        controller.observe(this, Observer { navController ->
            NavigationUI.setupActionBarWithNavController(this, navController)
        })
        mCurrentNavController = controller
    }

    private fun darkTheme(): Boolean {
        return PreferenceUtils.loadDarkThemeConfig(this)
    }

    private fun alternateTheme(darkTheme: Boolean) {
        if (darkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun saveThemeConfig(darkTheme: Boolean) {
        PreferenceUtils.saveDarkThemeConfig(this, darkTheme)
    }

    private fun loadInitialTheme() {
        if (darkTheme()) {
            alternateTheme(true)
        } else {
            alternateTheme(false)
        }
    }
}
