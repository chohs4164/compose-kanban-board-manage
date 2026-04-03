package woowacourse.kanban.board.task.domain

data class KanbanBoard(val title: String, val cards: List<KanbanCard> = emptyList()) {
    val totalCount: Int get() = cards.size
    val doneCount: Int get() = cards.count { it.status == KanbanStatus.DONE }

    fun getCardByStatus(status: KanbanStatus) = cards.filter { it.status == status }
}
