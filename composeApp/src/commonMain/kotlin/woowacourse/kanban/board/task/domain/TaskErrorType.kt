package woowacourse.kanban.board.task.domain

enum class TaskErrorType {
    TAG_DEFAULT, // 태스크 태그 통과
    TAG_FORMAT, // 태스크 태그의 형식이 올바르지 않음
    TAG_SIZE, // 태스크 태그의 갯수가 5개가 넘어가거나 한 태그의 글자수가 5자가 넘어간다.
    TITLE_MISSED, // 태스크 제목(필수사항)이 없음
    TITLE_DEFAULT, // 태스크 제목 통과
}
