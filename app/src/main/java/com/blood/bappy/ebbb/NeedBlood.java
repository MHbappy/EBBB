package com.blood.bappy.ebbb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NeedBlood extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_blood);


        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("NeedBlood");


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<EmergencyBlood, BlogVIewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EmergencyBlood, BlogVIewHolder>(
                EmergencyBlood.class,
                R.layout.need_blood_blog_row,
                BlogVIewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogVIewHolder viewHolder, EmergencyBlood model, int position) {
                viewHolder.setBloodGroup(model.getBloodGroup());
                viewHolder.setBloodName(model.getName());
                viewHolder.setBloodLocation(model.getBloodGroup());
                viewHolder.setBloodPhone(model.getPhone());
                viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogVIewHolder extends RecyclerView.ViewHolder{

        View mView;

        public BlogVIewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setBloodGroup(String bloodGroupC){
            TextView bloodGroup = (TextView) mView.findViewById(R.id.needBlood_groupCard);
            bloodGroup.setText(bloodGroupC);
        }

        public void setBloodName(String bloodName){
            TextView blood_Name = (TextView) mView.findViewById(R.id.needBlood_NameCard);
            blood_Name.setText(bloodName);
        }

        public void setBloodLocation(String bloodPhone){
            TextView blood_location = (TextView) mView.findViewById(R.id.needBlood_donarLoacation);
            blood_location.setText(bloodPhone);
        }

        public void setBloodPhone(String bloodPhone){
            TextView blood_phone = (TextView) mView.findViewById(R.id.needblood_PhoneCard);
            blood_phone.setText(bloodPhone);
        }

        public void setImage(Context ctx, String image){
            ImageView imageView =(ImageView) mView.findViewById(R.id.needBlood_image_post_show);
            Picasso.with(ctx).load(image).into(imageView);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add){
            startActivity(new Intent(NeedBlood.this,PostForBlood.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
