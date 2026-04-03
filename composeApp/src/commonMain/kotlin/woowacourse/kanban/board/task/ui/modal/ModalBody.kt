package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.label_assignee_with_essential
import kanbanboard.composeapp.generated.resources.label_assignee_without_essential
import kanbanboard.composeapp.generated.resources.label_description
import kanbanboard.composeapp.generated.resources.label_status
import kanbanboard.composeapp.generated.resources.label_tags
import kanbanboard.composeapp.generated.resources.label_title
import kanbanboard.composeapp.generated.resources.place_holder_input_description
import kanbanboard.composeapp.generated.resources.place_holder_input_tags
import kanbanboard.composeapp.generated.resources.place_holder_input_title
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.task.domain.KanbanCardForm
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskErrorType
import woowacourse.kanban.board.task.domain.TaskMockData
import woowacourse.kanban.board.task.ui.board.TaskModalMode
import woowacourse.kanban.board.theme.Blue50
import woowacourse.kanban.board.theme.Blue700
import woowacourse.kanban.board.theme.Indigo50
import woowacourse.kanban.board.theme.Indigo500

enum class AssigneeOptionType {
    NONE,
    MEMBER,
}

data class AssigneeOption(
    val type: AssigneeOptionType,
    val name: String? = null,
) {
    val label: String
        get() = name ?: "없음"
    val hasIcon: Boolean
        get() = type == AssigneeOptionType.MEMBER
}

@Composable
fun ModalBody(
    modifier: Modifier = Modifier,
    modalMode: TaskModalMode,
    assignee: List<String>,
    state: ModalCreateFormState,
    onDismissRequest: () -> Unit,
    onCreate: (KanbanCardForm, KanbanStatus) -> Unit,
    onEdit: (KanbanCardForm, KanbanStatus) -> Unit,
    onDelete: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // 제목 영역
        ModalBodyInput(
            title = stringResource(Res.string.label_title),
            placeholder = stringResource(Res.string.place_holder_input_title),
            maxLines = 1,
            validType = state.validTitle,
            state = state.title,
            onValueChange = {
                state.title = it
                state.resetTitleError()
            },
            isValid = state.isValidTitle,
        )

        // 설명 영역
        ModalBodyInput(
            title = stringResource(Res.string.label_description),
            placeholder = stringResource(Res.string.place_holder_input_description),
            maxLines = 5,
            validType = TaskErrorType.DESCRIPTION_DEFAULT,
            state = state.content,
            onValueChange = {
                state.content = it
            },
            isValid = true, // 설명은 유효성 검사 필요 없음
        )

        // 태그 영역
        ModalBodyInput(
            title = stringResource(Res.string.label_tags),
            placeholder = stringResource(Res.string.place_holder_input_tags),
            maxLines = 1,
            validType = state.validTag,
            state = state.tag,
            onValueChange = {
                state.tag = it
                state.resetTagError()
            },
            isValid = state.validTag == TaskErrorType.TAG_DEFAULT,
        )

        // 상태 영역
        ModalSelector(
            title = stringResource(Res.string.label_status),
            content = {
                itemsIndexed(
                    KanbanStatus.entries,
                ) { id, status ->
                    ModalOptionButton(
                        modifier = Modifier.height(52.dp),
                        onClick = { state.status = id },
                        isSelected = state.status == id,
                        selectedContainerColor = Blue50,
                        selectedBorderColor = Blue700,
                    ) {
                        ModalOptionStatus(
                            modifier = Modifier,
                            kanbanStatus = status,
                        )
                    }
                }
            },
        )


        // 담당자
        val selectedStatus = KanbanStatus.entries[state.status];
        val assigneeOptions = if (selectedStatus == KanbanStatus.TO_DO) {
            listOf(AssigneeOption(AssigneeOptionType.NONE)) +
                    assignee.map { AssigneeOption(AssigneeOptionType.MEMBER, it) }
        } else {
            assignee.map { AssigneeOption(AssigneeOptionType.MEMBER, it) }
        }

        ModalSelector(

            title = if (selectedStatus == KanbanStatus.TO_DO) stringResource(Res.string.label_assignee_without_essential) else stringResource(
                Res.string.label_assignee_with_essential,
            ),
            content = {
                items(assigneeOptions) { option ->
                    ModalOptionButton(
                        modifier = Modifier.height(68.dp),
                        onClick = {
                            state.assignee = option
                        },
                        isSelected = state.assignee == option,
                        selectedContainerColor = Indigo50,
                        selectedBorderColor = Indigo500,
                    ) {
                        ModalOptionAssignee(
                            modifier = Modifier,
                            name = option.label,
                            isExist = option.hasIcon,
                        )
                    }
                }
            },
        )


        // 취소 / 생성 버튼
        ModalAction(
            modalMode = modalMode,
            isValidTitle = state.isValidTitle,
            isValidTag = state.isValidTag,
            onDismissRequest = onDismissRequest,
            onAddOrEditClick = {
                if (state.validate()) {
                    when (modalMode) {
                        TaskModalMode.CREATE -> onCreate(
                            state.toKanbanCardForm(),
                            state.toKanbanCardStatus(),
                        )

                        TaskModalMode.EDIT -> onEdit(
                            state.toKanbanCardForm(),
                            state.toKanbanCardStatus(),
                        )
                    }
                }
            },
            onDeleteClick = {
                onDelete()
            },
        )
    }
}

private class ModalBodyPreviewParameterProvider : PreviewParameterProvider<TaskModalMode> {
    override val values = sequenceOf(
        TaskModalMode.CREATE,
        TaskModalMode.EDIT,
    )
}

@Preview(
    widthDp = 672,
    heightDp = 820,
)


@Composable
private fun ModalBodyPreview(@PreviewParameter(ModalBodyPreviewParameterProvider::class) taskModalMode: TaskModalMode) {
    val state = remember { ModalCreateFormState() }
    ModalBody(
        modifier = Modifier.background(Color.White),
        modalMode = taskModalMode,
        state = state,
        assignee = TaskMockData.assignees,
        onDismissRequest = {},
        onCreate = { _, _ -> },
        onEdit = { _, _ -> },
        onDelete = {},
    )

}
