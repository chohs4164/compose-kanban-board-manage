package woowacourse.kanban.board.task.ui.card

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.runComposeUiTest
import org.junit.Test
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanStatus

@OptIn(ExperimentalTestApi::class)
class KanbanCardTagsTest {
    @Test
    fun `빈 태그 리스트 테스트`() = runComposeUiTest {
        val tags = emptyList<String>()
        val kanbanCard = KanbanCard(
            id = 0,
            boardId = 0,
            title = "LazyColumn 컴포넌트 구현",
            assigneeName = "바드",
            status = KanbanStatus.TO_DO,
            tags = tags,
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
        )
        setContent {
            KanbanCardItem(
                kanbanCard = kanbanCard,
            )
        }

        onNodeWithContentDescription("칸반 카드 태그 목록").assertDoesNotExist()
    }
}
