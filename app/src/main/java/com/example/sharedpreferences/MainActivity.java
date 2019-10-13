package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserName, getEditTextPassword;
    Button buttonDate,buttonLogIn;
    CheckBox checkBoxSave;
    SharedPreferences sharedpreferences;
    String savedUsername,savedPassword,savedDate;
    TextView textViewDate;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "usernameKey";
    public static final String Password = "passwordKey";
    public static final String Date = "dateKey";

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserName = findViewById(R.id.editText);
        getEditTextPassword = findViewById(R.id.editText2);

        buttonLogIn = findViewById(R.id.button2);

        buttonDate = findViewById(R.id.button);

        checkBoxSave = findViewById(R.id.checkBox);

        textViewDate = findViewById(R.id.textView);

        //
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                textViewDate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        //

        sharedpreferences = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSave.isChecked()) {
                    SharedPreferences.Editor editor;
                    editor = sharedpreferences.edit();
                    editor.putString(Username, editTextUserName.getText().toString());
                    editor.putString(Password, getEditTextPassword.getText().toString());
                    editor.putString(Date, textViewDate.getText().toString());
                    editor.apply();
                }
            }
        });

        savedUsername = sharedpreferences.getString(Username,"Username");
        savedPassword = sharedpreferences.getString(Password,"Password");
        savedDate = sharedpreferences.getString(Date,"Date");

        editTextUserName.setText(savedUsername);
        getEditTextPassword.setText(savedPassword);
        textViewDate.setText(savedDate);
    }
}
