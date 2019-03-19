package com.blazingapps.asus.sanskriti19;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.InetAddress;

public class RadioActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseUser auth;
    TextView songname,moviename, dedicared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        db = FirebaseFirestore.getInstance();
        songname = findViewById(R.id.song_name);
        moviename = findViewById(R.id.movie);
        dedicared = findViewById(R.id.dedicated);
        auth = FirebaseAuth.getInstance().getCurrentUser();
        final String name = auth.getDisplayName();

        final Button b = findViewById(R.id.buttonrad);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String song_name = ""+songname.getText().toString();
                if (song_name.length() <=1 ){
                    Toast.makeText(getApplicationContext(),"Song name invalid length",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isInternetAvailable()){
                    Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
                }
                b.setEnabled(false);
                db.collection("songs").add(new SongObject(
                        song_name,
                        ""+moviename.getText().toString(),
                        ""+ dedicared.getText().toString(),
                        ""+name
                )).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        b.setEnabled(true);
                        songname.setText("");
                        moviename.setText("");
                        dedicared.setText("");
                        Toast.makeText(getApplicationContext(),"Send",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        b.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });

               }
        });
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public void back(View view) {
        finish();
    }
}
