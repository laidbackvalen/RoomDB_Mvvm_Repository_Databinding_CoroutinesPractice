package com.example.mvvmrepositoryroomdbanddatabinding.Repository

import androidx.lifecycle.LiveData
import com.example.mvvmrepositoryroomdbanddatabinding.room.QuoteDao
import com.example.mvvmrepositoryroomdbanddatabinding.room.Quote

class QuoteRepository(val quoteDao: QuoteDao) {

    suspend fun insertQuote(quote: Quote) {
        return quoteDao.insertQuote(quote)
    }

    suspend fun updateQuote(quote: Quote) {
        return quoteDao.updateQuote(quote)
    }

    suspend fun deleteQuote(quote: Quote) {
        return quoteDao.deleteQuote(quote)
    }

    fun getAllQuotes(): LiveData<List<Quote>> {
        return quoteDao.getAllQuotes()
    }
}