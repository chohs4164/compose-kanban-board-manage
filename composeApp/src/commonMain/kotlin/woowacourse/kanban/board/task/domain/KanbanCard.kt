package woowacourse.kanban.board.task.domain

data class KanbanCard(
    val id: Long,
    val boardId: Int,
    val title: String,
    val content: String = "",
    val status: KanbanStatus,
    val assigneeName: String?,
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
                tags = form.tags,
            )
        }
    }

    // 상태 전이 로직에 따른 스낵바를 띄워주기 위함
    fun updateStatus(next: KanbanStatus): KanbanCard {
        require(status.canTranslationTo(next)) {
            "해당 상태로 옮길 수 없습니다."
        }
        require(
            !(status == KanbanStatus.TO_DO &&
                    next == KanbanStatus.IN_PROGRESS &&
                    assigneeName == null),
        ) {
            "담당자를 지정해야 상태를 옮길 수 있습니다."
        }

        return copy(status = next)
    }

    fun update(form: KanbanCardForm, status: KanbanStatus): KanbanCard {
        return copy(
            title = form.title,
            content = form.content,
            tags = form.tags,
            status = status,
            assigneeName = form.assigneeName,
        )
    }

    fun validateDeletable() {
        require(status != KanbanStatus.REVIEW && status != KanbanStatus.DONE) {
            "해당 상태에서는 태스크 삭제가 불가합니다."
        }
    }
}
