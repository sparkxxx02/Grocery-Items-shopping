package com.sdp.remotehealthcareapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.sdp.remotehealthcareapp.R;

import java.util.HashMap;

public class EditProfileFragment extends Fragment {
    View v;
    TextView name,email,phone,address,age;
    Button save;
    public EditProfileFragment() {
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
        v=inflater.inflate(R.layout.profile_setup, container, false);
        init();
        loadata();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateProfile();
            }
        });
        return v;
    }

    public void updateProfile() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("email", email.getText().toString());
        map.put("phone", phone.getText().toString());
        map.put("address", address.getText().toString());
        map.put("age", age.getText().toString());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Intern")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Profile").document("Profile Data")
                .set(map, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getContext(), "Added Sucess", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(), "Error in adding", Toast.LENGTH_SHORT).show();
                    }
                });
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
    public void init(){
        name= v.findViewById(R.id.editName1);
        address=v.findViewById(R.id.editAddress);
        age= v.findViewById(R.id.editAge);
        phone=v.findViewById(R.id.editPhone);
        email=v.findViewById(R.id.editemail);
        save= v.findViewById(R.id.buttonSave);
    }

}