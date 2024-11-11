package com.example.anonymoustokens.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.anonymoustokens.Converters
import com.example.anonymoustokens.SlipDate

@Database(entities = [SlipDate::class], version = 1)
@TypeConverters(Converters::class)
abstract class SlipdatesDatabase : RoomDatabase() {

    companion object {
        const val NAME = "Slipdates_DB"
    }

    abstract fun getSlipdatesDao(): SlipdatesDao

}