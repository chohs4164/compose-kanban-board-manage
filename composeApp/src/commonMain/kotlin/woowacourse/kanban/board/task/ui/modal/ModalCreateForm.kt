package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import woowacourse.kanban.board.task.domain.KanbanCardForm
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskMockData

@Composable
fun ModalCreateForm(
    modifier: Modifier = Modifier,
    assignee: List<String>,
    onDismissRequest: () -> Unit,
    onCreate: (KanbanCardForm, KanbanStatus) -> Unit,
) {
    // 모달의 내용과 상태
    val state = remember { ModalCreateFormState() }

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
            ModalHeader(onDismissRequest = onDismissRequest)

            HorizontalDivider(color = Color.LightGray)
            // 모달 바디
            ModalBody(
                state = state,
                assignee = assignee,
                modifier = Modifier,
                onDismissRequest = onDismissRequest,
                onCreate = onCreate,
            )
        }
    }
}

@Preview(
    widthDp = 672,
    heightDp = 1000,
)
@Composable
private fun ModalCreateFormPreview() {
    ModalCreateForm(
        assignee = TaskMockData.assignees,
        onDismissRequest = {},
        onCreate = { _, _ -> },
    )
}
