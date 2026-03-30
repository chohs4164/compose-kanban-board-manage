package woowacourse.kanban.board.task.ui.modal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.status_Done
import kanbanboard.composeapp.generated.resources.status_In_Progress
import kanbanboard.composeapp.generated.resources.status_to_do
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.task.domain.KanbanStatus


@Composable
fun ModalOptionStatus(kanbanStatus: KanbanStatus, modifier: Modifier = Modifier) {
    val status = when (kanbanStatus) {
        KanbanStatus.TO_DO -> stringResource(Res.string.status_to_do)
        KanbanStatus.IN_PROGRESS -> stringResource(Res.string.status_In_Progress)
        KanbanStatus.DONE -> stringResource(Res.string.status_Done)
    }
    Text(
        modifier = modifier,
        text = status,
    )
}
