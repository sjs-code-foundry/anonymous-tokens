package com.example.anonymoustokens

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun toLocalDate(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

}