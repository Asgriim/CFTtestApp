package org.asgrim.cfttestapp.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.asgrim.cfttestapp.api.binReq
import org.asgrim.cfttestapp.api.defaultCardDetails
import org.asgrim.cfttestapp.data.CardDetails

@Composable
fun MainInputBlock(save: (CardDetails) -> Unit){
    var errText by remember {
        mutableStateOf("")
    }
    var text by remember { mutableStateOf("") }
    val lastCardDetails: MutableState<CardDetails> = remember {
        mutableStateOf(defaultCardDetails())
    }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("BIN/IIN") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        placeholder = { Text(text = "Enter the first digits of a card number (BIN/IIN)")},
        colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Magenta),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)


        )
    Text(text = errText, color = Color.Red)
    Button(onClick = {
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    val resp = binReq(text)
                    lastCardDetails.value = resp
                    save(resp)
                    errText = ""
                }catch (e: Exception){
                    errText = e.message?: "error"
                }
            }

        }

    }
    ) {
        Text(text = "SEND")
    }

    CardItem(cardDetails = lastCardDetails.value,modifier = Modifier
        .padding(8.dp)
        .background(Color(227, 232, 244))
        .fillMaxWidth()
    )
}

fun createToast(context: Context, message:String) {
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}
