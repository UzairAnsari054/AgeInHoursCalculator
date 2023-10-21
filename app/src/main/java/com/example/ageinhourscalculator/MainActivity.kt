package com.example.ageinhourscalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate: Button = findViewById(R.id.btnSelectDate)
        btnSelectDate.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
        val tvDateInHours: TextView = findViewById(R.id.tvDateInHours)

        val dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedDateObj = sdf.parse(selectedDate)
                val selectedDateInHours = selectedDateObj.time / 60000 / 60

                val currentDateObj = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInHours = currentDateObj.time / 60000 / 60

                val difference = currentDateInHours - selectedDateInHours

                tvDateInHours.text = difference.toString()
            },
            currentYear,
            currentMonth,
            currentDay
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 84600000
        dpd.show()
    }
}