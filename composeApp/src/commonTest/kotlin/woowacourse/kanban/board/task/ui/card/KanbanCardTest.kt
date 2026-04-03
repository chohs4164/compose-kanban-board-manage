package woowacourse.kanban.board.task.ui.card

import androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import kotlin.test.assertEquals
import org.junit.Test
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanStatus

@OptIn(ExperimentalTestApi::class)
class KanbanCardTest {
    @Test
    fun `모든 필드가 있는 카드 테스트`() = runComposeUiTest {
        val kanbanCard = KanbanCard(
            title = "LazyColumn 컴포넌트 구현",
            assigneeName = "바드",
            tags = listOf("컴포넌트", "성능"),
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            status = KanbanStatus.TO_DO,
        )

        setContent {
            KanbanCardItem(
                kanbanCard = kanbanCard,
                onCardClick = {},
            )
        }

        onNodeWithText("LazyColumn 컴포넌트 구현").assertExists()
        onNodeWithText("바드").assertExists()
        onNodeWithText("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.").assertExists()
        onNodeWithText("컴포넌트").assertExists()
        onNodeWithText("성능").assertExists()
    }

    @Test
    fun `content가 비어 있는 경우 UI 테스트`() = runComposeUiTest {
        val kanbanCard = KanbanCard(
            title = "LazyColumn 컴포넌트 구현",
            assigneeName = "바드",
            tags = listOf("컴포넌트", "성능"),
            content = "",
            status = KanbanStatus.TO_DO,
        )

        setContent {
            KanbanCardItem(
                kanbanCard = kanbanCard,
                onCardClick = {},
            )
        }

        onNodeWithText("세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.").assertDoesNotExist()
    }

    @Test
    fun `긴 담당자 말줄임표 발생 테스트`() = runComposeUiTest {
        val assigneeName = "너무 긴 담당자 이름너무 긴 담당자 이름너무 긴 담당자 이름"
        val kanbanCard = KanbanCard(
            title = "제목",
            assigneeName = assigneeName,
            status = KanbanStatus.TO_DO,
        )

        setContent {
            KanbanCardItem(
                kanbanCard = kanbanCard,
                onCardClick = {},
            )
        }

        val textLayoutResult = mutableListOf<TextLayoutResult>()
        onNodeWithText(assigneeName, useUnmergedTree = true).performSemanticsAction(GetTextLayoutResult) {
            it(textLayoutResult)
        }

        assertEquals(textLayoutResult.first().hasVisualOverflow, true)
    }
}
