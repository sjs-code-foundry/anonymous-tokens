package com.example.anonymoustokens

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymoustokens.ui.theme.AnonymousTokensTheme
import kotlinx.android.synthetic.main.activity_main.btnAddSlipDate
import kotlinx.android.synthetic.main.activity_main.btnDeleteSelectedSlipDates
import kotlinx.android.synthetic.main.activity_main.etSlipDateEntry
import kotlinx.android.synthetic.main.activity_main.rvSlipDateList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {

    private lateinit var slipDateAdapter: SlipDateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnonymousTokensTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        date = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        slipDateAdapter = SlipDateAdapter(mutableListOf())

        rvSlipDateList.adapter = slipDateAdapter
        rvSlipDateList.layoutManager = LinearLayoutManager(this)

        btnAddSlipDate.setOnClickListener {
            val slipDateEntry = etSlipDateEntry.text.toString()
            if(slipDateEntry.isNotEmpty()) {
                val slipDate = SlipDate(slipDateEntry)
                slipDateAdapter.addSlipDate(slipDate)
                etSlipDateEntry.text.clear()
            }
        }

        btnDeleteSelectedSlipDates.setOnClickListener {
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