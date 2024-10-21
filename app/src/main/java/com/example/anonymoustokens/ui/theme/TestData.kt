package com.example.anonymoustokens.ui.theme

import com.example.anonymoustokens.SlipDate

class TestData {

    companion object {

        fun createDataSet(): ArrayList<SlipDate> {
            val list = ArrayList<SlipDate>()

            list.add(
                SlipDate(
                    "19/10/2024",
                    false
                )
            )

            list.add(
                SlipDate(
                    "2/10/2024",
                    true
                )
            )

            list.add(
                SlipDate(
                    "17/09/2024",
                    false
                )
            )

            list.add(
                SlipDate(
                    "15/08/2024",
                    true
                )
            )

            list.add(
                SlipDate(
                    "17/01/2024",
                    false
                )
            )

            return list
        }

    }

}