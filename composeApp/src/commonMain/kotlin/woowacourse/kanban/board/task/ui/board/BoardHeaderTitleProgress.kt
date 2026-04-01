package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.progress_text
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.theme.Gray500

@Composable
fun BoardHeaderTitleProgress(modifier: Modifier = Modifier, title: String, doneCount: Int, totalCount: Int) {
    val progress = if (totalCount != 0) (doneCount * 100 / totalCount) else 0

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        // KanbanBoard 제목
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        )

        // 완료율 텍스트
        Text(
            text = stringResource(
                Res.string.progress_text,
                progress,
                doneCount,
                totalCount,
            ),
            color = Gray500,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
private fun BoardHeaderTitleProgressPreview() {
    BoardHeaderTitleProgress(
        modifier = Modifier.background(Color.White),
        title = "Compose Desktop 칸반 보드",
        doneCount = 3,
        totalCount = 6,
    )
}
