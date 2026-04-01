package woowacourse.kanban.board.task.ui.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModalOptionAssignee(modifier: Modifier = Modifier, name: String) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "담당자 아이콘",
            tint = Color.Gray,
        )

        Text(
            text = name,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun ModalOptionAssigneePreview() {
    ModalOptionAssignee(
        modifier = Modifier.background(Color.White),
        name = "다이노",
    )
}
