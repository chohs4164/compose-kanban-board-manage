package woowacourse.kanban.board.task.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.task.domain.KanbanCard
import woowacourse.kanban.board.task.domain.KanbanStatus

/**
 * @param tags 최대 5개까지만 표시되는 태그 리스트입니다. 5개를 초과하면 상위 5개만 렌더링됩니다.
 */
@Composable
fun KanbanCardItem(
    kanbanCard: KanbanCard,
    modifier: Modifier = Modifier,
    onCardClick: (KanbanCard) -> Unit,
    onDragStart: (KanbanCard) -> Unit = {},
    onDragChange: (Offset) -> Unit = {},
    onDragEnd: () -> Unit = {},
    onDragCancel: () -> Unit = {},
) {
    var cardWindowPosition by remember { mutableStateOf(Offset.Zero) }
    Column(
        modifier = modifier
            .width(286.dp)
            .background(
                Color.White,
                RoundedCornerShape(10.dp),
            )
            .border(
                Dp.Hairline,
                Color.Gray,
                RoundedCornerShape(10.dp),
            )
            .onGloballyPositioned { cardWindowPosition = it.positionInWindow() }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { onDragStart(kanbanCard) },
                    onDrag = { change, _ ->
                        change.consume()
                        onDragChange(cardWindowPosition + change.position)
                    },
                    onDragEnd = { onDragEnd() },
                    onDragCancel = { onDragCancel() },
                )
            }
            .clickable { onCardClick(kanbanCard) }
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        KanbanCardTitle(kanbanCard.title)

        if (kanbanCard.content.isNotBlank()) {
            KanbanCardContent(kanbanCard.content)
        }

        if (kanbanCard.tags.isNotEmpty()) {
            KanbanCardTags(kanbanCard.tags)
        }

        HorizontalDivider(
            thickness = Dp.Hairline,
            color = Color.LightGray,
        )

        KanbanCardProfile(
            crewName = kanbanCard.assigneeName
        )
    }
}

data class KanbanCardInfo(val title: String, val crewName: String, val tags: List<String> = emptyList(), val content: String = "")

private class KanbanCardPreviewParameterProvider : PreviewParameterProvider<KanbanCard> {
    val tags = listOf(
        "컴포넌트",
        "성능",
    )
    override val values = sequenceOf(
        // 기본
        KanbanCard(
            id = 0,
            boardId = 0,
            title = "LazyColumn 컴포넌트 구현",
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            status = KanbanStatus.TO_DO,
            assigneeName = "바드",
            tags = tags,
        ),
        // 내용이 없는 경우
        KanbanCard(
            id = 0,
            boardId = 0,
            title = "LazyColumn 컴포넌트 구현",
            content = "",
            status = KanbanStatus.TO_DO,
            assigneeName = "바드",
            tags = tags,
        ),
        // 태그가 없는 경우
        KanbanCard(
            id = 0,
            boardId = 0,
            title = "LazyColumn 컴포넌트 구현",
            content = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다.",
            status = KanbanStatus.TO_DO,
            assigneeName = "바드",
            tags = emptyList(),
        ),
        // 내용과 태그가 없는 경우
        KanbanCard(
            id = 0,
            boardId = 0,
            title = "LazyColumn 컴포넌트 구현",
            content = "",
            status = KanbanStatus.TO_DO,
            assigneeName = "바드",
            tags = emptyList(),
        ),
    )
}

@Preview
@Composable
private fun KanbanCardItemPreview(@PreviewParameter(KanbanCardPreviewParameterProvider::class) card: KanbanCard) {
    val kanbanCard = KanbanCard(
        id = 0,
        boardId = 0,
        title = card.title,
        assigneeName = card.assigneeName,
        status = card.status,
        content = card.content,
        tags = card.tags,
    )
    KanbanCardItem(
        kanbanCard = kanbanCard,
        onCardClick = {},
    )
}
