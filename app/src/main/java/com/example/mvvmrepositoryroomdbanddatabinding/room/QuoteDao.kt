package com.example.mvvmrepositoryroomdbanddatabinding.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: Quote)
    @Update
    suspend fun updateQuote(quote: Quote)
    @Delete
    suspend fun deleteQuote(quote: Quote)

    @Query("SELECT * FROM quote")
    fun getAllQuotes(): LiveData<List<Quote>>
}