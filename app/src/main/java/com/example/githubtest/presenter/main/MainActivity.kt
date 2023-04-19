package com.example.githubtest.presenter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.githubtext.R
import com.example.githubtext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


         navController = findNavController(R.id.nav_host_fragment_content_main)




    }



}