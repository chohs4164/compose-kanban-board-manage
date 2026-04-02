package woowacourse.kanban.board.task.domain

data class KanbanCard(
    val id: Long,
    val boardId: Int,
    val title: String,
    val status: KanbanStatus,
    val assigneeName: String,
    val content: String = "",
    val tags: List<String> = emptyList(),
) {
    companion object {
        fun create(newId: Long, boardId: Int, form: KanbanCardForm, status: KanbanStatus): KanbanCard {
            return KanbanCard(
                id = newId,
                boardId = boardId,
                title = form.title,
                content = form.content,
                status = status,
                assigneeName = form.assigneeName,
            )
        }
    }

    fun updateStatus(status: KanbanStatus): KanbanCard {
        return copy(status = status)
    }

    fun update(form: KanbanCardForm,status: KanbanStatus): KanbanCard{
        return copy(
            title = form.title,
            content = form.content,
            tags = form.tags,
            status = status,
            assigneeName = form.assigneeName,
        )
    }
}
