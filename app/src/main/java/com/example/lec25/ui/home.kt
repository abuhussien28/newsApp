package com.example.lec25.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.lec25.R
import com.example.lec25.databinding.ActivityHomeBinding
import com.google.android.gms.ads.MobileAds

class home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this) {}
       /* binding.btnGenral.setOnClickListener {GetCat("general")}
        binding.btnSport.setOnClickListener {GetCat("sports")}
        binding.btnTecno.setOnClickListener {GetCat("technology")}*/
        binding.apply {
            toggle= ActionBarDrawerToggle(this@home,Drawer,
                R.string.drawe_open,
                R.string.drawe_close)
            Drawer.addDrawerListener(toggle)
            toggle.syncState()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.item_one -> GetCat("general")
                    R.id.item2 -> GetCat("sports")
                    R.id.item3 -> GetCat("technology")
                    R.id.item4 -> GetCat("science")
                    R.id.item5 -> GetCat("business")
                    R.id.settings -> startActivity(Intent(this@home, settings::class.java))
                }
                true
            }
        }
    }
    fun GetCat(cat:String){
        val i=Intent(this, MainActivity::class.java)
        i.putExtra("cat",cat)
        startActivity(i)
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.settings,menu)
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            true
       // else startActivity(Intent(this,settings::class.java))

        return super.onOptionsItemSelected(item)
    }

    private fun closeDrawer(){
        binding.Drawer.closeDrawer(GravityCompat.START)}
    override fun onBackPressed() {
        if (binding.Drawer.isDrawerOpen(GravityCompat.START))
            closeDrawer()
        else
            super.onBackPressed()
    }
}