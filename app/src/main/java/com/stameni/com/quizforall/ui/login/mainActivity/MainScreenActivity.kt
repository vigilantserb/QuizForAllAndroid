package com.stameni.com.quizforall.ui.login.mainActivity

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.explore.ExploreFragment
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.favorites.FavoritesFragment
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.home.HomeFragment
import com.stameni.com.quizforall.ui.login.mainActivity.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenActivity : AppCompatActivity() {

    private var navigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
        initToolbar()
        initComponent()
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.navigationIcon!!.setColorFilter(resources.getColor(R.color.grey_3), PorterDuff.Mode.SRC_ATOP)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun initComponent() {

        navigation = findViewById<View>(R.id.navigation) as BottomNavigationView
        navigation!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val homeFragment = HomeFragment.newInstance()
                    openFragment(homeFragment)
                    supportActionBar!!.title = "Home"
                    navigation!!.setBackgroundColor(resources.getColor(R.color.blue_grey_700))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_explore -> {
                    val exploreFragment = ExploreFragment.newInstance()
                    openFragment(exploreFragment)
                    supportActionBar!!.title = "Explore"
                    navigation!!.setBackgroundColor(resources.getColor(R.color.pink_800))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    val favoritesFragment = FavoritesFragment.newInstance()
                    openFragment(favoritesFragment)
                    supportActionBar!!.title = "Favorites"
                    navigation!!.setBackgroundColor(resources.getColor(R.color.grey_700))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    val profileFragment = ProfileFragment.newInstance()
                    openFragment(profileFragment)
                    supportActionBar!!.title = "Profile"
                    navigation!!.setBackgroundColor(resources.getColor(R.color.teal_800))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
