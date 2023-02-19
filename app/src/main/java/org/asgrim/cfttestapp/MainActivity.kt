package org.asgrim.cfttestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.asgrim.cfttestapp.compose.MainScreen
import org.asgrim.cfttestapp.data.CardDetailsDatabase
import org.asgrim.cfttestapp.data.CardDetailsRepositoryImpl
import org.asgrim.cfttestapp.ui.theme.CFTtestAppTheme
import org.asgrim.cfttestapp.vm.VMfactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = CardDetailsDatabase.getInstance(application).cardDetailsDAO
        val repo = CardDetailsRepositoryImpl(dao)
        val factory = VMfactory(repo)
        setContent {
            CFTtestAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                  MainScreen(factory = factory)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CFTtestAppTheme {

    }
}