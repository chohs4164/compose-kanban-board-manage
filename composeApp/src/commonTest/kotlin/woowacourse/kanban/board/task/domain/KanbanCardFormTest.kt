package woowacourse.kanban.board.task.domain

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.assertFailsWith
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

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
                crewName = "바드",
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
                crewName = "바드",
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
            crewName = crewName,
            tags = tags,
            content = content,
        )

        assertThat(formInfo.title).isEqualTo("제목 이름")
        assertThat(formInfo.crewName).isEqualTo("바드")
        assertThat(formInfo.tags).isEqualTo(
            listOf(
                "태그1",
                "태그2",
                "태그3",
            ),
        )
        assertThat(formInfo.content).isEqualTo("칸반 카드 내용")
    }
}
