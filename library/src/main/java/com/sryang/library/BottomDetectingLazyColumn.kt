package com.sryang.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Objects

@Composable
fun BottomDetectingLazyColumn(
    items: Int,
    onBottom: ((Void?) -> Unit)? = null,
    composable: @Composable ((Int) -> Unit),
    userScrollEnabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier,
        userScrollEnabled = userScrollEnabled
    ) {
        items(items) { item ->
            // 아이템을 표시하는 코드
            composable.invoke(item)
        }

        // 하단 감지를 위한 공간 생성
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .background(Color.Transparent)
            )
            LaunchedEffect(Unit) {
                onBottom?.let {
                    onBottom.invoke(null)
                }
            }
        }
    }
}