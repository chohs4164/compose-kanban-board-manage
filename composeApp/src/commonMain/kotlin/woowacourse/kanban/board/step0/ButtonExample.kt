package woowacourse.kanban.board.step0

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SaveButtonExample() {
    Button(onClick = { }) {
        Text(text = "저장")
    }
}

@Composable
fun FavoriteButtonExample() {
    Button(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "좋아요 아이콘",
        )
    }
}

@Preview
@Composable
private fun ButtonExampleExamplePreview() {
    Column {
        SaveButtonExample()
        FavoriteButtonExample()
    }
}
