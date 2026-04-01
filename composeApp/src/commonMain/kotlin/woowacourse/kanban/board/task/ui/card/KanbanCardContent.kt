package woowacourse.kanban.board.task.ui.card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun KanbanCardContent(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        modifier = modifier,
        fontSize = 14.sp,
        color = Color.DarkGray,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}
