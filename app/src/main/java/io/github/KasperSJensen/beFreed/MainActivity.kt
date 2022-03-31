package io.github.KasperSJensen.beFreed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

  lateinit var  appBarConfiguration: AppBarConfiguration
  lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)




        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navController = findNavController(R.id.fragmentContainerView)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.journalOverviewFragment,
            R.id.profileFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)






   // NavigationUI.setupWithNavController(bottomNavigationView,navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

}