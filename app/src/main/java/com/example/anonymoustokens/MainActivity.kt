package com.example.anonymoustokens

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymoustokens.databinding.ActivityMainBinding
import com.example.anonymoustokens.ui.theme.AnonymousTokensTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var slipDateAdapter: SlipDateAdapter
    private lateinit var binding: ActivityMainBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        slipDateAdapter = SlipDateAdapter(mutableListOf())

        binding.rvSlipDateList.adapter = slipDateAdapter
        binding.rvSlipDateList.layoutManager = LinearLayoutManager(this)

//        binding.btnAddSlipDate.setOnClickListener {
//            val slipDateEntry = binding.etSlipDateEntry.text.toString()
//            if(slipDateEntry.isNotEmpty()) {
//                val slipDate = SlipDate(slipDateEntry)
//                slipDateAdapter.addSlipDate(slipDate)
//                binding.etSlipDateEntry.text.clear()
//            }
//        }

        binding.btnAddSlipDate.setOnClickListener {
            showDatePicker()
        }

        binding.btnDeleteSelectedSlipDates.setOnClickListener {
            slipDateAdapter.deleteSelectedSlipDates()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                val selectedSlipDate = SlipDate("$formattedDate")
                binding.tvSlipDateEntry.text = selectedSlipDate.date
                slipDateAdapter.addSlipDate(selectedSlipDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormatDate(date: String): LocalDate? {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val formattedDate = LocalDate.parse(date, formatter)

    println(formattedDate)
    return formattedDate
}