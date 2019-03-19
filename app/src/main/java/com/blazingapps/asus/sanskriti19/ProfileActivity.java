package com.blazingapps.asus.sanskriti19;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ProfileActivity extends AppCompatActivity {
    private static final String MYPREF = "mypref";
    private static final String COLLAGE = "collage";
    private static final String SEMESTER = "semester";
    private static final String PHONE = "phone";
    private static final String PROFILECOMPLETE = "profilecomplete";
    ImageView profilepic;
    TextView username;
    TextView collage;
    TextView phone;
    TextView semester;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = FirebaseAuth.getInstance().getCurrentUser();
        profilepic = findViewById(R.id.profileImageView);
        collage = findViewById(R.id.collage_name);
        semester = findViewById(R.id.semester);
        username = findViewById(R.id.user_name);
        phone = findViewById(R.id.phone_no);
        loadprofile();

        SharedPreferences sharedPreferences = getSharedPreferences(MYPREF,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(PROFILECOMPLETE,false)){
            showCompleteProfile();
        }

    }

    public void loadprofile(){
        String photoUrl = user.getPhotoUrl().toString();
        username.setText(user.getDisplayName());
        SharedPreferences sharedPreferences =getSharedPreferences(MYPREF,MODE_PRIVATE);
        collage.setText(sharedPreferences.getString(COLLAGE,"MACE"));
        semester.setText(sharedPreferences.getString(SEMESTER,"Semester"));
        phone.setText(sharedPreferences.getString(PHONE,"000-000-000"));

        for (UserInfo profile : user.getProviderData()) {
            System.out.println(profile.getProviderId());
            // check if the provider id matches "facebook.com"
            if (profile.getProviderId().equals("facebook.com")) {
                String facebookUserId = profile.getUid();
                photoUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?height=256";
            }
            else if (profile.getProviderId().equals("google.com")) {
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                if (acct != null) {
                    String originalPieceOfUrl = "s96-c/photo.jpg";
                    String newPieceOfUrlToAdd = "s256-c/photo.jpg";
                    photoUrl = photoUrl.replace(originalPieceOfUrl, newPieceOfUrlToAdd);
                }
            }
        }
        final Transformation transformation = new CircleTransform();
        Picasso.get().load(photoUrl).placeholder(R.drawable.logo).fit().centerCrop().transform(transformation).into(profilepic);

    }

    public void updateProfile(final String phone, final String collage, final String semester) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getUid())
                .update(
                        "collagename", collage,
                        "semester", semester,
                        "phoneno",phone).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                SharedPreferences sharedPreferences =getSharedPreferences(MYPREF,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(PROFILECOMPLETE,true);
                editor.putString(PHONE,phone);
                editor.putString(COLLAGE,collage);
                editor.putString(SEMESTER,semester);
                editor.commit();
                Log.d("TAGZ","successed");
            }
        });
    }

    public void profileExit(View view) {

        finish();
    }

    public void showCompleteProfile(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details");
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_complete_profile, null);
        final EditText input_collage = alertLayout.findViewById(R.id.et_collage);
        final EditText input_phone = alertLayout.findViewById(R.id.et_phone);
        final Spinner input_semester = alertLayout.findViewById(R.id.sp_semester);
        String[] s = {"S2","S4","S6","S8" };
        final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, s);
        input_semester.setAdapter(adp);
        builder.setCancelable(false);
        builder.setView(alertLayout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phone_no = input_phone.getText().toString();
                String collage = input_collage.getText().toString();
                String semester = input_semester.getSelectedItem().toString();
                updateProfile(phone_no,collage,semester);
                startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
                finish();
            }
        });
        builder.show();
    }
    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
