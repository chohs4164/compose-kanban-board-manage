package woowacourse.kanban.board.task.domain

object TaskValidator {
    fun validateTitle(title: String): TaskErrorType {
        if (title.isBlank()) return TaskErrorType.TITLE_MISSED
        return TaskErrorType.TITLE_DEFAULT
    }

    fun validateTags(tagInput: String): TaskErrorType {
        if (tagInput.isEmpty()) return TaskErrorType.TAG_DEFAULT

        val tags = tagInput.split(",").map { it.trim() }

        if (tags.any { it.isBlank() }) {
            return TaskErrorType.TAG_FORMAT
        }
        if (tags.size > 5 || tags.any { it.length > 5 }) {
            return TaskErrorType.TAG_SIZE
        }
        return TaskErrorType.TAG_DEFAULT
    }
}
