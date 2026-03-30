package woowacourse.kanban.board.task.domain

data class KanbanCard(
    val id: Long,
    val boardId: Int,
    val title: String,
    val assigneeName: String,
    val status: KanbanStatus,
    val content: String = "",
    val tags: List<String> = emptyList(),
) {
    companion object {
        fun create(
            newId:Long,
            boardId: Int,
            form: KanbanCardForm,
            status: KanbanStatus,
        ): KanbanCard {
            return KanbanCard(
                id = newId,
                boardId = boardId,
                title = form.title,
                content = form.content,
                assigneeName = form.crewName,
                status = status,
            )
        }
    }

    fun updateStatus(status: KanbanStatus): KanbanCard {
        return copy(status = status)
    }
}
