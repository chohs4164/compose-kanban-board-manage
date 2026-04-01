package woowacourse.kanban.board.task.ui.card

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import kotlin.test.assertEquals
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class KanbanCardContentTest {
    @Test
    fun `긴 내용 말줄임표 발생 테스트`() = runComposeUiTest {
        val content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.".repeat(3)

        setContent {
            KanbanCardContent(
                content = content,
                modifier = Modifier.testTag("content").width(100.dp),
            )
        }

        val textLayoutResult = mutableListOf<TextLayoutResult>()
        onNodeWithTag("content").performSemanticsAction(GetTextLayoutResult) {
            it(textLayoutResult)
        }

        assertEquals(textLayoutResult.first().hasVisualOverflow, true)
    }
}
