package com.example.internshala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.internshala.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val languagelist=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        languagelist.add("English")
        languagelist.add("Hindi")
        languagelist.add("French")
        languagelist.add("hindi")
        val arrayadapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,languagelist)
        binding.spinner2.adapter=arrayadapter

binding.button3.setOnClickListener {
    startActivity(Intent(this,Mobiilenumberverification::class.java))
    finish()  
}



    }
}