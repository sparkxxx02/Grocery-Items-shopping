package com.sdp.remotehealthcareapp.Fragments;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;
import com.sdp.remotehealthcareapp.Activities.MainScreen;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.Dataclass;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.ViewHolder;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.description.DescriptionFragment;
import com.sdp.remotehealthcareapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class CartFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore firebaseFirestore;
    String category;
    private FirestoreRecyclerAdapter recyclerAdapter;
    FirestoreRecyclerOptions<Dataclass> options;
    int total=0;
    TextView text_total;
    View v;

    public CartFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_selected_category, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

        Query query = FirebaseFirestore.getInstance().collection("Intern")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Cart");
        options= new FirestoreRecyclerOptions.Builder<Dataclass>()
                .setQuery(query, Dataclass.class)
                .build();
        recyclerAdapter = new FirestoreRecyclerAdapter<Dataclass, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);
                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Dataclass model) {

                holder.setCartname(model.getName());
                holder.setCartimage(model.getImg());
                holder.setCartprice(model.getPrice());
                holder.setCartquantity(model.getQuantity());

                holder.itemView.findViewById(R.id.button_incr).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "success added", Toast.LENGTH_SHORT).show();
                        Increment_button(model);
                    }
                });
                holder.itemView.findViewById(R.id.button_dec).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "success removed", Toast.LENGTH_SHORT).show();
                        Decrement_button(model);
                    }
                });

                total+= Integer.parseInt(model.getPrice());
            }

        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        ((MainScreen)getActivity()).getSupportActionBar().setTitle("CART");
        return v;

    }


    @Override
    public void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerAdapter.startListening();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void Increment_button(Dataclass model){
        HashMap<String, Object> map = new HashMap<>();
        String str;
        map.put("name", model.getName());
        int num = Integer.parseInt(model.getQuantity());
        map.put("quantity", String.valueOf(num+1));
        map.put("price", model.getPrice());
        map.put("img", model.getImg());
        FirebaseFirestore.getInstance().collection("Intern")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Cart")
                .document(model.getName())
                .set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Decrement_button(Dataclass model){
        HashMap<String, Object> map = new HashMap<>();
        String str;
        map.put("name", model.getName());
        int num = Integer.parseInt(model.getQuantity());
        map.put("quantity", String.valueOf(num-1));
        map.put("price", model.getPrice());
        map.put("img", model.getImg());
        FirebaseFirestore.getInstance().collection("Intern")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Cart")
                .document(model.getName())
                .set(map, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

