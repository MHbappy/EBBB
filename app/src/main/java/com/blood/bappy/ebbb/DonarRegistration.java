package com.blood.bappy.ebbb;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DonarRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{



    private EditText donarName;
    private EditText bloodGroup;
    private EditText donarAddress;
    private Spinner sex;
    private EditText donarOccupation;
    private TextView dateOFBirthText;
    private Button dateOfBirthButton;
    private EditText donarEmail;
    private EditText donarPhoneNumber;
    private EditText donarNationalIdCard;
    private EditText donarAge;

    private Button donarRegistrationButton;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    private String sexInformation;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_registration);

        donarName =(EditText) findViewById(R.id.name);
        bloodGroup = (EditText)findViewById(R.id.bloodGroup);
        donarAddress = (EditText) findViewById(R.id.address);
        sex = (Spinner) findViewById(R.id.sex);
        donarOccupation = (EditText)findViewById(R.id.occupation);

        dateOFBirthText = (TextView) findViewById(R.id.dateOfBirthText);
        dateOfBirthButton = (Button) findViewById(R.id.dateOfBirthButton);

        donarEmail = (EditText) findViewById(R.id.emailOfDonar);
        donarPhoneNumber = (EditText) findViewById(R.id.phoneNumberOfDonar);
        donarNationalIdCard = (EditText) findViewById(R.id.nationalIdNo);
        donarAge = (EditText) findViewById(R.id.age);

        donarRegistrationButton = (Button)findViewById(R.id.donarRegistrationBtn);


        sex.setOnItemSelectedListener(this);
        mDatabase = FirebaseDatabase.getInstance().getReference("DonarRegistration");
        progressDialog = new ProgressDialog(this);





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


        donarRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegistration();
            }
        });


    }

    private void startRegistration() {
        progressDialog.setMessage("Posting to Blog");
        progressDialog.show();

        final String donar_name = donarName.getText().toString().trim();
        final String blood_group = bloodGroup.getText().toString().trim();
        final String donar_address = donarAddress.getText().toString().trim();
        String sexs = sexInformation;
        final String donar_Occupation = donarOccupation.getText().toString().trim();
        final String date_of_birth = birthdate();
        final String email = donarEmail.getText().toString().trim();
        final String phone = donarPhoneNumber.getText().toString().trim();
        final String nationalIdNo = donarNationalIdCard.getText().toString().trim();
        final String age = donarAge.getText().toString().trim();



        Log.v("---------------------",donar_address+"-------------------------------"+blood_group);

        if (!TextUtils.isEmpty(donar_name) && !TextUtils.isEmpty(donar_address)){
            Log.v("++++++++++++++++","-------------------------------");

            DatabaseReference newPost = mDatabase.push();

            newPost.child("name").setValue(donar_name);
            newPost.child("bloodGroup").setValue(blood_group);
            newPost.child("address").setValue(donar_address);
            newPost.child("sex").setValue(sexs);
            newPost.child("occupation").setValue(donar_Occupation);
            newPost.child("birthDate").setValue(date_of_birth);
            newPost.child("email").setValue(email);
            newPost.child("phone").setValue(phone);
            newPost.child("nationalIdNo").setValue(nationalIdNo);
            newPost.child("age").setValue(age);



            progressDialog.dismiss();
            startActivity(new Intent(DonarRegistration.this,Main2Activity.class));

        }

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        sexInformation = item;
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

    //////////////////////////////////////////////////////////////////start for date only
    /////////only for date


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
        Toast.makeText(this, new StringBuilder().append("Birth Date ").append(dateOFBirthText.getText()),  Toast.LENGTH_SHORT).show();


    }

    private String birthdate() {
        return dateOFBirthText.getText().toString();
    }

    //////////////////////////////////////////////////////////////////end for date only
}
