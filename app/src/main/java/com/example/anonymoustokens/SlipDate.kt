package com.example.anonymoustokens

import java.time.LocalDate
import java.util.Date

data class SlipDate(
    val date: LocalDate,
    var isChecked: Boolean = false
)