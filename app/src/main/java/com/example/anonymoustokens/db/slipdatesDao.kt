package com.example.anonymoustokens.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.anonymoustokens.SlipDate

@Dao
interface SlipdatesDao {

    @Query("SELECT * FROM SlipDate")
    fun getAllSlipdates(): LiveData<List<SlipDate>>

    @Insert
    fun addSlipdates(slipdates: SlipDate)

    @Query("DELETE FROM SlipDate WHERE id = :id")
    fun deleteSlipdates(id: Int)

}