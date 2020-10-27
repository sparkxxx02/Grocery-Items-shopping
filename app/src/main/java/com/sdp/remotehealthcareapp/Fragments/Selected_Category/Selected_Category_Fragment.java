package com.sdp.remotehealthcareapp.Fragments.Selected_Category;

import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sdp.remotehealthcareapp.Activities.MainScreen;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.description.DescriptionFragment;
import com.sdp.remotehealthcareapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Selected_Category_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore firebaseFirestore;
    String category;
    private FirestoreRecyclerAdapter recyclerAdapter;
    FirestoreRecyclerOptions<Dataclass> options;
    View v;
    public Selected_Category_Fragment(String category) {
        this.category=category;
        // Required empty public constructor
    }
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_selected_category, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        Query query = FirebaseFirestore.getInstance().collection("Intern")
                .document("Items").collection("food items")
                .whereEqualTo("category",category );
         options= new FirestoreRecyclerOptions.Builder<Dataclass>()
                .setQuery(query, Dataclass.class)
                .build();
         recyclerAdapter = new FirestoreRecyclerAdapter<Dataclass, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_selected_items,parent,false);
                    return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Dataclass model) {
                holder.setTxtTitle(model.getName());
                //holder.set(model.getQuantity());
                holder.setTxtPrice(model.getPrice());
                holder.setimg(model.getImg());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), model.getName(), Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                                new DescriptionFragment(model)).addToBackStack("Selected Fragment").commit();

                    }
                });

            }

        };
         recyclerView.setHasFixedSize(true);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         recyclerView.setAdapter(recyclerAdapter);

       ((MainScreen)getActivity()).getSupportActionBar().setTitle(category);

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
}

