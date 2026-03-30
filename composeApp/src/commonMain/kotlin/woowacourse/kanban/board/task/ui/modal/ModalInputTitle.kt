package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.label_description
import kanbanboard.composeapp.generated.resources.label_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ModalInputTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = title,
        fontSize = 20.sp,
    )
}

@Preview
@Composable
private fun ModalInputTitlePreview() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(5.dp),
    ) {
        ModalInputTitle(stringResource(Res.string.label_title))
    }
}
