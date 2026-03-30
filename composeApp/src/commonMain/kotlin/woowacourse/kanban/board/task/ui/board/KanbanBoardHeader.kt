package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.theme.CreateButtonBackground

@Composable
fun KanbanBoardHeader(modifier: Modifier = Modifier, title: String, doneCount: Int, totalCount: Int, onCreateClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        val progress = if (totalCount != 0) (doneCount * 100 / totalCount) else 0

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BoardHeaderTitleProgress(
                title = title,
                doneCount = doneCount,
                totalCount = totalCount,
                progress = progress,
            )
            BoardCreateButton(onCreateClick = onCreateClick)
        }

        LinearProgressIndicator(
            progress = { progress * 0.01f },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
            color = CreateButtonBackground,
            trackColor = Color.LightGray,
            strokeCap = StrokeCap.Butt,
            gapSize = 0.dp,
            drawStopIndicator = {},
        )
    }
}

@Preview(widthDp = 1000)
@Composable
private fun KanbanBoardHeaderPreview() {
    KanbanBoardHeader(
        title = "보드 제목",
        doneCount = 1,
        totalCount = 3,
        onCreateClick = {},
    )
}
