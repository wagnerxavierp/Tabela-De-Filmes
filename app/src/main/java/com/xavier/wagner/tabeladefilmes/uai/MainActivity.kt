package com.xavier.wagner.tabeladefilmes.uai

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.xavier.wagner.tabeladefilmes.R
import com.xavier.wagner.tabeladefilmes.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlinx.android.synthetic.main.drawer_layout.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)
        setupToolbar(toolbarMain, getString(R.string.filmes), false)
        initDrawerLayout()
        setNavigationViewListener()
        setNavigationHeader()
    }

    private fun initDrawerLayout(){
        val toogle = ActionBarDrawerToggle(
                this,
                drawerLayoutMain,
                toolbarMain,
                R.string.abrir_drawer,
                R.string.fechar_drawer
        )
        drawerLayoutMain.addDrawerListener(toogle)
        toogle.syncState()
    }
    private fun setNavigationViewListener() {
        nav_view.setNavigationItemSelectedListener(this)
    }
    private fun setNavigationHeader(){
        val headerView = View.inflate(this, R.layout.drawer_header, null)
        headerView.nomeUsuarioTEV.text = getString(R.string.nome_do_usuario)
        nav_view.addHeaderView(headerView)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.sobre_nos -> {
                Toast.makeText(this, "Sobre nós", Toast.LENGTH_LONG).show()
                drawerLayoutMain.closeDrawer(GravityCompat.START)
                true
            }
            else -> true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
                Toast.makeText(this, "Deslogado com sucesso.", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}