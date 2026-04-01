package woowacourse.kanban.board.task.ui.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.theme.Gray200

@Composable
fun KanbanProjectSideBar(title: String, boardTitle: List<String>, selected: Int, onClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(255.dp)
            .border(
                width = 1.dp,
                color = Gray200,
            )
            .background(Color.White),
    ) {
        // 헤더
        KanbanProjectSideBarHeader(
            modifier = Modifier.fillMaxWidth(),
            title = title,
        )
        // 하단부
        KanbanProjectSideBarItem(
            modifier = Modifier.fillMaxWidth(),
            kanbanBoardTitles = boardTitle,
            selected = selected,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
private fun KanbanProjectSideBarPreview() {
    var selected by remember { mutableIntStateOf(0) }

    KanbanProjectSideBar(
        title = "4주차 미션 보드",
        boardTitle = listOf(
            "Compose1",
            "Compose2",
            "Compose3너무너무긴제목입니다.",
        ),
        selected = selected,
        onClick = { index ->
            selected = index
        },
    )
}
