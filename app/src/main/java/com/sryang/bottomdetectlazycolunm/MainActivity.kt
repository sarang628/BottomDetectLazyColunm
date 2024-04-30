package com.sryang.bottomdetectlazycolunm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
            var position by remember { mutableStateOf("0") }
            val listState = rememberLazyListState()
            LaunchedEffect(key1 = position) {
                try {
                    Log.d("__BottomDetectingLazyColumn", "${position}")
                    listState.animateScrollToItem(Integer.parseInt(position))
                } catch (e: Exception) {

                }
            }
            BottomDetectLazyColunmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.fillMaxSize()) {
                        OutlinedTextField(value = position, onValueChange = { position = it })
                        val items = (1..100).map { "Item $it" }
                        BottomDetectingLazyColumn(
                            listState = listState,
                            items = items.size,
                            onBottom = {},
                        ) { index ->
                            Text(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth(),
                                text = items[index]
                            )
                        }
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

}