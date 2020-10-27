package com.sdp.remotehealthcareapp.Fragments.Selected_Category.description;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.sdp.remotehealthcareapp.Activities.MainScreen;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.Dataclass;
import com.sdp.remotehealthcareapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class DescriptionFragment extends Fragment {
    String name;
    String price;
    String desc;
    String img;
    View v;
    Spinner spinner;
    String bt_name, bt_url, bt_price,bt_quantity;

    public DescriptionFragment(Dataclass dataclass) {

        name= dataclass.getName();
        price= dataclass.getPrice();
        desc= dataclass.getDesc();
        img= dataclass.getImg();
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
        v=inflater.inflate(R.layout.layout_description, container, false);
        getData();

        //spinner
        spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinneradapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.numbers, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);
        spinner.setPrompt("Quantity");



        (v.findViewById(R.id.imageCart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                Increment_button();
            }
        });
        ((MainScreen)getActivity()).getSupportActionBar().setTitle("Description");
        return v;
    }
    public void getData(){
        TextView N= v.findViewById(R.id.desc_name);
        TextView P=v.findViewById(R.id.desc_price);
        TextView D= v.findViewById(R.id.desc_desc);
        ImageView I=v.findViewById(R.id.desc_img);

        N.setText(name);
        P.setText(price);
        D.setText(desc);
        Picasso.get().load(img).into(I);
    }
    public void Increment_button(){
        HashMap<String, Object> map = new HashMap<>();
        String str;
        map.put("name", name);
        int num = Integer.parseInt(spinner.getSelectedItem().toString());
        map.put("quantity", String.valueOf(num+1));
        map.put("price", price);
        map.put("img", img);
        FirebaseFirestore.getInstance().collection("Intern")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Cart")
                .document(name)
                .set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}