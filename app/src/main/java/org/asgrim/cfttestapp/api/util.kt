package org.asgrim.cfttestapp.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.asgrim.cfttestapp.data.Bank
import org.asgrim.cfttestapp.data.CardDetails
import org.asgrim.cfttestapp.data.Country
import org.asgrim.cfttestapp.data.dto.BankDTO
import org.asgrim.cfttestapp.data.dto.CardDetailsDTO



fun toEntity(cardDetailsDTO: CardDetailsDTO): CardDetails {
    val bank = BankDTO(
        cardDetailsDTO.bank?.name,
        cardDetailsDTO.bank?.url,
        cardDetailsDTO.bank?.phone,
        cardDetailsDTO.bank?.city
    )
    val country = Country(
        cardDetailsDTO.country?.numeric,
        cardDetailsDTO.country?.alpha2,
        cardDetailsDTO.country?.name,
        cardDetailsDTO.country?.emoji,
        cardDetailsDTO.country?.currency,
        cardDetailsDTO.country?.latitude,
        cardDetailsDTO.country?.longitude
    )
    val number = org.asgrim.cfttestapp.data.Number(
        cardDetailsDTO.number?.length,
        cardDetailsDTO.number?.luhn
    )
    val entity = CardDetails(
        null,
        number,
        bank,
        cardDetailsDTO.scheme,
        cardDetailsDTO.type,
        cardDetailsDTO.brand,
        cardDetailsDTO.prepaid,
        country
    )
    return entity
}

const val baseURL = "https://lookup.binlist.net/"
val httpClient = OkHttpClient()

suspend fun binReq(num: String): CardDetails {
    val request = Request.Builder().url(baseURL + num).build()
    val response = httpClient.newCall(request).execute()
    when(response.code){
        400,404 -> throw Exception("Card not found")
        429 -> throw Exception("Too much request.\nTry later")
        200 -> return toEntity(Gson().fromJson(response.body?.string(),CardDetailsDTO::class.java))
        else -> throw Exception("Unexpected error")
    }
}

fun defaultCardDetails(): CardDetails = CardDetails(0,null,null,null,null,null,null,null)