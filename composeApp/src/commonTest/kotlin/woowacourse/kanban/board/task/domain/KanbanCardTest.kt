package woowacourse.kanban.board.task.domain

import kotlin.test.assertFailsWith
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class KanbanCardTest {
    @Test
    fun `KanbanCard의 Status가 변경된다`() {
        val kanbanCard = KanbanCard(
            title = "칸반제목 1",
            assigneeName = "담당자 1",
            status = KanbanStatus.TO_DO,
        )

        val updateKanbanCard = kanbanCard.updateStatus(next = KanbanStatus.IN_PROGRESS)

        assertThat(updateKanbanCard.status).isEqualTo(KanbanStatus.IN_PROGRESS)
    }

    @Test
    fun `담당자가 없으면 To Do에서 In Progress로 옮길 수 없다`() {
        val kanbanCard = KanbanCard(
            title = "칸반제목 1",
            assigneeName = null,
            status = KanbanStatus.TO_DO,
        )

        assertFailsWith<IllegalArgumentException> {
            kanbanCard.updateStatus(KanbanStatus.IN_PROGRESS)
        }
    }

    @Test
    fun `제목에 비어있거나 공백이 입력되면 에러가 발생한다`() {
        assertFailsWith<IllegalArgumentException> {
            KanbanCard(
                title = "",
                assigneeName = "바드",
                status = KanbanStatus.TO_DO,
            )
        }
        assertFailsWith<IllegalArgumentException> {
            KanbanCard(
                title = "      ",
                assigneeName = "바드",
                status = KanbanStatus.TO_DO,
            )
        }
    }

    @Test
    fun `태그의 개수가 5개 이상이면 에러가 발생한다`() {
        assertFailsWith<IllegalArgumentException> {
            KanbanCard(
                title = "제목이름",
                assigneeName = "바드",
                tags = listOf("태그1", "태그2", "태그3", "태그4", "태그5", "태그6"),
                status = KanbanStatus.TO_DO,
            )
        }
    }

    @Test
    fun `태그가 5글자 이상이면 에러가 발생한다`() {
        assertFailsWith<IllegalArgumentException> {
            KanbanCard(
                title = "제목 이름",
                assigneeName = "바드",
                tags = listOf("긴 태그이름입니다."),
                status = KanbanStatus.TO_DO,
            )
        }
    }

    @Test
    fun `정상 테스트`() {
        val card = KanbanCard(
            title = "제목 이름",
            assigneeName = "바드",
            tags = listOf("태그1", "태그2", "태그3"),
            content = "칸반 카드 내용",
            status = KanbanStatus.TO_DO,
        )

        assertThat(card.title).isEqualTo("제목 이름")
        assertThat(card.assigneeName).isEqualTo("바드")
        assertThat(card.tags).containsExactly("태그1", "태그2", "태그3")
        assertThat(card.content).isEqualTo("칸반 카드 내용")
    }
}
