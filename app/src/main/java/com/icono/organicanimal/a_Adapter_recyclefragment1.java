package com.icono.organicanimal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by alfo6-10 on 2018-03-21.
 */

public class a_Adapter_recyclefragment1 extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ArrayList<a_Fragment1_Item>> items4;
    ArrayList<a_Fragment1_Item> items2 = new ArrayList<>();

    String Age;
    String CareNm;
    String CareAddr;
    String ColorCd;
    String Popfile;
    String HappenDt;
    String Happenplace;
    String KindCd;
    String SexCd;

    boolean isVisible = false;

    public a_Adapter_recyclefragment1(Context context, ArrayList<ArrayList<a_Fragment1_Item>> items4) {
        this.context = context;
        this.items4 = items4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_fragment_page1_recycler_item, parent, false);
        a_Adapter_recyclefragment1.VH vh = new a_Adapter_recyclefragment1.VH(view);
        Age = "";
        CareNm = "";
        CareAddr = "";
        ColorCd = "";
        Popfile = "";
        HappenDt = "";
        Happenplace = "";
        KindCd = "";
        SexCd = "";

        for(ArrayList<a_Fragment1_Item> t : items4){
            for(a_Fragment1_Item item2 : t){
                items2.add(item2);
            }
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final VH vh = (VH)holder;
        if(items2 != null){
            final a_Fragment1_Item item = items2.get(position);
            vh.txt_result.setText("");
            vh.txt_result.append(item.getAge()+"\n");
            vh.txt_result.append(item.getCareNm()+"\n");
            vh.txt_result.append(item.getCareAddr()+"\n");
            vh.txt_result.append(item.getColorCd()+"\n");
            Glide.with(context).load(item.getFilename()).into(vh.img_result);
            vh.txt_result.append(item.getHappenDt()+"\n");
            vh.txt_result.append(item.getHappenPlace()+"\n");
            vh.txt_result.append(item.getKindCd()+"\n");
            if(item.getSexCd() != null){
                if(item.getSexCd().equals("M")){
                    vh.txt_result.append("수컷\n");
                }else{
                    vh.txt_result.append("암컷\n");
                }
            }

            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intoFocus(position);
                }
            });
        }
    }

    public void intoFocus(int position){
        FocusItem fi = new FocusItem();
        fi.setItem(items2, position);

        Intent intent = new Intent(context, FocusItem.class);
        intent.putExtra("items2", items2);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return items4.size();
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView img_result;
        TextView txt_result;

        public VH(final View itemView) {
            super(itemView);

            img_result = itemView.findViewById(R.id.img_result);
            txt_result = itemView.findViewById(R.id.txt_result);
        }
    }
}
