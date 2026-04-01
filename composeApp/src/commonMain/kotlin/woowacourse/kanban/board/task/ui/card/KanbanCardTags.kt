package woowacourse.kanban.board.task.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun KanbanCardTags(tags: List<String>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.semantics { contentDescription = "칸반 카드 태그 목록" },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tags.forEach { tag -> TagChip(name = tag) }
    }
}
