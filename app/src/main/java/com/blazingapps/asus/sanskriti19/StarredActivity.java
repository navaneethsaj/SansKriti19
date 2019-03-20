package com.blazingapps.asus.sanskriti19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class StarredActivity extends AppCompatActivity {
    private static final String TAG = "TAGZ";
    private RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EventsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred);
        recyclerView = findViewById(R.id.my_recycler_view);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        db.collection("staredevents").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                ArrayList<EventItem> eventItems = new ArrayList<>();

                for (QueryDocumentSnapshot doc : snapshots) {
                    if (doc.get("name") != null) {
                        String name = doc.getString("name");
                        String desc = doc.getString("desc");
                        String url = doc.getString("url");
                        String ticketurl = ""+doc.getString("ticket_url");
                        eventItems.add(new EventItem(name,name,desc,url,ticketurl));
                    }
                }
                mAdapter = new EventsAdapter(eventItems, getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
