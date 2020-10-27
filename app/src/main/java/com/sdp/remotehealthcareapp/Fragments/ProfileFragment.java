package com.sdp.remotehealthcareapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sdp.remotehealthcareapp.Activities.MainScreen;
import com.sdp.remotehealthcareapp.R;


public class ProfileFragment extends Fragment {
    TextView name,address,phone,email;
    ImageView edit;
    View v;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_profile, container, false);
        init();
        loadata();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                        new EditProfileFragment()).addToBackStack("Edit Profile Fragment").commit();
            }
        });
        ((MainScreen)getActivity()).getSupportActionBar().setTitle("Profile");
        return v;
    }
    public void loadata(){
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("Intern")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Profile").document("Profile Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();

                            assert document != null;
                            name.setText(document.getString("name"));
                            address.setText(document.getString("address"));
                            phone.setText(document.getString("phone"));
                            email.setText(document.getString("email"));

                            Toast.makeText((Context)getContext(), "Sucessfully added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void init()
    {
        name= v.findViewById(R.id.edit_name);
        address= v.findViewById(R.id.edit_address);
        phone=v.findViewById(R.id.edit_phone);
        email= v.findViewById(R.id.edit_email);
        edit=v.findViewById(R.id.image_edit);

    }

}