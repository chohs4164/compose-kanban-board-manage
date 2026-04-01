package woowacourse.kanban.board.task.ui.project

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import woowacourse.kanban.board.task.domain.KanbanBoard
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanProject
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskMockData
import woowacourse.kanban.board.task.ui.board.KanbanBoardScreen

@Composable
fun KanbanProjectScreen(modifier: Modifier = Modifier) {
    // task가 드래그 되었는지 판단하고 관리
    var draggedTask by remember { mutableStateOf<KanbanCard?>(null) }
    // 현재 드래그 해서 놓아지는 위치 관리
    var currentDragPosition by remember { mutableStateOf<Offset?>(null) }
    // 칸반 카드의 상태에 따른 영역 관리
    val columnBounds = remember { mutableStateMapOf<KanbanStatus, Rect>() }
    // 선택된 보드 인덱스 관리
    var selectedBoardId by remember { mutableIntStateOf(0) }
    // 칸반 프로젝트의 제목 관리
    var kanbanProject by remember {
        mutableStateOf(
            KanbanProject(
                projectTitle = "4주차 미션 보드",
            ),
        )
    }

    // 스낵바를 위한 상태 관리
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val kanbanBoard by remember {
        mutableStateOf(
            KanbanBoard(
                title = TaskMockData.boardTitles[selectedBoardId], cards = kanbanProject.getKanbanCardByBoardId(selectedBoardId),
            ),
        )
    }
    Row(
        modifier = modifier,
    ) {

        KanbanProjectSideBar(
            modifier = Modifier.fillMaxHeight(),
            title = kanbanProject.projectTitle,
            boardTitle = TaskMockData.boardTitles,
            selected = selectedBoardId,
            onClick = { index ->
                selectedBoardId = index
            },
        )
        KanbanBoardScreen(
            kanbanBoard = kanbanBoard,
            onAddCard = { form, status ->
                val newId = (kanbanProject.kanbanCards.maxOfOrNull { it.id } ?: 0) + 1
                val newCard = KanbanCard.create(newId, selectedBoardId, form, status)

                kanbanProject = kanbanProject.addCard(newCard)
            },
            getIsDropTarget = { status ->
                currentDragPosition?.let { columnBounds[status]?.contains(it) } ?: false
            },
            onBoundsChanged = { status, rect -> columnBounds[status] = rect },
            onTaskDragStart = { task -> draggedTask = task },
            onTaskDragChange = { pos -> currentDragPosition = pos },
            onTaskDragEnd = {
                val dropPosition = currentDragPosition ?: return@KanbanBoardScreen
                val targetStatus = columnBounds.entries
                    .firstOrNull { (_, rect) -> rect.contains(dropPosition) }?.key

                draggedTask?.let { task ->
                    if (targetStatus != null && task.status != targetStatus) {
                        kanbanProject = kanbanProject.updateCardStatus(task.id, targetStatus)
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "태스크가 이동되었습니다.",
                                duration = SnackbarDuration.Short,
                            )
                        }
                    }
                }
                currentDragPosition = null
                draggedTask = null
            },
            onTaskDragCancel = {
                currentDragPosition = null
                draggedTask = null
            },
            snackbarHostState = snackbarHostState,
            scope = scope,
        )
    }
}

@Preview(widthDp = 1500)
@Composable
private fun KanbanProjectScreenPreview() {
    KanbanProjectScreen()
}
