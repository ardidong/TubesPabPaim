package com.apahayo.tubespabpaim;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apahayo.tubespabpaim.Model.Mood;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilUserFragment extends Fragment {

    private DatabaseReference dbRefrence;
    private int value = 0;
    private Mood currentMood;
    private TextView namaUser,emailUser;
    private ImageView emotImageView,gambarUser;
    GoogleSignInClient mGoogleSignClient;
    private Button btnLogout;
    FirebaseAuth mAuth;
    FirebaseUser user;



    public ProfilUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profil_user, container, false);
        namaUser = view.findViewById(R.id.namaUser);
        emailUser = view.findViewById(R.id.emailUser);
        gambarUser = view.findViewById(R.id.profile_image);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {


            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            namaUser.setText(personName);
            emailUser.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(gambarUser);




        }else if (acct ==null){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
            db.child("nama").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String nama = dataSnapshot.getValue(String.class);
                    namaUser.setText(nama);
                    emailUser.setText(email);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }




        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        btnLogout = view.findViewById(R.id.logoutBtn);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.logoutBtn:
                        signOut();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                }



            }
        });
        return view;
    }

    private void signOut(){
        mGoogleSignClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(),"Berhasil Keluar !!",Toast.LENGTH_SHORT).show();


                    }
                });
    }

}
