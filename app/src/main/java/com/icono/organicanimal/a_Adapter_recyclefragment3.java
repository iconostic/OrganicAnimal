package com.icono.organicanimal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alfo6-10 on 2018-03-21.
 */

public class a_Adapter_recyclefragment3 extends RecyclerView.Adapter {

    private Context context;

    private ArrayList<ArrayList<a_Fragment3_Item>> items4;
    ArrayList<String> items2 = new ArrayList<>();

    String CareNm;

    public a_Adapter_recyclefragment3(Context context, ArrayList<ArrayList<a_Fragment3_Item>> items4) {
        this.context = context;
        this.items4 = items4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_fragment_page3_recycler_item, parent, false);
        a_Adapter_recyclefragment3.VH vh = new a_Adapter_recyclefragment3.VH(view);
        CareNm = "";

        for(ArrayList<a_Fragment3_Item> t : items4){
            for(a_Fragment3_Item item2 : t){
                items2.add(item2.getCareNm());
            }
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        if(items2 != null){
            String item = items2.get(position);
            vh.txt_result.setText(item);
        }

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intoFocus();
            }
        });
    }

    public void intoFocus(){
        Intent intent = new Intent(context, FocusShelter.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return items4.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView txt_result;

        public VH(View itemView) {
            super(itemView);
            txt_result = itemView.findViewById(R.id.txt_result);
        }
    }
}
