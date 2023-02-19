package org.asgrim.cfttestapp.data

import kotlinx.coroutines.flow.Flow


interface CardDetailsRepository {
    suspend fun insertCardDetails(cardDetails: CardDetails)
    suspend fun deleteCardDetails(cardDetails: CardDetails)
    suspend fun deleteAll()
    fun getCardDetails(): Flow<List<CardDetails>>
}