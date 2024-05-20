package com.example.mvvmrepositoryroomdbanddatabinding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrepositoryroomdbanddatabinding.Repository.QuoteRepository
import com.example.mvvmrepositoryroomdbanddatabinding.room.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun getQuotes(): LiveData<List<Quote>> {
        return quoteRepository.getAllQuotes()
    }

    fun insertQuote(quoteEntity: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.insertQuote(quoteEntity)
        }
    }
}