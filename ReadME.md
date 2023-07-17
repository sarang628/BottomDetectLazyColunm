# Jetpack Compose 안드로이드 하단 감지 Lazy Colunm

잿팩 컴포즈용 하단 감지 리스트 기능을 구현하였습니다.
구글링에서 검색하여 구현하려고했는데 검색이 잘 되지않아.
챗 GTP에 도움을 받아 구현했습니다. 챗 GPT의 유용함을 다시 한 번 꺠닫게 되었습니다.

```
dependencies {
	        implementation 'com.github.sarang628:BaseFeed:69c748757d'
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