package com.sryang.library

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @param onBottom 하단 도달
 */
@Composable
fun BottomDetectingLazyColumn(
    modifier            : Modifier                      = Modifier,
    listState           : LazyListState                 = rememberLazyListState(),
    items               : Int                           = 0,
    onBottom            : () -> Unit                    = { },
    contentPadding      : PaddingValues                 = PaddingValues(0.dp),
    reverseLayout       : Boolean                       = false,
    verticalArrangement : Arrangement.Vertical          = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment : Alignment.Horizontal          = Alignment.Start,
    flingBehavior       : FlingBehavior                 = ScrollableDefaults.flingBehavior(),
    userScrollEnabled   : Boolean                       = true,
    listContent         : LazyListScope.() -> Unit = {},
    contents            : @Composable ((Int) -> Unit)       = {},
) {
    LazyColumn(
        state               = listState,
        modifier            = modifier,
        userScrollEnabled   = userScrollEnabled,
        contentPadding      = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior       = flingBehavior,
    ) {
        items(items) { item ->
            // 아이템을 표시하는 코드
            contents.invoke(item)
        }

        listContent()

        // 하단 감지를 위한 공간 생성
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .background(Color.Transparent)
            )
            LaunchedEffect(Unit) {
                onBottom.invoke()
            }
        }
    }
}