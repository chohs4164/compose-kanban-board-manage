package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.button_cancel
import kanbanboard.composeapp.generated.resources.button_create
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.theme.Violet600

@Composable
fun ModalAction(
    isValidTitle: Boolean,
    isValidTag: Boolean,
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        thickness = Dp.Hairline,
        color = Color.LightGray,
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Button(
            modifier = Modifier
                .height(44.dp)
                .width(68.dp),
            onClick = onDismissRequest,
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black,
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = stringResource(Res.string.button_cancel),
                fontSize = 16.sp,
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp),
        )

        Button(
            modifier = Modifier
                .height(44.dp)
                .width(68.dp),
            enabled = isValidTitle && isValidTag,
            onClick = onClick,
            colors = ButtonColors(
                containerColor = Violet600,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White,
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = stringResource(Res.string.button_create),
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
private fun ModalActionPreview() {
    Box(modifier = Modifier.padding(10.dp)) {
        ModalAction(
            isValidTag = true,
            isValidTitle = true,
            onDismissRequest = {},
            onClick = {},
        )
    }
}
