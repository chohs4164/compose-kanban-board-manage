package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import woowacourse.kanban.board.task.ui.modal.ModalForm
import woowacourse.kanban.board.theme.Gray50

enum class TaskModalMode {
    CREATE,
    EDIT,
}

@Composable
fun KanbanBoardScreen(
    modifier: Modifier = Modifier,
    kanbanBoard: KanbanBoard, // KanbanProject 에서 띄울 KanbanBoard 하나
    onAddCard: (KanbanCardForm, KanbanStatus) -> Unit, // 카드 추가(boardId, 보드 폼 내용, 칸반카드 상태 받아옴)
    onEditCard: (Long, KanbanCardForm, KanbanStatus) -> Unit,
    onDeleteCard: (Long) -> Unit,
    getIsDropTarget: (KanbanStatus) -> Boolean = { false }, // 칸반 카드가 놓아지는 위치 파악
    onBoundsChanged: (KanbanStatus, Rect) -> Unit = { _, _ -> }, // 칸반 카드 상태와 위치 변경 파악
    onTaskDragStart: (KanbanCard) -> Unit = {}, // task 드래그 시작
    onTaskDragChange: (Offset) -> Unit = {}, // task의 위치가 바뀌었는지 파악
    onTaskDragEnd: () -> Unit = {}, // task의 최종 위치 파악
    onTaskDragCancel: () -> Unit = {}, // task 드래그 취소
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
) {
    var editingCard by remember { mutableStateOf<KanbanCard?>(null) }
    var taskModalMode by remember { mutableStateOf<TaskModalMode?>(null) }
    // 새 태스크 생성 모달 창을 화면에 띄울 건지
    var isShowAddTaskModal by remember { mutableStateOf(false) }
    // 기존 태스크 수정 모달 창을 화면에 띄울 건지
    var isShowEditTaskModal by remember { mutableStateOf(false) }
    // 상태에 따른 KanbanCard 분리를 위한 관리
    val todoCards = kanbanBoard.getCardByStatus(KanbanStatus.TO_DO)
    val inProgressCards = kanbanBoard.getCardByStatus(KanbanStatus.IN_PROGRESS)
    val reviewCards = kanbanBoard.getCardByStatus(KanbanStatus.REVIEW)
    val doneCards = kanbanBoard.getCardByStatus(KanbanStatus.DONE)

    when (taskModalMode) {
        TaskModalMode.CREATE -> if (isShowAddTaskModal) {
            ModalForm(
                modalMode = taskModalMode!!,
                assignee = TaskMockData.assignees,
                onDismissRequest = { isShowAddTaskModal = false },
                onCreate = { form, status ->
                    onAddCard(form, status)
                    isShowAddTaskModal = false
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

        TaskModalMode.EDIT -> if (isShowEditTaskModal) {
            ModalForm(
                editingCard = editingCard,
                modalMode = taskModalMode!!,
                assignee = TaskMockData.assignees,
                onDismissRequest = { isShowEditTaskModal = false },
                onEdit = { form, status ->
                    editingCard?.let { card ->
                        onEditCard(card.id, form, status)
                    }
                    isShowEditTaskModal = false
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "태스크가 수정되었습니다.",
                            duration = SnackbarDuration.Short,
                        )
                    }
                },
                onDelete = {
                    editingCard?.let { card ->
                        onDeleteCard(card.id)
                    }
                    isShowEditTaskModal = false
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "태스크가 삭제되었습니다.",
                            duration = SnackbarDuration.Short,
                        )
                    }
                },
            )
        }

        else -> ""
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
                onCreateClick = {
                    taskModalMode = TaskModalMode.CREATE
                    isShowAddTaskModal = true
                },
            )
        },
    ) { paddingValues ->
        val scrollstate = rememberScrollState()

        KanbanBody(
            todoCards = todoCards,
            inProgressCards = inProgressCards,
            reviewCards = reviewCards,
            doneCards = doneCards,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .background(Gray50)
                .padding(24.dp)
                .horizontalScroll(scrollstate),
            onCardClick = { card ->
                editingCard = card
                taskModalMode = TaskModalMode.EDIT
                isShowEditTaskModal = true
            },
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
        onEditCard = { _, _, _ -> },
        onDeleteCard = {},
        kanbanBoard = KanbanBoard(
            title = "compose",
            cards = listOf(),
        ),
        snackbarHostState = remember { SnackbarHostState() },
        scope = scope,
    )
}
