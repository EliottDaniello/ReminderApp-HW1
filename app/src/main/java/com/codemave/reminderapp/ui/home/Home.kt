package com.codemave.reminderapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.codemave.reminderapp.data.entity.Reminder
import com.google.accompanist.insets.systemBarsPadding
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Home(
    navController: NavController,
    userID: Int
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 0.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "addReminder/"+userID.toString()) },
                contentColor = Color.DarkGray,
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
        ) {
            val appBarColor = MaterialTheme.colors.secondary.copy(alpha = 0.87f)

            HomeAppBar(
                backgroundColor = appBarColor,
                navController = navController,
                userID
            )

            val list = mutableListOf<Reminder>()
            for (x in 1..15) {
                list.add(
                    Reminder(
                        userID = userID,
                        reminderID = x.toLong(),
                        reminderTitle = "Reminder $x userID : $userID",
                        reminderDate = Date()
                    )
                )
            }
            ReminderList(list)
        }
    }
}

@Composable
private fun HomeAppBar(
    backgroundColor: Color,
    navController: NavController,
    userID: Int
) {
    TopAppBar(
        title = {
            Text(
                text = "All reminders",
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton( onClick = {navController.navigate(route = "login")} ) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "")

            }
            IconButton(onClick = { navController.navigate("profil/"+userID.toString()) }) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
            }
        }
    )
}

@Composable
private fun ReminderList(
    list: List<Reminder>
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(list) { item ->
            ReminderListItem(
                reminder = item,
                onClick = {},
                modifier = Modifier.fillParentMaxWidth(),
            )
        }
    }
}

@Composable
private fun ReminderListItem(
    reminder: Reminder,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(modifier = modifier.clickable { onClick() }) {
        val (divider, reminderTitle, icon, date) = createRefs()
        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        // title
        Text(
            text = reminder.reminderTitle,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(reminderTitle) {
                linkTo(
                    start = parent.start,
                    end = icon.start,
                    startMargin = 24.dp,
                    endMargin = 16.dp,
                    bias = 0f // float this towards the start. this was is the fix we needed
                )
                top.linkTo(parent.top, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )

        // date
        Text(
            text = when {
                reminder.reminderDate != null -> { reminder.reminderDate.formatToString() }
                else -> Date().formatToString()
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(date) {
                linkTo(
                    start = parent.start,
                    end = icon.start,
                    startMargin = 24.dp,
                    endMargin = 16.dp,
                    bias = 0f // float this towards the start. this was is the fix we needed
                )
                top.linkTo(reminderTitle.bottom, margin = 6.dp)
                bottom.linkTo(parent.bottom, 10.dp)
                width = Dimension.preferredWrapContent
            }
        )

        // icon
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = ""
            )
        }
    }
}

private fun Date.formatToString(): String {
    return SimpleDateFormat("dd MMMM yyyy 'at' HH:mm", Locale.getDefault()).format(this)
}
