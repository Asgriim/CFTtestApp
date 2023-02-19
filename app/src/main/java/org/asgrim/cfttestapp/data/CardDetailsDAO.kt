package org.asgrim.cfttestapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDetailsDAO {

    @Insert
    suspend fun insertCardDetails(cardDetails: CardDetails)

    @Delete
    suspend fun deleteCardDetails(cardDetails: CardDetails)

    @Query("DELETE  FROM card_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM card_table")
    fun getCardDetails(): Flow<List<CardDetails>>

}