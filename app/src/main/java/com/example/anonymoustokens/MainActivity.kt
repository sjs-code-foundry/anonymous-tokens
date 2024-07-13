package com.example.anonymoustokens

import android.os.Build
import android.os.Bundle
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {

    private lateinit var slipDateAdapter: SlipDateAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        slipDateAdapter = SlipDateAdapter(mutableListOf())

        binding.rvSlipDateList.adapter = slipDateAdapter
        binding.rvSlipDateList.layoutManager = LinearLayoutManager(this)

        binding.btnAddSlipDate.setOnClickListener {
            val slipDateEntry = binding.etSlipDateEntry.text.toString()
            if(slipDateEntry.isNotEmpty()) {
                val slipDate = SlipDate(slipDateEntry)
                slipDateAdapter.addSlipDate(slipDate)
                binding.etSlipDateEntry.text.clear()
            }
        }

        binding.btnDeleteSelectedSlipDates.setOnClickListener {
            slipDateAdapter.deleteSelectedSlipDates()
        }

    }
}

@Composable
fun Greeting(date: String, modifier: Modifier = Modifier) {
    Text(
        text = "You have been clean for: $date!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnonymousTokensTheme {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Greeting(FormatDate("2024-06-01").toString())
        }
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