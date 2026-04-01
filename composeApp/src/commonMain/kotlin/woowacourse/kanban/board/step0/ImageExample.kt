package woowacourse.kanban.board.step0

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.image_example
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageExample() {
    Image(
        painter = painterResource(resource = Res.drawable.image_example),
        contentDescription = "예시 이미지 입니다.",
    )
}

@Preview
@Composable
private fun ImageExamplePreview() {
    ImageExample()
}
