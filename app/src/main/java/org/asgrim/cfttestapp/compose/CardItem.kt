package org.asgrim.cfttestapp.compose

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import org.asgrim.cfttestapp.api.defaultCardDetails
import org.asgrim.cfttestapp.data.CardDetails

@Preview
@Composable
fun CardItem(
    cardDetails: CardDetails = defaultCardDetails(), modifier: Modifier = Modifier
        .padding(8.dp)
        .background(Color(227, 232, 244))
) {
    val context = LocalContext.current
    val textSiza = 15.sp
    Row(modifier = modifier) {
        Column(modifier.weight(1F), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "SCHEME / NETWORK:")
            Text(text = cardDetails.scheme ?: "?",
                fontSize = textSiza)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "BRAND:")
            Text(text = cardDetails.brand ?: "?",
                fontSize = textSiza)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "CARD NUMBER:")
            Row() {
                Text(text = "length: ${cardDetails.number?.length ?: "?"}", fontSize = 10.sp)
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "LUHN: ${cardDetails.number?.luhn ?: "?"}", fontSize = 10.sp)

            }
        }
        Spacer(modifier = Modifier.size(15.dp))
        Column(modifier.weight(1F), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "PREPAID")
            Text(text = "${cardDetails.prepaid ?: "?"}", fontSize = textSiza)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "COUNTRY:", modifier = Modifier.clickable { sendToMap(context, cardDetails) })
            Text(
                text = "${(cardDetails.country?.emoji ?: "?") + (cardDetails.country?.name ?: "?")}",
                fontSize = 10.sp,
                modifier = Modifier.clickable { sendToMap(context, cardDetails) }
            )
            Text(
                text = "(latitude: ${cardDetails.country?.latitude ?: "?"}  longitude: ${cardDetails.country?.longitude ?: "?"})",
                fontSize = 8.sp,
                modifier = Modifier.clickable { sendToMap(context, cardDetails) }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "BANK")
            Text(
                text = "${cardDetails.bank?.bankName ?: "?"}, ${cardDetails.bank?.city ?: "?"}",
                fontSize = 8.sp
            )
            Text(
                text = cardDetails.bank?.url ?: "?",
                color = Color.Blue,
                fontSize = 8.sp,
                modifier = Modifier.clickable { sendToWeb(context, cardDetails) })
            Text(
                text = cardDetails.bank?.phone ?: "?",
                color = Color.Blue,
                fontSize = 8.sp,
                modifier = Modifier.clickable { sendToTelephone(context, cardDetails) })
        }

    }

}

fun sendToMap(context: Context, cardDetails: CardDetails) {
    if (cardDetails.country?.latitude != null && cardDetails.country?.longitude != null) {
        val mapIntent: Intent = Uri.parse(
            "geo:${cardDetails.country?.latitude},${cardDetails.country?.longitude}?z=6"
        ).let { location ->
            Intent(Intent.ACTION_VIEW, location)
        }
        try {
            startActivity(context, mapIntent, Bundle.EMPTY)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }

    }
}

fun sendToTelephone(context: Context, cardDetails: CardDetails) {
    if (cardDetails.bank?.phone != null) {
        val phoneIntent: Intent = Uri.parse("tel:${cardDetails.bank.phone}").let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }
        try {
            startActivity(context, phoneIntent, Bundle.EMPTY)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }
}


fun sendToWeb(context: Context, cardDetails: CardDetails) {
    if (cardDetails.bank?.url != null) {
        val webIntent: Intent =
            Uri.parse("http://" + cardDetails.bank?.url.toString()).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
        try {
            startActivity(context, webIntent, Bundle.EMPTY)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }

    }

}


