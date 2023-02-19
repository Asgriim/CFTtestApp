package org.asgrim.cfttestapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.asgrim.cfttestapp.data.dto.BankDTO

@Entity(tableName = "card_table")
data class CardDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @Embedded
    val number: org.asgrim.cfttestapp.data.Number?,
    @Embedded
    val bank: BankDTO?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded
    val country: Country?

)
