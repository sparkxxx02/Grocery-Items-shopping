package com.sdp.remotehealthcareapp.Fragments.HomeFragment_Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sdp.remotehealthcareapp.Fragments.HomeFragment;
import com.sdp.remotehealthcareapp.Fragments.Selected_Category.Selected_Category_Fragment;
import com.sdp.remotehealthcareapp.R;

public class HomeFragment_one extends Fragment {

    View v;
    String[] CategoryNames = {"fruits", "vegetables", "bev", "grains"};
    int[] CategoryImages = {R.drawable.fruits, R.drawable.vegetable, R.drawable.bev, R.drawable.grains};

    public HomeFragment_one() {
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
         v=inflater.inflate(R.layout.fragment_home_one, container, false);
        GridView gridView;


        //finding listview
        gridView = v.findViewById(R.id.gridview);

        CustomHomeGrid customGrid = new CustomHomeGrid(getActivity(), CategoryNames, CategoryImages);
        gridView.setAdapter(customGrid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), CategoryNames[position], Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_sc,
                        new Selected_Category_Fragment(CategoryNames[position])).addToBackStack("Selected Fragment").commit();
                //startActivity(new Intent(getActivity(), Selected_Category_Fragment.class));
            }

        });
        return v;
    }

}