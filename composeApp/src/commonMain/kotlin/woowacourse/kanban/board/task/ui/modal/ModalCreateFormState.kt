package woowacourse.kanban.board.task.ui.modal

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanCardForm
import woowacourse.kanban.board.task.domain.KanbanStatus
import woowacourse.kanban.board.task.domain.TaskErrorType
import woowacourse.kanban.board.task.domain.TaskValidator

class ModalCreateFormState() {
    // 태스크 제목
    var title by mutableStateOf("")

    // 태스크 설명
    var content by mutableStateOf("")

    // 태스크 태그
    var tag by mutableStateOf("")

    // 태스크 상태 with index
    var status by mutableIntStateOf(0)

    // 태스크 담당자 with index
    var assignee by mutableIntStateOf(0)

    // 태스크 제목 유효성 검사를 통과한 제목
    var validTitle by mutableStateOf(TaskErrorType.TITLE_DEFAULT)

    // 태스크 제목이 유효한지 여부
    val isValidTitle by derivedStateOf {
        validTitle == TaskErrorType.TITLE_DEFAULT
    }

    // 태스크 태그 유효성 검사를 통과한 태그
    var validTag by mutableStateOf(TaskErrorType.TAG_DEFAULT)

    // 태스크 태그가 유효한지 여부
    val isValidTag by derivedStateOf {
        validTag == TaskErrorType.TAG_DEFAULT
    }

    // 제목을 다시 입력하기 시작할 때, 이전 검증 에러 표시를 지우기 위함
    fun resetTitleError() {
        validTitle = TaskErrorType.TITLE_DEFAULT
    }

    // 태그를 다시 입력하기 시작할 때, 이전 검증 에러 표시를 지우기 위함
    fun resetTagError() {
        validTag = TaskErrorType.TAG_DEFAULT
    }

    // 태스크 제목과 태그가 유효성 검사를 통과했다는 상태를 알리기 위함
    fun validate(): Boolean {
        validTitle = TaskValidator.validateTitle(title)
        validTag = TaskValidator.validateTags(tag)
        return validTitle == TaskErrorType.TITLE_DEFAULT && validTag == TaskErrorType.TAG_DEFAULT
    }

    // 도메인에서 쓸 제출 데이터
    fun toKanbanCardForm(assignees: List<String>): KanbanCardForm {
        val tags = if (tag.isEmpty()) emptyList() else tag.split(",").map { it.trim() }
        return KanbanCardForm(
            title = title,
            content = content,
            tags = tags,
            assigneeName = assignees[assignee],
        )
    }

    // 도메인에 상태를 넘거주기 위함
    fun toKanbanCardStatus() = KanbanStatus.entries[status]

    companion object{
        fun from(card: KanbanCard,assignees: List<String>): ModalCreateFormState{
            return ModalCreateFormState().apply{
                title = card.title
                content = card.content
                tag = card.tags.joinToString(",")
                status = KanbanStatus.entries.indexOf(card.status)
                assignee = assignees.indexOf(card.assigneeName).coerceAtLeast(0) // 카드의 담당자 이름이 assignees와 매칭되지 않는다면 첫번째 담당자로
            }
        }
    }
}
