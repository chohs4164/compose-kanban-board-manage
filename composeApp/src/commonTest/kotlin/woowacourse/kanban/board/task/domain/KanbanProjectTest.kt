package woowacourse.kanban.board.task.domain

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows


class KanbanProjectTest {
    @Test
    fun `boardId에 따라 KanbanCard를 분류한다`() {
        val kanbanProject = KanbanProject("프로젝트")

        val newKanbanProject = kanbanProject.addCard(
            createKanbanCard(
                0,
                0,
                status = KanbanStatus.IN_PROGRESS,
            ),
        ).addCard(
            createKanbanCard(
                1,
                0,
                status = KanbanStatus.DONE,
            ),
        ).addCard(
            createKanbanCard(
                2,
                1,
                status = KanbanStatus.DONE,
            ),
        )

        val boardIdList = newKanbanProject.getKanbanCardByBoardId(0)
        val boardIdList1 = newKanbanProject.getKanbanCardByBoardId(1)

        assertThat(boardIdList.size).isEqualTo(2)
        assertThat(boardIdList1.size).isEqualTo(1)
    }

    @Test
    fun `KanbanCard를 추가한다`() {
        val kanbanCard = createKanbanCard(
            cardId = 0,
            boardId = 0,
        )

        val kanbanProject = KanbanProject(projectTitle = "프로젝트")

        val newProject = kanbanProject.addCard(kanbanCard)

        assertThat(newProject.kanbanCards.size).isEqualTo(1)
    }

    @Test
    fun `KanbanCard의 Status를 수정한다`() {
        val kanbanProject = KanbanProject(
            projectTitle = "프로젝트",
        )

        val newKanbanProject = kanbanProject.addCard(
            createKanbanCard(
                0,
                0,
                status = KanbanStatus.IN_PROGRESS,
            ),
        ).addCard(
            createKanbanCard(
                1,
                0,
                status = KanbanStatus.DONE,
            ),
        ).addCard(
            createKanbanCard(
                2,
                0,
                status = KanbanStatus.DONE,
            ),
        )

        val updateKanbanProject = newKanbanProject.updateCardStatus(
            id = 1,
            status = KanbanStatus.TO_DO,
        )

        assertThat(updateKanbanProject.getKanbanCard(1)?.status).isEqualTo(KanbanStatus.TO_DO)
    }

    @Test
    fun `존재하지 않는 카드 ID로 상태를 변경하면 예외가 발생한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            val kanbanProject = KanbanProject(
                projectTitle = "4주차 미션 보드",
            )
            val newKanbanProject = kanbanProject.addCard(
                createKanbanCard(
                    cardId = 2,
                    boardId = 0,
                    status = KanbanStatus.IN_PROGRESS,
                ),
            )
            val updateKanbanProject = newKanbanProject.updateCardStatus(
                id = 5,
                status = KanbanStatus.DONE,
            )
        }
    }

    @Test
    fun `DONE 카드를 TO_DO로 이동하면 doneCount가 감소한다`() {
        val project = KanbanProject("프로젝트")
            .addCard(createKanbanCard(cardId = 1, boardId = 0, status = KanbanStatus.DONE))
            .addCard(createKanbanCard(cardId = 2, boardId = 0, status = KanbanStatus.TO_DO))

        val Board1 = KanbanBoard(
            title = "보드",
            cards = project.getKanbanCardByBoardId(0),
        )
        val updateProject = project.updateCardStatus(
            id = 1,
            status = KanbanStatus.TO_DO,
        )
        val Board2 = KanbanBoard(
            title = "보드",
            cards = project.getKanbanCardByBoardId(0),
        )

        assertThat(Board1.doneCount).isEqualTo(1)
        assertThat(Board2.doneCount).isEqualTo(0)
        assertThat(Board2.doneCount).isEqualTo(Board2.totalCount)
    }

    private fun createKanbanCard(cardId: Long, boardId: Int, status: KanbanStatus = KanbanStatus.TO_DO) = KanbanCard(
        id = cardId,
        boardId = boardId,
        title = "제목",
        assigneeName = "담당자",
        status = status,
    )
}
