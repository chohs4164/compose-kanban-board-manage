package woowacourse.kanban.board.task.ui.card

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.assertFailsWith
import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.kanban.board.task.domain.KanbanCardForm

@OptIn(ExperimentalTestApi::class)
class KanbanCardFormTest {
    @Test
    fun `제목에 비어있거나 공백이 입력되면 에러가 발생`() = runComposeUiTest {
        // when & then
        assertFailsWith<IllegalArgumentException> {
            KanbanCardForm(
                "",
                "바드",
            )
            KanbanCardForm(
                "      ",
                "바드",
            )
        }
    }

    @Test
    fun `담당자가 비어있거나 공백이 입력되면 에러가 발생`() = runComposeUiTest {
        // when & then
        assertFailsWith<IllegalArgumentException> {
            KanbanCardForm(
                "제목 이름",
                "",
            )
            KanbanCardForm(
                "제목 이름",
                "         ",
            )
        }
    }

    @Test
    fun `태그의 개수가 5개 이상이면 에러가 발생`() = runComposeUiTest {
        assertFailsWith<IllegalArgumentException> {
            KanbanCardForm(
                title = "제목이름",
                assigneeName = "바드",
                tags = listOf(
                    "태그1",
                    "태그2",
                    "태그3",
                    "태그4",
                    "태그5",
                    "태그6",
                ),
            )
        }
    }

    @Test
    fun `태그가 5글자 이상이면 에러가 발생`() = runComposeUiTest {
        assertFailsWith<IllegalArgumentException> {
            KanbanCardForm(
                title = "제목 이름",
                assigneeName = "바드",
                tags = listOf("긴 태그이름입니다."),
            )
        }
    }

    @Test
    fun `정상 테스트`() = runComposeUiTest {
        val title = "제목 이름"
        val crewName = "바드"
        val tags = listOf(
            "태그1",
            "태그2",
            "태그3",
        )
        val content = "칸반 카드 내용"

        val formInfo = KanbanCardForm(
            title = title,
            assigneeName = crewName,
            tags = tags,
            content = content,
        )

        Assertions.assertThat(formInfo.title).isEqualTo("제목 이름")
        Assertions.assertThat(formInfo.assigneeName).isEqualTo("바드")
        Assertions.assertThat(formInfo.tags).isEqualTo(
            listOf(
                "태그1",
                "태그2",
                "태그3",
            ),
        )
        Assertions.assertThat(formInfo.content).isEqualTo("칸반 카드 내용")
    }

    @Test
    fun `'ToDo' 상태의 'KanbanCard'는 담당자 미지정을 허용한다`() = runComposeUiTest {
    }

    @Test
    fun `Review or Done 상태에서 태스크를 삭제하려고 할 때 스낵 바 노출`() = runComposeUiTest {
    }

    @Test
    fun `불가능한 상태 전이를 시도할 때 스낵바 노출`() = runComposeUiTest {
    }

    @Test
    fun `담당자를 지정하지 않고 In Progress로 전이할 때 스낵바 노출`() = runComposeUiTest {
    }

    @Test
    fun `태스크가 수정되었을 때 스낵바 노출`() = runComposeUiTest {
    }

    @Test
    fun `태스크가 삭제되었을 때 스낵바 노출`() = runComposeUiTest {
    }
}
