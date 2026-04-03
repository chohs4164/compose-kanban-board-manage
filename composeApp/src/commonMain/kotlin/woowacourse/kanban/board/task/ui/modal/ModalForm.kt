package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.modal_title_new_task
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanCardForm
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskMockData
import woowacourse.kanban.board.task.ui.board.TaskModalMode


@Composable
fun ModalForm(
    editingCard: KanbanCard? = null,
    modalMode: TaskModalMode,
    modifier: Modifier = Modifier,
    assignee: List<String>,
    onDismissRequest: () -> Unit,
    onCreate: (KanbanCardForm, KanbanStatus) -> Unit = { _, _ -> },
    onEdit: (KanbanCardForm, KanbanStatus) -> Unit = { _, _ -> },
    onDelete: () -> Unit = { },
) {
    // 모달의 내용과 상태(생성일 때에는 빈 값, 수정일 때에는 기존 값 받아오기)
    val state = remember(editingCard?.id) {
        editingCard?.let { ModalCreateFormState.from(editingCard) }
            ?: ModalCreateFormState()
    }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(
                    1.dp,
                    Color.LightGray,
                    RoundedCornerShape(10.dp),
                ),
        ) {
            // 모달 헤더
            ModalHeader(modalMode = modalMode, onDismissRequest = onDismissRequest)

            HorizontalDivider(color = Color.LightGray)
            // 모달 바디
            ModalBody(
                modifier = Modifier,
                modalMode = modalMode,
                state = state,
                assignee = assignee,
                onDismissRequest = onDismissRequest,
                onCreate = onCreate,
                onEdit = onEdit,
                onDelete = onDelete,
            )
        }
    }
}

private class ModalFormPreviewParameterProvider : PreviewParameterProvider<TaskModalMode> {
    override val values = sequenceOf(
        TaskModalMode.CREATE,
        TaskModalMode.EDIT,
    )
}

@Preview(
    widthDp = 672,
    heightDp = 1000,
)
@Composable
private fun ModalFormPreview(@PreviewParameter(ModalFormPreviewParameterProvider::class) taskModalMode: TaskModalMode) {
    ModalForm(
        modalMode = taskModalMode,
        assignee = TaskMockData.assignees,
        onDismissRequest = {},
        onCreate = { _, _ -> },
    )
}
