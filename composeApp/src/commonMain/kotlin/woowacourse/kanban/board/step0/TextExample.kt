package woowacourse.kanban.board.step0

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TextExample() {
    Text(
        text = "Jetpack Compose Text 실습",
        fontSize = 22.sp,
        color = Color.Blue,
        fontWeight = FontWeight.Bold,
    )
}

@Preview
@Composable
private fun TextExamplePreview() {
    TextExample()
}
