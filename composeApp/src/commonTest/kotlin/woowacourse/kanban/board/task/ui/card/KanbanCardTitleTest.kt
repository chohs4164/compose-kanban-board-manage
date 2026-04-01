package woowacourse.kanban.board.task.ui.card

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import kotlin.test.assertEquals
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class KanbanCardTitleTest {
    @Test
    fun `긴 제목 말줄임표 발생 테스트`() = runComposeUiTest {
        val title = "너무 너무 긴 제목너무 너무 긴 제목너무 너무 긴 제목"

        setContent {
            KanbanCardTitle(title, modifier = Modifier.testTag("title"))
        }

        val textLayoutResult = mutableListOf<TextLayoutResult>()
        onNodeWithTag("title", useUnmergedTree = true).performSemanticsAction(GetTextLayoutResult) {
            it(textLayoutResult)
        }

        assertEquals(textLayoutResult.first().hasVisualOverflow, true)
    }
}
