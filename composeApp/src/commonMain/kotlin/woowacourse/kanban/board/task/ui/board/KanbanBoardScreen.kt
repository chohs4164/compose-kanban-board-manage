package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import woowacourse.kanban.board.task.domain.KanbanBoard
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanCardForm
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskMockData
import woowacourse.kanban.board.task.ui.modal.ModalCreateForm
import woowacourse.kanban.board.theme.BoardBackground

@Composable
fun KanbanBoardScreen(
    modifier: Modifier = Modifier,
    boardId: Int, // 보드의 Id
    kanbanBoard: KanbanBoard, // KanbanProject 에서 띄울 KanbanBoard 하나
    onAddCard: (Int, KanbanCardForm, KanbanStatus) -> Unit, // 카드 추가(boardId, 보드 폼 내용, 칸반카드 상태 받아옴)
    getIsDropTarget: (KanbanStatus) -> Boolean = { false }, // 칸반 카드가 놓아지는 위치 파악
    onBoundsChanged: (KanbanStatus, Rect) -> Unit = { _, _ -> }, // 칸반 카드 상태와 위치 변경 파악
    onTaskDragStart: (KanbanCard) -> Unit = {}, // task 드래그 시작
    onTaskDragChange: (Offset) -> Unit = {}, // task의 위치가 바뀌었는지 파악
    onTaskDragEnd: () -> Unit = {}, // task의 최종 위치 파악
    onTaskDragCancel: () -> Unit = {}, // task 드래그 취소
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
) {
    // 모달 창을 화면에 띄울 건지
    var isShowModal by remember { mutableStateOf(false) }
    // 상태에 따른 KanbanCard 분리를 위한 관리
    val todoCards = kanbanBoard.getCardByStatus(KanbanStatus.TO_DO)
    val inProgressCards = kanbanBoard.getCardByStatus(KanbanStatus.IN_PROGRESS)
    val doneCards = kanbanBoard.getCardByStatus(KanbanStatus.DONE)

    if (isShowModal) {
        ModalCreateForm(
            assignee = TaskMockData.assignees,
            onDismissRequest = { isShowModal = false },
            onCreate = { form, status ->
                onAddCard(boardId, form, status)
                isShowModal = false
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "새로운 태스크가 추가되었습니다.",
                        duration = SnackbarDuration.Short,
                    )
                }
            },
            modifier = Modifier.width(672.dp).height(820.dp),
        )
    }

        // 칸반 보드
        Scaffold(
            modifier = modifier,
            containerColor = Color.White,
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                    SnackBarCard(
                        modifier = Modifier,
                        message = snackbarData.visuals.message,
                        onDismiss = { snackbarData.dismiss() },
                    )
                }
            },
            topBar = {
                KanbanBoardHeader(
                    modifier = Modifier.padding(
                        vertical = 16.dp,
                        horizontal = 24.dp,
                    ),
                    title = kanbanBoard.title,
                    doneCount = kanbanBoard.doneCount,
                    totalCount = kanbanBoard.totalCount,
                    onCreateClick = { isShowModal = true },
                )
            },
        ) { paddingValues ->
            KanbanBody(
                todoCards = todoCards,
                inProgressCards = inProgressCards,
                doneCards = doneCards,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .background(BoardBackground)
                    .padding(24.dp),
                getIsDropTarget = getIsDropTarget,
                onBoundsChanged = onBoundsChanged,
                onTaskDragStart = onTaskDragStart,
            onTaskDragChange = onTaskDragChange,
            onTaskDragEnd = onTaskDragEnd,
            onTaskDragCancel = onTaskDragCancel,
        )
    }
}

@Preview(
    widthDp = 1300,
    heightDp = 900,
)
@Composable
private fun KanbanBoardScreenPreview() {
    val scope = rememberCoroutineScope()
    KanbanBoardScreen(
        boardId = 0,
        onAddCard = { _, _, _ -> },
        kanbanBoard = KanbanBoard(
            title = "compose",
            cards = listOf(),
        ),
        snackbarHostState = remember { SnackbarHostState() },
        scope = scope,
    )
}
