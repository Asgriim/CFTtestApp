package org.asgrim.cfttestapp.data

import kotlinx.coroutines.flow.Flow

class CardDetailsRepositoryImpl(private val dao: CardDetailsDAO): CardDetailsRepository {


    override suspend fun insertCardDetails(cardDetails: CardDetails) {
      dao.insertCardDetails(cardDetails)
    }

    override suspend fun deleteCardDetails(cardDetails: CardDetails) {
        dao.deleteCardDetails(cardDetails)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override fun getCardDetails(): Flow<List<CardDetails>> {
        println(dao.getCardDetails().toString())
        println("bebra")
        return dao.getCardDetails()
    }
}