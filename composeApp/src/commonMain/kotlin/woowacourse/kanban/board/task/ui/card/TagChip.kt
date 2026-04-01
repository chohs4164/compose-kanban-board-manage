package woowacourse.kanban.board.task.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagChip(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.LightGray, CircleShape)
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Text(
            text = name,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
private fun TagChipPreview() {
    TagChip(name = "다섯글자.")
}
