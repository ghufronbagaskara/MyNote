package com.example.noteapps

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapps.databinding.ListItemBinding

class ListItem : AppCompatActivity() {

    private lateinit var binding: ListItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}