package com.icono.organicanimal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class a_FragmentPage2 extends Fragment{

    a_Adapter_recyclefragment2 adapter;
    RecyclerView recycler;

    ArrayList<ArrayList<a_Fragment1_Item>> items4 = new ArrayList<>();
    
    FloatingActionButton fab;

    public void setItems(ArrayList<ArrayList<a_Fragment1_Item>> items4){
        this.items4 = items4;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a__fragment_page1, container, false);

        recycler = view.findViewById(R.id.recycler);
        adapter = new a_Adapter_recyclefragment2(view.getContext(), items4);
        recycler.setAdapter(adapter);

        //recycler.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        if(items4 != null){
            adapter.notifyDataSetChanged();
        }
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycler.scrollToPosition(0);
            }
        });

        return view;
    }

    public void scrollTop(){
        //recycler.scrollToPosition(0);
    }
}
