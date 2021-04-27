package com.neeldeshmukh.vpn.Core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.neeldeshmukh.vpn.R;
import com.neeldeshmukh.vpn.Model.CloudStorageModel;
import com.neeldeshmukh.vpn.Model.User;

import java.util.List;


public class CloudStorage extends AppCompatActivity implements  View.OnClickListener{
    private static final int PICKFILE_UPLOAD_CODE = 911 ;
    TextView filenameuser;
    Button upload,download;
    ListView listview;
    List<CloudStorageModel> fileinserver;
    private StorageReference mStorageRef;
    public DatabaseReference firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    public User appuser;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_storage);
        upload = findViewById(R.id.uploadbtn);
        filenameuser = findViewById(R.id.file_name);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //Init Model based on user
        appuser = new User(firebaseUser.getUid(),firebaseUser.getDisplayName(),firebaseUser.getEmail());
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mStorageRef  = FirebaseStorage.getInstance().getReference();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                    chooseFile.setType("*/*");
                    chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                    startActivityForResult(chooseFile, PICKFILE_UPLOAD_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICKFILE_UPLOAD_CODE:
                try{
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                Uri uri = data.getData();
                String src = uri.getPath();
                String filename = getFileName(uri);
                if(uri!=null) {//progressbar here

                dialog = new ProgressDialog(CloudStorage.this);
                dialog.setMessage("Uploading File...");
                dialog.show();
                    storageReference.child(appuser.getUuid() + "/" + filename).putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                            storageReference.child(appuser.getUuid() + "/"+filename).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    CloudStorageModel csm = new CloudStorageModel(filenameuser.getText().toString(), task.getResult().toString());
                               firebaseDatabase.child("Users").child(appuser.getUuid()).child("links")
                                                            .child(firebaseDatabase.push().getKey()).setValue(csm);
                               dialog.dismiss();
                               filenameuser.setText("");
                            }
                            });
                        }
                    });
                }
                }catch (Exception ex){
                    Toast.makeText(this, ""+ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    public void onClick(View view) {

    }


}