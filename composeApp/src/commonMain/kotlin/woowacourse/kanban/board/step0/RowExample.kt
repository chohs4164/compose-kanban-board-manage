package woowacourse.kanban.board.step0

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RowExample() {
    Button(onClick = { }) {
        Row {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "좋아요 아이콘",
            )
            Text(text = "좋아요")
        }
    }
}

@Preview
@Composable
private fun RowExamplePreview() {
    RowExample()
}
