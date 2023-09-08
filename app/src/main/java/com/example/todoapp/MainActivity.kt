@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Page()
                }
            }
        }
    }
}


@Composable
fun Page() {

    val tasks = remember {
        mutableStateListOf(
            "Task 1",
        )
    }

    val inputText = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
//            Title of the app
            Text(
                text = "My First Todo App!",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(16.dp)
            )
//            Input box and button
            OutlinedTextField(
                value = inputText.value,
                label = { Text(text = "Task") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    inputText.value = it
                })

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                tasks.add(inputText.value)
                inputText.value = ""
            }) {
                Text(text = "Add Task")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tasks List",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                FilledTonalButton(
                    onClick = {
                        tasks.clear()
                    }) {
                    Text(text = "Clear All")
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(tasks) { index, task ->
                    val checked = remember { mutableStateOf(false) }
                    ListItem(
                        headlineText = {
                            Text(
                                text = task,
                                textDecoration = if (checked.value) TextDecoration.LineThrough else TextDecoration.None
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        leadingContent = {
                            Checkbox(checked = checked.value, onCheckedChange = {
                                checked.value = it
                            })
                        },
                        trailingContent = {
                            IconButton(onClick = {
                                tasks.removeAt(index)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Task",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    )
                    Divider()
                }
//        list of tasks
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {
        Page()
    }
}