package com.example.anonymoustokens

import java.time.LocalDate

data class SlipDate(
    val date: LocalDate,
    var isChecked: Boolean = false
)