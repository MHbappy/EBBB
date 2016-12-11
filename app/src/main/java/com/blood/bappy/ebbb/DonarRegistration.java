package com.blood.bappy.ebbb;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DonarRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private EditText editText;
    private Spinner sex;
    private EditText dateOfBirthDay;

//////////////////////////////////////////////////////////////////start for date only
    /////////only for date
    private TextView dateOFBirthText;
    private Button dateOfBirthButton;
    private int pYear;
    private int pMonth;
    private int pDay;


    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
    static final int DATE_DIALOG_ID = 0;

    /** Callback received when the user "picks" a date in the dialog */
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();
                    displayToast();
                }
            };

    private void updateDisplay() {
        dateOFBirthText.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(pMonth + 1).append("/")
                        .append(pDay).append("/")
                        .append(pYear).append(" "));
    }

    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(dateOFBirthText.getText()),  Toast.LENGTH_SHORT).show();

    }

    //////////////////////////////////////////////////////////////////end for date only

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_registration);


        sex = (Spinner) findViewById(R.id.sex);
        sex.setOnItemSelectedListener(this);



        dateOFBirthText = (TextView) findViewById(R.id.dateOfBirthText);
        dateOfBirthButton = (Button) findViewById(R.id.dateOfBirthButton);


        //////////////dateOfBirth

        dateOfBirthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        ///////////////spiner start

        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(dataAdapter);
        ////////////////spiner end

//        editText = (EditText) findViewById(R.id.name);
//        Drawable drawable = editText.getBackground(); // get current EditText drawable
//        drawable.setColorFilter(Color.parseColor("#FF8A0707"), PorterDuff.Mode.SRC_ATOP); // change the drawable color
//
//        if(Build.VERSION.SDK_INT > 16) {
//            editText.setBackground(drawable); // set the new drawable to EditText
//        }else{
//            editText.setBackgroundDrawable(drawable); // use setBackgroundDrawable because setBackground required API 16
//        }



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }
}
