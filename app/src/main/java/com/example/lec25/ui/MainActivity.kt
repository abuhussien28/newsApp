package com.example.lec25.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.lec25.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        getnews()
  binding.refresh.setOnRefreshListener {
    getnews()
      }
    }
    fun getnews(){
        val retrofit=Retrofit.Builder().baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val call=retrofit.create(callable::class.java)
        val cat=intent.getStringExtra("cat")
        val prefs=getSharedPreferences("code", MODE_PRIVATE)
        val code=prefs.getString("code","eg")
        call.getnews(cat!!,code!!).enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                binding.refresh.isRefreshing=false
                binding.progress.visibility=View.GONE
                val news =response.body()
                val articles=news?.articles
                val adpater= newsAdpater(this@MainActivity,articles!!)
                binding.newsRv.adapter=adpater
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                binding.progress.visibility=View.GONE
                Log.d("trace","error!!")
            }

        })
    }

}