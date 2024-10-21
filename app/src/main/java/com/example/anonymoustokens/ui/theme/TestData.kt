package com.example.anonymoustokens.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.anonymoustokens.SlipDate
import java.time.LocalDate
import java.util.Date

class TestData {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun createDataSet(): ArrayList<SlipDate> {
            val list = ArrayList<SlipDate>()

            list.add(
                SlipDate(
                    LocalDate.parse("2024-01-17"),
                    false
                )
            )

            list.add(
                SlipDate(
                    LocalDate.parse("2024-09-17"),
                    false
                )
            )

            list.add(
                SlipDate(
                    LocalDate.parse("2024-10-19"),
                    false
                )
            )

            list.add(
                SlipDate(
                    LocalDate.parse("2024-08-15"),
                    true
                )
            )

            list.add(
                SlipDate(
                    LocalDate.parse("2024-10-02"),
                    true
                )
            )

            return list
        }

    }

}