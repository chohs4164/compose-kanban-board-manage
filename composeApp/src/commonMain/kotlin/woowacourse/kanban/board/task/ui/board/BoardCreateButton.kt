package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.button_task_create
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.theme.Violet600

@Composable
fun BoardCreateButton(modifier: Modifier = Modifier, onCreateClick: () -> Unit) {
    Button(
        modifier = modifier.height(40.dp),
        onClick = onCreateClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Violet600,
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            modifier = Modifier.size(20.dp),
            contentDescription = "추가",
        )
        Text(
            text = stringResource(Res.string.button_task_create),
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
fun BoardCreateButtonPreview() {
    BoardCreateButton(
        modifier = Modifier.background(Color.White),
        onCreateClick = {},
    )
}
