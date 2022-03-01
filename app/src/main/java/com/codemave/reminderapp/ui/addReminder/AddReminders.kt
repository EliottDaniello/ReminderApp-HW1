package com.codemave.reminderapp.ui.addReminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun AddReminder(
    onBackPress: () -> Unit,
    userID: Int
    ) {
    val name = rememberSaveable { mutableStateOf("") }
    val date = rememberSaveable { mutableStateOf("") }
    val hour = rememberSaveable { mutableStateOf("") }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = "Create a new reminder")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = {name.value = it},
                    label = { Text(text = "Reminder name")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    OutlinedTextField(
                        value = date.value,
                        onValueChange = {date.value = it},
                        label = { Text(text = "Date")}
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        value = hour.value,
                        onValueChange = {hour.value = it},
                        label = { Text(text = "Hour")}
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().size(55.dp)
                ) {
                    Text("Add reminder")
                }
            }
        }
    }
}