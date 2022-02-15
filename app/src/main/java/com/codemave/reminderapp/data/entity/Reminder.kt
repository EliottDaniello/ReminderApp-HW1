package com.codemave.reminderapp.data.entity

import java.util.*

data class Reminder(
    val userID: Int,
    val reminderID: Long,
    val reminderTitle: String,
    val reminderDate: Date?,
)
