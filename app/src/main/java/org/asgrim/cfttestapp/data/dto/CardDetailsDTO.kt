package org.asgrim.cfttestapp.data.dto


import org.asgrim.cfttestapp.data.Bank
import org.asgrim.cfttestapp.data.Country

data class CardDetailsDTO(
    val id: Int?,
    val number: org.asgrim.cfttestapp.data.Number?,
    val bank: Bank?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?
)
