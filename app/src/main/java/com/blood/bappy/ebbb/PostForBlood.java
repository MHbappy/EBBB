package com.blood.bappy.ebbb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostForBlood extends AppCompatActivity {


    private ImageButton mSelectImage;
    private EditText bloodGroup;
    private EditText name;
    private EditText phone;
    private EditText location;
    private Button postButton;

    private Uri mImageUri = null;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    private static final int GALLARY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_for_blood);

        mSelectImage = (ImageButton) findViewById(R.id.bloodPostimageButton);
        bloodGroup = (EditText) findViewById(R.id.bloodPostGroup);
        name = (EditText) findViewById(R.id.bloodPostName);
        phone = (EditText) findViewById(R.id.bloodPostPhone);
        location = (EditText) findViewById(R.id.bloodPostLocation);
        postButton = (Button) findViewById(R.id.emergency_Blood_Need);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference("NeedBlood");
        progressDialog = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLARY_REQUEST);

            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }

    private void startPosting() {
//        progressDialog.setMessage("Posting to Blog");
//        progressDialog.show();

        final String bloodGroupE = bloodGroup.getText().toString().trim();
        final String nameE = name.getText().toString().trim();
        final String phoneE = phone.getText().toString().trim();
        final String locationE = location.getText().toString().trim();

        Log.v("99999999999999999999",""+TextUtils.isEmpty(bloodGroupE)+"   "+TextUtils.isEmpty(phoneE)+"   "+mImageUri+"   "+TextUtils.isEmpty(locationE));


        if (!TextUtils.isEmpty(bloodGroupE) && !TextUtils.isEmpty(phoneE) && mImageUri!=null && !TextUtils.isEmpty(nameE)){

            StorageReference filePath =  mStorage.child("NeedBlood").child(mImageUri.getLastPathSegment());


            filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri =taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();

                    newPost.child("bloodGroup").setValue(bloodGroupE);
                    newPost.child("name").setValue(nameE);
                    newPost.child("phone").setValue(phoneE);
                    newPost.child("location").setValue(locationE);
                    newPost.child("image").setValue(downloadUri.toString());

                    progressDialog.dismiss();

                    startActivity(new Intent(PostForBlood.this,Main2Activity.class));
                }
            });

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLARY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mSelectImage.setImageURI(mImageUri);

        }
    }


}
