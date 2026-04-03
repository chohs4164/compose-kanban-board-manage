package woowacourse.kanban.board.task.domain

data class KanbanProject(val projectTitle: String, val boards: List<KanbanBoard> = emptyList()) {
    fun getBoard(boardId: Int): KanbanBoard? = boards.find { it.boardId == boardId }

    fun getBoardTitles(): List<String> = boards.map { it.title }

    fun updateCardStatus(boardId: Int, cardId: String, status: KanbanStatus): KanbanProject? {
        val targetBoard = getBoard(boardId) ?: return null
        val updateBoard = targetBoard.updateCardStatus(cardId = cardId, status = status) ?: return null
        val newBoards = boards.map {
            if (it.boardId == boardId) updateBoard else it
        }
        return copy(boards = newBoards)
    }

    fun addBoardCard(boardId: Int, card: KanbanCard): KanbanProject? {
        val targetBoard = getBoard(boardId) ?: return null
        val addBoard = targetBoard.addCard(card)
        val newBoards = boards.map {
            if (it.boardId == boardId) addBoard else it
        }
        return copy(boards = newBoards)
    }

    fun updateCard(boardId: Int, cardId: String, form: KanbanCardForm, status: KanbanStatus): KanbanProject? {
        val targetBoard = getBoard(boardId) ?: return null
        val updateBoard = targetBoard.updateCard(cardId = cardId, form = form, status = status) ?: return null
        val newBoards = boards.map {
            if (it.boardId == boardId) updateBoard else it
        }
        return copy(boards = newBoards)
    }

    fun deleteCard(boardId: Int, cardId: String): KanbanProject? {
        val targetBoard = getBoard(boardId) ?: return null
        val deleteBoard = targetBoard.deleteCard(cardId) ?: return null
        val newBoards = boards.map {
            if (it.boardId == boardId) deleteBoard else it
        }
        return copy(boards = newBoards)
    }
}
