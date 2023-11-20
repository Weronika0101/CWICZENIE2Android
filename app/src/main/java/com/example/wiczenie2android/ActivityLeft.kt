package com.example.wiczenie2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wiczenie2android.databinding.ActivityLeftBinding


class ActivityLeft : AppCompatActivity() {
    lateinit var binding : ActivityLeftBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }
}