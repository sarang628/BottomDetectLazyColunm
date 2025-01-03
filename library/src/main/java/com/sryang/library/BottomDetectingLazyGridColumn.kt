package com.sryang.library

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @param modifier Modifier
 * @param listState LazyList 상태
 * @param items 항목 개수
 * @param onBottom 하단 도달
 * @param contentPadding 내용에 적용할 패딩 값
 * @param verticalArrangement 새로 마진 값
 * @param horizontalArrangement 가로 마진 값
 * @param flingBehavior 플링 상태
 * @param userScrollEnabled 사용자 스크롤 가능 여부
 * @param columns 그리드 설정
 * @param contents 내용
 */
@Composable
fun BottomDetectingGridLazyColumn(
    modifier: Modifier = Modifier,
    listState: LazyGridState = rememberLazyGridState(),
    items: Int,
    onBottom: () -> Unit = { },
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    columns: GridCells,
    contents: @Composable ((Int) -> Unit),
) {
    LazyVerticalGrid(
        state = listState,
        modifier = modifier,
        userScrollEnabled = userScrollEnabled,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        flingBehavior = flingBehavior,
        columns = columns
    ) {
        items(items) { item ->
            // 아이템을 표시하는 코드
            contents.invoke(item)
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
                onBottom.invoke()
            }
        }
    }
}