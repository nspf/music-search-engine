package com.example.musicsearchengine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.musicsearchengine.databinding.ActivityMainBinding
import com.example.musicsearchengine.utils.setupWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

//        setUpNavigation()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = binding.bottomNav
        val navGraphIds = listOf(
                R.navigation.navigation_search,
                R.navigation.navigation_favorites
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_fragment,
                intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

//    private fun setUpNavigation() {
//        host = supportFragmentManager
//                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
//
//        navController = host.navController
//
//        setSupportActionBar(binding.toolbar)
//        setupActionBarWithNavController(this, navController)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return (Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
//                || super.onSupportNavigateUp())
//    }

    fun showToolbar() {
        binding.toolbar.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        binding.toolbar.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNav.visibility = View.GONE
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

}