package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.progress_text
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.theme.ProgressText

@Composable
fun BoardHeaderTitleProgress(title: String, doneCount: Int, totalCount: Int, progress: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        )

        Text(
            text = stringResource(
                Res.string.progress_text,
                progress,
                doneCount,
                totalCount,
            ),
            color = ProgressText,
            fontSize = 14.sp,
        )
    }
}