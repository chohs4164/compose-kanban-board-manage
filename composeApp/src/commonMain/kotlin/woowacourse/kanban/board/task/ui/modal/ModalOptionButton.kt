package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.status_Done
import kanbanboard.composeapp.generated.resources.status_In_Progress
import kanbanboard.composeapp.generated.resources.status_to_do
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskMockData
import woowacourse.kanban.board.theme.Blue50
import woowacourse.kanban.board.theme.Blue700
import woowacourse.kanban.board.theme.Gray200
import woowacourse.kanban.board.theme.Indigo50
import woowacourse.kanban.board.theme.Indigo500

@Composable
fun ModalOptionButton(
    modifier: Modifier = Modifier,
    selectedContainerColor: Color,
    selectedBorderColor: Color,
    onClick: () -> Unit,
    isSelected: Boolean,
    content: @Composable () -> Unit,
) {
    val containerColor = if (isSelected) selectedContainerColor else Color.White
    val borderColor = if (isSelected) selectedBorderColor else Gray200
    Box(
        modifier = modifier
            .width(200.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(containerColor)
            .border(
                1.5.dp,
                borderColor,
                RoundedCornerShape(10.dp),
            )
            .clickable(
                enabled = true,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview
@Composable
private fun ModalOptionButtonPreview() {
    var enabled by remember { mutableStateOf(false) }
    ModalOptionButton(
        onClick = {},
        content = {
            ModalOptionStatus(
                modifier = Modifier,
                kanbanStatus = KanbanStatus.IN_PROGRESS,
            )
        },
        isSelected = enabled,
        selectedContainerColor = Blue50,
        selectedBorderColor = Blue700,
    )
}

@Preview
@Composable
private fun ModalOptionAssigneePreview() {
    var enabled by remember { mutableStateOf(false) }
    ModalOptionButton(
        onClick = {},
        content = {
            ModalOptionAssignee(
                modifier = Modifier,
                name = TaskMockData.assignees[0],
            )
        },
        isSelected = enabled,
        selectedContainerColor = Indigo50,
        selectedBorderColor = Indigo500,
    )
}
