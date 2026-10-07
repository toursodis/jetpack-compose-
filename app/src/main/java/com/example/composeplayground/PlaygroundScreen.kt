package com.example.composeplayground

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.theme.AppTheme
import com.example.composeplayground.model.Chat
import com.example.composeplayground.data.mockChats
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext

/**
 * Raw starter playground screen.
 * Feel free to replace the contents of this composable with your own practice UI!
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun conversationPage(modifier: Modifier = Modifier) {
    var chats by remember {
    mutableStateOf(mockChats)
    }
    var addChat by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("hamfa  chat test") },
                    actions = {
                        IconButton(onClick ={ Toast.makeText(context,"search was clicked",Toast.LENGTH_SHORT).show()} ){
                            Icon(
                                imageVector=Icons.Default.Search,
                                "search Users"
                                )
                        }
                    },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                
                ),

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { addChat=!addChat},
                containerColor = MaterialTheme.colorScheme.primary
                
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add chat"
                    
                )
            }
        }
    ) { innerPadding ->
        chatList(chats,
        modifier = Modifier.padding(innerPadding)
)

        if (addChat) {
            var name by remember { mutableStateOf("") }
            var message by remember { mutableStateOf("") }

            AlertDialog(
                onDismissRequest = { addChat = false },
                title = { Text("Add chat") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            label = { Text("Message") }
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        enabled = name.isNotBlank() && message.isNotBlank(),
                        onClick = {
                            chats = chats + Chat(
                                id = (chats.maxOfOrNull { it.id } ?: 0) + 1,
                                name = name.trim(),
                                lastMessage = message.trim(),
                                time = "Now"
                            )
                            addChat = false
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { addChat = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
