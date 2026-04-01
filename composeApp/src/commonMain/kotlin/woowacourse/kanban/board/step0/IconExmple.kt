package woowacourse.kanban.board.step0

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconExample() {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "아이콘 예시입니다.",
    )
}

@Preview
@Composable
private fun IconExamplePreview() {
    IconExample()
}
