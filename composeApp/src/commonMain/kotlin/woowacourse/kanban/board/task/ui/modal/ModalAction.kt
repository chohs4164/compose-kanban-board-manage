package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import kanbanboard.composeapp.generated.resources.button_delete
import org.jetbrains.compose.resources.stringResource
import woowacourse.kanban.board.theme.Red500
import woowacourse.kanban.board.theme.Violet600

@Composable
fun ModalAction(
    primaryText: String,
    isPrimaryEnabled: Boolean,
    onDismissRequest: () -> Unit,
    onPrimaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    extraAction: (@Composable () -> Unit)? = null,
) {
    HorizontalDivider(
        thickness = Dp.Hairline,
        color = Color.LightGray,
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ModalActionButton(
                text = stringResource(Res.string.button_cancel),
                onClick = onDismissRequest,
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.Black,
                ),
            )

            extraAction?.invoke()

            ModalActionButton(
                text = primaryText,
                enabled = isPrimaryEnabled,
                onClick = onPrimaryClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Violet600,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White,
                ),
            )
        }
    }
}

@Composable
fun ModalDeleteAction(onDeleteClick: () -> Unit, modifier: Modifier = Modifier) {
    ModalActionButton(
        text = stringResource(Res.string.button_delete),
        onClick = onDeleteClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Red500,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White,
        ),
        modifier = modifier,
    )
}

@Composable
private fun ModalActionButton(
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .height(44.dp)
            .width(68.dp),
        onClick = onClick,
        colors = colors,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
private fun ModalCreateActionPreview() {
    ModalAction(
        primaryText = "생성",
        isPrimaryEnabled = true,
        onDismissRequest = {},
        onPrimaryClick = {},
    )
}

@Preview
@Composable
private fun ModalEditActionPreview() {
    ModalAction(
        primaryText = "수정",
        isPrimaryEnabled = true,
        onDismissRequest = {},
        onPrimaryClick = {},
        extraAction = {
            ModalDeleteAction(onDeleteClick = {})
        },
    )
}
