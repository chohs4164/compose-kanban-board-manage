package woowacourse.kanban.board.task.ui.modal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.button_create
import kanbanboard.composeapp.generated.resources.button_edit
import kanbanboard.composeapp.generated.resources.modal_title_edit_task
import kanbanboard.composeapp.generated.resources.modal_title_new_task
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.task.domain.KanbanCard
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
    onCreate: (
        title: String,
        content: String,
        assigneeName: String?,
        tags: List<String>,
        status: KanbanStatus,
    ) -> Unit = { _, _, _, _, _ -> },
    onEdit: (
        title: String,
        content: String,
        assigneeName: String?,
        tags: List<String>,
        status: KanbanStatus,
    ) -> Unit = { _, _, _, _, _ -> },
    onDelete: () -> Unit = {},
) {
    val state = remember(editingCard?.id) {
        editingCard?.let { ModalFormState.from(it) } ?: ModalFormState()
    }
    val title = when (modalMode) {
        TaskModalMode.CREATE -> stringResource(Res.string.modal_title_new_task)
        TaskModalMode.EDIT -> stringResource(Res.string.modal_title_edit_task)
    }
    val primaryButtonText = when (modalMode) {
        TaskModalMode.CREATE -> stringResource(Res.string.button_create)
        TaskModalMode.EDIT -> stringResource(Res.string.button_edit)
    }

    val onSubmit = {
        if (state.validate()) {
            val tags = if (state.tag.isEmpty()) {
                emptyList()
            } else {
                state.tag.split(",").map { it.trim() }
            }

            val assigneeName = when (state.assignee.type) {
                AssigneeOptionType.NONE -> null
                AssigneeOptionType.MEMBER -> state.assignee.name
            }

            val status = state.toKanbanCardStatus()

            when (modalMode) {
                TaskModalMode.CREATE -> onCreate(
                    state.title,
                    state.content,
                    assigneeName,
                    tags,
                    status,
                )

                TaskModalMode.EDIT -> onEdit(
                    state.title,
                    state.content,
                    assigneeName,
                    tags,
                    status,
                )
            }
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        TaskFormSlot(
            modifier = modifier,
            title = {
                ModalHeader(
                    title = title,
                    onDismissRequest = onDismissRequest,
                )
            },
            content = {
                ModalBody(
                    state = state,
                    assignee = assignee,
                )
            },
            actions = {
                ModalAction(
                    primaryText = primaryButtonText,
                    isPrimaryEnabled = state.isValidTitle && state.isValidTag,
                    onDismissRequest = onDismissRequest,
                    onPrimaryClick = onSubmit,
                    extraAction = if (modalMode == TaskModalMode.EDIT) {
                        {
                            ModalDeleteAction(onDeleteClick = onDelete)
                        }
                    } else {
                        null
                    },
                )
            },
        )
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
private fun ModalFormPreview(
    @PreviewParameter(ModalFormPreviewParameterProvider::class)
    taskModalMode: TaskModalMode,
) {
    ModalForm(
        modalMode = taskModalMode,
        assignee = TaskMockData.assignees,
        onDismissRequest = {},
    )
}
