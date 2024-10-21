package com.example.anonymoustokens

import android.app.DatePickerDialog
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymoustokens.databinding.ActivityMainBinding
import com.example.anonymoustokens.ui.theme.TestData
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var slipDateAdapter: SlipDateAdapter
    private lateinit var binding: ActivityMainBinding
//    private val calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        initRecyclerView()
        addTestData()

        binding.btnAddSlipDate.setOnClickListener {
//            showDatePicker()
            Log.i("Latest Date", getLatestDate().toString())
        }
    }

    private fun addTestData() {
        val data = TestData.createDataSet()
        slipDateAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        binding.rvSlipDateList.apply {
            layoutManager = LinearLayoutManager (this@MainActivity)
            slipDateAdapter = SlipDateAdapter()
            adapter = slipDateAdapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getLatestDate(): LocalDate? {
        val slipDateList = slipDateAdapter.getSlipDates()
        val dateListLength = slipDateAdapter.itemCount

        if(dateListLength > 0) {
            val sortedList = slipDateList.sortedWith(compareByDescending { it.date })

            Log.i("slipDateList", sortedList.toString())

            return sortedList[0].date
        } else {
            return null
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//        enableEdgeToEdge()
//
//        slipDateAdapter = SlipDateAdapter(mutableListOf())
//
//        binding.rvSlipDateList.adapter = slipDateAdapter
//        binding.rvSlipDateList.layoutManager = LinearLayoutManager(this)
//
//        binding.btnAddSlipDate.setOnClickListener {
//            showDatePicker()
//        }
//
//        binding.btnDeleteSelectedSlipDates.setOnClickListener {
//            slipDateAdapter.deleteSelectedSlipDates()
//            updateCleanTime()
//        }
//    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun showDatePicker() {
//        val datePickerDialog = DatePickerDialog(
//            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
//                val selectedDate = Calendar.getInstance()
//                selectedDate.set(year, monthOfYear, dayOfMonth)
//                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                val formattedDate = dateFormat.format(selectedDate.time)
//                val selectedSlipDate = SlipDate("$formattedDate")
//                slipDateAdapter.addSlipDate(selectedSlipDate)
//                updateCleanTime()
//            },
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//        datePickerDialog.show()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun updateCleanTime() {
//        val latestDate = getLatestDate()
//
//        binding.tvSlipDateEntry.text = latestDate
//
//        if(latestDate != null) {
//            val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//
//            val from = LocalDate.parse(latestDate, dateFormat)
//            val to = LocalDate.now()
//
//            val period = Period.between(from, to)
//            val years = period.years
//            val months = period.months
//            val days = period.days
//
//            if (years < 0 || months < 0 || days < 0) {
//                binding.tvTimeSinceLastSlip.text = "One date is in the future, please delete."
//                tokenColour(0, 0, 0)
//            } else if (years == 0 && months == 0 ) {
//                binding.tvTimeSinceLastSlip.text = "Clean for $days days."
//                tokenColour(years, months, days)
//            } else if (years == 0) {
//                binding.tvTimeSinceLastSlip.text = "Clean for $months months and $days days."
//                tokenColour(years, months, days)
//            } else {
//                binding.tvTimeSinceLastSlip.text = "Clean for $years years, $months months and $days days."
//                tokenColour(years, months, days)
//            }
//        } else {
//            binding.tvTimeSinceLastSlip.text = "There are no dates."
//            tokenColour(0, 0, 0)
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun getLatestDate(): String? {
//        var slipDateList = slipDateAdapter.getSlipDates()
//        val dateListLength = slipDateAdapter.itemCount
//
//        if(dateListLength > 0) {
//            slipDateList.sortByDescending { it.date }
//
//            return slipDateList[0].date
//        } else {
//            return null
//        }
//    }
//
//    private fun tokenColour(years: Int, months: Int, days: Int) {
//        val token = binding.ivAnonymousToken
//
//        if (months < 1 && years < 1) {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_dayOne), PorterDuff.Mode.MULTIPLY )
//        } else if (months == 1 &&  years == 0) {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_1Month), PorterDuff.Mode.MULTIPLY )
//        } else if (months == 2 &&  years == 0)  {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_2Months), PorterDuff.Mode.MULTIPLY )
//        } else if (months in 3..5 && years == 0) {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_3Months), PorterDuff.Mode.MULTIPLY )
//        } else if (months in 6..8 && years == 0) {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_6Months), PorterDuff.Mode.MULTIPLY )
//        } else if (months in 9..11 && years == 0) {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_9Months), PorterDuff.Mode.MULTIPLY )
//        } else {
//            token.setColorFilter(ContextCompat.getColor(this, R.color.token_1YearPlus), PorterDuff.Mode.MULTIPLY )
//        }
//    }
}
