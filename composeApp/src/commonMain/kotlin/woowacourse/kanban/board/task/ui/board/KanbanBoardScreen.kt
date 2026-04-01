package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import woowacourse.kanban.board.task.domain.KanbanBoard
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanCardForm
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskMockData
import woowacourse.kanban.board.task.ui.modal.ModalCreateForm
import woowacourse.kanban.board.theme.Gray50

@Composable
fun KanbanBoardScreen(
    modifier: Modifier = Modifier,
    kanbanBoard: KanbanBoard, // KanbanProject 에서 띄울 KanbanBoard 하나
    onAddCard: (KanbanCardForm, KanbanStatus) -> Unit, // 카드 추가(boardId, 보드 폼 내용, 칸반카드 상태 받아옴)
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
    val reviewCards = kanbanBoard.getCardByStatus(KanbanStatus.REVIEW)
    val doneCards = kanbanBoard.getCardByStatus(KanbanStatus.DONE)

    if (isShowModal) {
        ModalCreateForm(
            assignee = TaskMockData.assignees,
            onDismissRequest = { isShowModal = false },
            onCreate = { form, status ->
                onAddCard(form, status)
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
            reviewCards = reviewCards,
            doneCards = doneCards,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .background(Gray50)
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
    widthDp = 1500,
    heightDp = 900,
)
@Composable
private fun KanbanBoardScreenPreview() {
    val scope = rememberCoroutineScope()
    KanbanBoardScreen(
        onAddCard = { _, _ -> },
        kanbanBoard = KanbanBoard(
            title = "compose",
            cards = listOf(),
        ),
        snackbarHostState = remember { SnackbarHostState() },
        scope = scope,
    )
}
