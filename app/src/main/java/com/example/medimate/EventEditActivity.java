package com.example.medimate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    private LocalDate selectedDate;
    private LocalTime selectedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtil.formattedDate(CalendarUtil.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtil.formattedTime(time));

        selectedDate = CalendarUtil.selectedDate != null ? CalendarUtil.selectedDate : LocalDate.now();
        selectedTime = LocalTime.now();
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void openDatePicker(View view) {
        LocalDate today = selectedDate != null ? selectedDate : LocalDate.now();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view1, year, month, dayOfMonth) -> {
                    selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
                    eventDateTV.setText("Date: " + CalendarUtil.formattedDate(selectedDate));
                },
                today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth()
        );
        datePickerDialog.show();
    }

    public void openTimePicker(View view) {
        LocalTime now = selectedTime != null ? selectedTime : LocalTime.now();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (timePicker, hourOfDay, minute) -> {
                    selectedTime = LocalTime.of(hourOfDay, minute);
                    eventTimeTV.setText("Time: " + CalendarUtil.formattedTime(selectedTime));
                },
                now.getHour(), now.getMinute(), true
        );
        timePickerDialog.show();
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();

        if (eventName.isEmpty()) {
            Toast.makeText(this, "Please enter an event name.", Toast.LENGTH_SHORT).show();
            return;
        }

        Event newEvent = new Event(eventName, CalendarUtil.selectedDate, time);
        Event.eventsList.add(newEvent);
        Toast.makeText(this, "Event saved: " + eventName, Toast.LENGTH_SHORT).show();
        finish();
    }
}