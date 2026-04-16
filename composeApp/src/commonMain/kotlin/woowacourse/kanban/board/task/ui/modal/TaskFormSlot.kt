package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskFormSlot(
    title: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(
                1.dp,
                Color.LightGray,
                RoundedCornerShape(10.dp),
            ),
    ) {
        title()

        HorizontalDivider(color = Color.LightGray)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content,
        )

        actions()
    }
}
