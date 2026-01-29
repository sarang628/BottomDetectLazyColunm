package com.sryang.bottomdetectlazycolunm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.bottomdetectlazycolunm.ui.theme.BottomDetectLazyColunmTheme
import com.sryang.library.BottomDetectingGridLazyColumn
import com.sryang.library.BottomDetectingLazyColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomDetectLazyColunmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomDetectingLazyColumnPreview()
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

@Preview(showBackground = true)
@Composable
fun BottomDetectingLazyColumnPreview() {
    var position by remember { mutableStateOf("0") }
    val listState = rememberLazyListState()
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = position) {
        try {
            Log.d("__BottomDetectingLazyColumn", "${position}")
            listState.animateScrollToItem(Integer.parseInt(position))
        } catch (e: Exception) {

        }
    }

    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(value = position, onValueChange = { position = it })
        val items = (1..100).map { "Item $it" }
        Text("onBottomDetect  : $count")
        BottomDetectingLazyColumn(
            listState = listState,
            onBottom = { count++ },
        ){
            items(items){
                Text(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    text = it
                )
            }
            item {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomDetectingLazyGridColumnPreview() {
    var position by remember { mutableStateOf("0") }
    val listState = rememberLazyGridState()
    var count by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = position) {
        try {
            Log.d("__BottomDetectingLazyColumn", "${position}")
            listState.animateScrollToItem(Integer.parseInt(position))
        } catch (e: Exception) {

        }
    }

    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(value = position, onValueChange = { position = it })
        val items = (1..100).map { "Item $it" }
        Text("onBottomDetect  : $count")
        BottomDetectingGridLazyColumn(
            listState = listState,
            items = items.size,
            onBottom = { count++ },
            columns = GridCells.Fixed(3)
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