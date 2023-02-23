package com.example.lec25.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lec25.R
import com.example.lec25.databinding.ActivitySettingsBinding

class settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.group.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb_eg -> SaveCount("eg")
                R.id.rb_us -> SaveCount("us")
                R.id.rb_sa -> SaveCount("sa")
                R.id.rb_gb ->SaveCount("gb")
            }

        }
    }
    fun SaveCount(code:String){
        val perfs=getSharedPreferences("code", MODE_PRIVATE).edit()
        perfs.putString("code",code)
        perfs.apply()
        Toast.makeText(this, "countries chaged", Toast.LENGTH_SHORT).show()
    }
}