# Jetpack Compose 안드로이드 하단 감지 Lazy Colunm

```
dependencies {
	        implementation 'com.github.sarang628:BottomDetectingLazyColumn:69c748757d'
	}
```

# How to make

GPT의 도움을 받음.

리스트 상단 하단 2개를 만든다.

상단 리스트는 실제 항목을 표시할 리스트
하단 리스트는 항목이 1개로 이 항목의 표시를 감지하여 하단에 도달했다고 판단.

구현코드

```
/**
 * @param modifier Modifier
 * @param listState LazyList 상태
 * @param items 항목 개수
 * @param onBottom 하단 도달
 * @param contentPadding 내용에 적용할 패딩 값
 * @param verticalArrangement 새로 정렬 값
 * @param flingBehavior 플링 상태
 * @param userScrollEnabled 사용자 스크롤 가능 여부
 * @param contents 내용
 */
@Composable
fun BottomDetectingLazyColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    items: Int,
    onBottom: ((Void?) -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    contents: @Composable ((Int) -> Unit),
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        userScrollEnabled = userScrollEnabled,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
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
                onBottom?.invoke(null)
            }
        }
    }
}
```

예제코드

```
@Preview
@Composable
fun MyScreen() {
    val items = (1..100).map { "Item $it" }

    BottomDetectingLazyColumn(
        items = items.size,
        onBottom = {
            //TODO::
        }
    ) { index ->
        Text(text = items[index])
    }
}
```