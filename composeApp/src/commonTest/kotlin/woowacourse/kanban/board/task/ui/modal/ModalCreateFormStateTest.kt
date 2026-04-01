package woowacourse.kanban.board.task.ui.modal

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import woowacourse.kanban.board.task.domain.TaskErrorType

class ModalCreateFormStateTest {
    private lateinit var state: ModalCreateFormState

    @BeforeTest
    fun setUp() {
        state = ModalCreateFormState()
    }

    @Test
    fun `빈 제목은 유효하지 않다`() {
        state.title = ""
        state.validate()
        assertFalse(state.isValidTitle)
        assertEquals(TaskErrorType.TITLE_MISSED, state.validTitle)
    }

    @Test
    fun `제목이 비어있지 않으면 유효하다`() {
        state.title = "태스크 제목"
        state.validate()
        assertTrue(state.isValidTitle)
        assertEquals(TaskErrorType.TITLE_DEFAULT, state.validTitle)
    }

    @Test
    fun `태그가 비어있으면 유효하다`() {
        state.tag = ""
        state.validate()
        assertTrue(state.isValidTag)
        assertEquals(TaskErrorType.TAG_DEFAULT, state.validTag)
    }

    @Test
    fun `태그가 5개 이하이고 각각 5자 이하이면 유효하다`() {
        state.tag = "태그1,태그2,태그3,태그4,태그5"
        state.validate()
        assertTrue(state.isValidTag)
        assertEquals(TaskErrorType.TAG_DEFAULT, state.validTag)
    }

    @Test
    fun `태그가 5개를 초과하면 유효하지 않다`() {
        state.tag = "1,2,3,4,5,6"
        state.validate()
        assertFalse(state.isValidTag)
        assertEquals(TaskErrorType.TAG_SIZE, state.validTag)
    }

    @Test
    fun `태그 중 하나라도 5자를 초과하면 유효하지 않다`() {
        state.tag = "정상태그,육글자태그임"
        state.validate()
        assertFalse(state.isValidTag)
        assertEquals(TaskErrorType.TAG_SIZE, state.validTag)
    }

    @Test
    fun `태그 형식이 올바르지 않으면 유효하지 않다 (쉼표만 있는 경우)`() {
        state.tag = "태그1,,태그2"
        state.validate()
        assertFalse(state.isValidTag)
        assertEquals(TaskErrorType.TAG_FORMAT, state.validTag)
    }
}
