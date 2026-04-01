package woowacourse.kanban.board.task.ui.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.theme.Indigo50
import woowacourse.kanban.board.theme.Violet700

@Composable
fun KanbanProjectSideBarButton(isSelected: Boolean, buttonText: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val containerColor = if (isSelected) Indigo50 else Color.Transparent
    val contentColor = if (isSelected) Violet700 else Color.Black
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .background(color = containerColor)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp,
            ),
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = contentColor,
        )
    }
}

@Preview
@Composable
private fun KanbanProjectSideBarButtonPreview() {
    Column {
        KanbanProjectSideBarButton(
            isSelected = true,
            buttonText = "Compose1",
            onClick = {},
        )
        KanbanProjectSideBarButton(
            isSelected = false,
            buttonText = "Compose2",
            onClick = {},
            modifier = Modifier.background(Color.White),
        )
    }
}
