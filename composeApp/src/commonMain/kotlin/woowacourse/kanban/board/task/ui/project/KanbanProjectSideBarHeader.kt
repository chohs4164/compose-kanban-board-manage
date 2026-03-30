package woowacourse.kanban.board.task.ui.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.project_title
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.theme.BorderButtonDefault
import woowacourse.kanban.board.theme.color1
import woowacourse.kanban.board.theme.color2

@Composable
fun KanbanProjectSideBarHeader(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = BorderButtonDefault,
            )
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(Res.string.project_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = color1,
        )
        Text(
            text = title,
            color = color2,
            fontSize = 14.sp,
        )
    }
}

@Preview
@Composable
private fun KanbanProjectSideBarHeaderPreview() {
    KanbanProjectSideBarHeader(
        title = "4주차 미션 보드",
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    )
}