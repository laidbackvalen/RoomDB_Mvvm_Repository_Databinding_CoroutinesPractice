package com.example.mvvmrepositoryroomdbanddatabinding.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmrepositoryroomdbanddatabinding.R
import com.example.mvvmrepositoryroomdbanddatabinding.Repository.QuoteRepository
import com.example.mvvmrepositoryroomdbanddatabinding.databinding.ActivityMainBinding
import com.example.mvvmrepositoryroomdbanddatabinding.room.Quote
import com.example.mvvmrepositoryroomdbanddatabinding.room.QuoteDatabase
import com.example.mvvmrepositoryroomdbanddatabinding.viewmodels.MainViewModel
import com.example.mvvmrepositoryroomdbanddatabinding.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val quoteDao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val quoteRepository = QuoteRepository(quoteDao)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(quoteRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()

            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "This is quote", "This is author")
            mainViewModel.insertQuote(quote)
        }


    }
}