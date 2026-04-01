package woowacourse.kanban.board.task.domain

data class KanbanCardForm(val title: String, val crewName: String, val tags: List<String> = emptyList(), val content: String = "") {
    init {
        require(title.isNotBlank()) { "칸반 카드의 제목은 공백이거나 비어있을수 없습니다." }
        require(crewName.isNotBlank()) { "칸반 카드의 담당자는 공백이거나 비어있을수 없습니다." }
        tags.forEach { require(it.isNotBlank() && it.length <= 5) { "칸반 카드의 태그의 형식이 올바르지 않습니다." } }
        require(tags.size <= 5) { "태그의 개수는 최대 5개까지 가능합니다." }
    }
}
