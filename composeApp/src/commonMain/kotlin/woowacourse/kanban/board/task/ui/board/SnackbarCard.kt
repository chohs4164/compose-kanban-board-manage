package woowacourse.kanban.board.task.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.theme.SnackBarBackground

@Composable
fun SnackBarCard(modifier: Modifier = Modifier, message: String, onDismiss: () -> Unit) {
    Row(
        modifier = modifier
            .width(344.dp)
            .height(48.dp)
            .background(
                color = SnackBarBackground,
                shape = RoundedCornerShape(4.dp),
            )
            .padding(
                vertical = 14.dp,
                horizontal = 16.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = message,
            fontSize = 14.sp,
            color = Color.White,
        )

        Icon(
            imageVector = Icons.Default.Close,
            modifier = modifier.size(24.dp).clickable(onClick = { onDismiss() }),
            contentDescription = "스낵바 닫기",
            tint = Color.White,
        )
    }
}

@Preview
@Composable
private fun SnackbarCardpreview() {
    SnackBarCard(
        message = "새로운 태스크가 추가되었습니다.",
        onDismiss = {},
    )
}