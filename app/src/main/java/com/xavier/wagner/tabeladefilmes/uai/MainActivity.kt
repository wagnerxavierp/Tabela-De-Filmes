package com.xavier.wagner.tabeladefilmes.uai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.xavier.wagner.tabeladefilmes.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupBottomNavigation(){
        bottom_navigation.setOnNavigationItemSelectedListener  { item ->
            when(item.itemId) {
                R.id.filmesNavigation -> {
                    val currentDestination =  nav_host_fragment.findNavController().currentDestination?.id
                    if(currentDestination == R.id.pesquisarFragment){
                        nav_host_fragment.findNavController()
                                .navigate(R.id.action_pesquisarFragment_to_filmesFragment)
                    }
                    else if (currentDestination == R.id.contaFragment){
                        nav_host_fragment.findNavController()
                                .navigate(R.id.action_contaFragment_to_filmesFragment)
                    }
                    true
                }
                R.id.pesquisarNavigation -> {
                    val currentDestination =  nav_host_fragment.findNavController().currentDestination?.id
                    if(currentDestination == R.id.contaFragment){
                        nav_host_fragment.findNavController()
                                .navigate(R.id.action_contaFragment_to_pesquisarFragment)
                    }
                    else if (currentDestination == R.id.filmesFragment){
                        nav_host_fragment.findNavController()
                                .navigate(R.id.action_filmesFragment_to_pesquisarFragment)
                    }
                    true
                }
                R.id.contaNavigation -> {
                    val currentDestination =  nav_host_fragment.findNavController().currentDestination?.id
                    if(currentDestination == R.id.filmesFragment){
                        nav_host_fragment.findNavController()
                                .navigate(R.id.action_filmesFragment_to_contaFragment)
                    }
                    else if (currentDestination == R.id.pesquisarFragment){
                        nav_host_fragment.findNavController()
                                .navigate(R.id.action_pesquisarFragment_to_contaFragment)
                    }
                    true
                }else -> {
                    false
                }
            }
        }
    }

}