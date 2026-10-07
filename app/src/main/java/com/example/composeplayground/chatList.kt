package com.example.composeplayground
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeplayground.model.Chat
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
@Composable
fun chatList(
chats: List<Chat> , modifier: Modifier)
{
    LazyColumn(modifier=modifier,     verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    itemsIndexed(chats, key = { _, chat -> chat.id }) { index, chat ->
    chatRow(chat)
    if (index < chats.lastIndex) {
        HorizontalDivider()
    }
}
}
}
@Composable
fun chatRow(
    chat: Chat
) {
    Row(
        
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
        
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)

        ) {
            Text(
                text = chat.time,
                style = MaterialTheme.typography.labelSmall
            )

           if (chat.unreadCount > 0) {
               Box(
        modifier = Modifier
            .size(24.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = chat.unreadCount.toString(),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
        }
    }
}