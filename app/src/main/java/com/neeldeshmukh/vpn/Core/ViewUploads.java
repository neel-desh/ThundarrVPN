package com.neeldeshmukh.vpn.Core;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.neeldeshmukh.vpn.R;
import com.neeldeshmukh.vpn.Model.CloudStorageModel;

import java.util.ArrayList;
import java.util.List;

public class ViewUploads extends AppCompatActivity {

    //the listview
    ListView listView;
    ProgressDialog pd;
    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    FirebaseUser user;
    //list to store uploads data
    List<CloudStorageModel> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);
       final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pd = new ProgressDialog(ViewUploads.this);
                pd.setTitle("Cloud Storage Options");
                pd.setMessage("Choose any one option to download or delete the file from the cloud storage");
                pd.setIcon(R.drawable.ic_app_google);

                pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Delete File", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //Toast.makeText(ViewUploads.this, "Delete file code goes here", Toast.LENGTH_SHORT).show();
                        //after file delete u also have to delete that from firebase storage
                        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                        CloudStorageModel upload = uploadList.get(i);
                        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(upload.getFile_url());
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                Query applesQuery = ref.child("Users").child(user.getUid()).child("links")
                                                        .orderByChild("file_url").equalTo(upload.getFile_url());

                                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                            appleSnapshot.getRef().removeValue();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e("DB REMOVED", "onCancelled", databaseError.toException());
                                    }
                                });
                                Log.e("File","#deleted");
                                finish();
                                startActivity(getIntent());
                            }
                        });

                    }
                });
                pd.setButton(DialogInterface.BUTTON_POSITIVE, "Download File", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //Toast.makeText(ViewUploads.this, "Downloading file code goes here", Toast.LENGTH_SHORT).show();
                        CloudStorageModel upload = uploadList.get(i);
                        Toast.makeText(getApplicationContext(),"BRUH: "+i,Toast.LENGTH_LONG).show();
                        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(upload.getFile_url()));
                        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, upload.getFile_name());
                        r.allowScanningByMediaScanner();
                        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(r);

                    }
                });
                pd.setButton(DialogInterface.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //Toast.makeText(ViewUploads.this, "Just dismiss", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                });
                pd.show();
                //getting the upload
//                CloudStorageModel upload = uploadList.get(i);
//                Toast.makeText(getApplicationContext(),"BRUH: "+i,Toast.LENGTH_LONG).show();
//                DownloadManager.Request r = new DownloadManager.Request(Uri.parse(upload.getFile_url()));
//                r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, upload.getFile_name());
//                r.allowScanningByMediaScanner();
//                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//                dm.enqueue(r);
            }
        });


        //getting the database reference
        //user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("links");

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    CloudStorageModel upload = postSnapshot.getValue(CloudStorageModel.class);
                    uploadList.add(upload);
                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getFile_name();
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
               if(adapter.isEmpty()){
                   String[]  emptty = new String[1];
                   emptty[0] = "There is no file uploaded";
                   listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,emptty));
               }
else {                listView.setAdapter(adapter);}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}