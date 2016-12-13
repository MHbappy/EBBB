package com.blood.bappy.ebbb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListOFdonar extends AppCompatActivity {

    private RecyclerView bloodDonarBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ofdonar);

        bloodDonarBlogList = (RecyclerView) findViewById(R.id.blog_list);
        bloodDonarBlogList.setHasFixedSize(true);
        bloodDonarBlogList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("DonarRegistration");
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DonarList, BlogVIewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DonarList, BlogVIewHolder>(
                DonarList.class,
                R.layout.donar_list_blog_row,
                BlogVIewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogVIewHolder viewHolder, DonarList model, int position) {

                viewHolder.setBlood(model.getBloodGroup());
                viewHolder.setName(model.getName());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setAge(model.getAge());
                viewHolder.setSex(model.getSex());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setEmaill(model.getEmail());
                viewHolder.setNationalId(model.getNationalIdNo());
                viewHolder.setOccupation(model.getOccupation());


            }
        };
        bloodDonarBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class  BlogVIewHolder extends RecyclerView.ViewHolder{

        View mView;

        public BlogVIewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setBlood(String bloodGroup){
            TextView blood_group = (TextView) mView.findViewById(R.id.bloodGroupCard);
            blood_group.setText(bloodGroup);
        }

        public void setName(String name){
            TextView blood_name = (TextView) mView.findViewById(R.id.bloodNameCard);
            blood_name.setText(name);

        }

        public void setAddress(String occupation){
            TextView blood_address = (TextView) mView.findViewById(R.id.bloodAddressCard);
            blood_address.setText(occupation);
        }

        public void setSex(String sex){
            TextView blood_sex = (TextView) mView.findViewById(R.id.bloodSexCard);
            blood_sex.setText(sex);
        }

        public void setAge(String age){
            TextView blood_age = (TextView) mView.findViewById(R.id.bloodAgeCard);
            blood_age.setText(age);
        }

        public void setPhone(String phone){
            TextView blood_phone = (TextView) mView.findViewById(R.id.bloodPhoneCard);
            blood_phone.setText(phone);

        }
        public void setEmaill(String email){
            TextView blood_email = (TextView) mView.findViewById(R.id.bloodEmailCard);
            blood_email.setText(email);
        }
        public void setNationalId(String nationalId){
            TextView blood_national_id = (TextView) mView.findViewById(R.id.bloodnationalCard);
            blood_national_id.setText(nationalId);
        }
        public void setOccupation(String occupation){
            TextView blood_occupation = (TextView) mView.findViewById(R.id.bloodnationalCard);
            blood_occupation.setText(occupation);
        }




    }

}
