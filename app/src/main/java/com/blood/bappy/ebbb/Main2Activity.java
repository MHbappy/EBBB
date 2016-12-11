package com.blood.bappy.ebbb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Button donarRegistration;
    private Button needBlood;
    private Button donarList;
    private Button searchByLocation;
    private Button account;
    private Button aboutUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        donarRegistration =  (Button)findViewById(R.id.donar_Registration_btn);
        needBlood = (Button)findViewById(R.id.need_blood);
        donarList = (Button)findViewById(R.id.list_of_donar);
        searchByLocation =(Button) findViewById(R.id.search_by_location_btn);
        account = (Button)findViewById(R.id.account_btn);
        aboutUs = (Button)findViewById(R.id.about_us);


        donarRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, DonarRegistration.class));
            }
        });
        needBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, NeedBlood.class));
            }
        });
        donarList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ListOFdonar.class));
            }
        });
        searchByLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, SearchByLocation.class));
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, AboutUs.class));
            }
        });

    }
}
