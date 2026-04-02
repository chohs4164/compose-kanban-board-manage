package woowacourse.kanban.board.task.domain

data class KanbanProject(val projectTitle: String, val kanbanCards: List<KanbanCard> = emptyList()) {
    fun updateCardStatus(id: Long, status: KanbanStatus): KanbanProject {
        val newCard = getKanbanCard(id).updateStatus(status)
        val updatedCards = kanbanCards.map { card ->
            if (card.id == id) newCard else card
        }
        return copy(kanbanCards = updatedCards.toList())
    }

    fun addCard(kanbanCard: KanbanCard) = copy(kanbanCards = kanbanCards + kanbanCard)

    // 카드의 내용을 수정
    fun updateCard(id: Long, form: KanbanCardForm, status: KanbanStatus): KanbanProject {
        val updateCards = kanbanCards.map { card ->
            if (card.id == id) {
                card.update(form, status)
            } else {
                card
            }
        }
        return copy(kanbanCards = updateCards)
    }

    // 카드를 삭제
    fun deleteCard(id: Long): KanbanProject = copy(kanbanCards = kanbanCards.filterNot { it.id == id })

    fun getKanbanCard(id: Long): KanbanCard {
        val findKanbanCard = kanbanCards.find { it.id == id } ?: throw IllegalArgumentException("id가 ${id}인 카드를 찾지 못했습니다.")
        return findKanbanCard
    }

    fun getKanbanCardByBoardId(boardId: Int) = kanbanCards.filter { it.boardId == boardId }
}
