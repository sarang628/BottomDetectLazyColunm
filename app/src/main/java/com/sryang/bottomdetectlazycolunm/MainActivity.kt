package com.sryang.bottomdetectlazycolunm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.bottomdetectlazycolunm.ui.theme.BottomDetectLazyColunmTheme
import com.sryang.library.BottomDetectingLazyColumn
import java.util.Objects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var position by remember { mutableStateOf(0) }
            BottomDetectLazyColunmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(Modifier.fillMaxSize()) {

                        val items = (1..100).map { "Item $it" }

                        BottomDetectingLazyColumn(
                            items = items.size,
                            onBottom = {},
                            position = position
                        ) { index ->
                            Text(text = items[index])
                        }

                        OutlinedTextField(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            value = position.toString(),
                            onValueChange = {
                                try {
                                    position = Integer.parseInt(it)
                                } catch (e: Exception) {

                                }
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomDetectLazyColunmTheme {
        Greeting("Android")
    }
}

@Preview
@Composable
fun MyScreen() {
    val items = (1..100).map { "Item $it" }

    BottomDetectingLazyColumn(
        items = items.size,
        onBottom = {},
    ) { index ->
        Text(text = items[index])
    }
}