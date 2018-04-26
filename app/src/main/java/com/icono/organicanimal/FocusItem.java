package com.icono.organicanimal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FocusItem extends AppCompatActivity {

    CircleImageView profile;

    RelativeLayout layout, empty;
    ScrollView scrollView;

    TextView noticeNo, happendDt,age,kindCd,weight,colorCd,sexCdm, processState,specialMark,happenPlace,noticeSdt,noticeEdt,careNm;

    ImageView focus;

    ArrayList<a_Fragment1_Item> items2;
    int position;

    boolean isVisible = false;
    boolean isclose = false;


    public void setItem(ArrayList<a_Fragment1_Item> items2, int position){
        this.items2 = items2;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_item);

        profile = findViewById(R.id.img_profile);
        focus = findViewById(R.id.img_focus);

        noticeNo = findViewById(R.id.txt_noticeNo);
        happendDt = findViewById(R.id.txt_happenDt);
        age = findViewById(R.id.txt_age);
        kindCd = findViewById(R.id.txt_kindCd);
        weight = findViewById(R.id.txt_weight);
        colorCd = findViewById(R.id.txt_colorCd);
        sexCdm = findViewById(R.id.txt_sexCd);
        processState = findViewById(R.id.txt_precessState);
        specialMark = findViewById(R.id.txt_specialMark);
        happenPlace = findViewById(R.id.txt_happenPlace);
        noticeSdt = findViewById(R.id.txt_noticeSdt);
        noticeEdt = findViewById(R.id.txt_noticeEdt);
        careNm = findViewById(R.id.txt_careNm);


        if(getIntent() != null){
            Intent intent = getIntent();
            items2 = (ArrayList<a_Fragment1_Item>)intent.getSerializableExtra("items2");
            position = intent.getIntExtra("position",0);
        }

        if(items2 != null){
            a_Fragment1_Item item = items2.get(position);
            noticeNo.setText(item.getNoticeNo());
            happendDt.setText(item.getHappenDt());
            age.setText(item.getAge());
            kindCd.setText(item.getKindCd());
            weight.setText(item.getWeight());
            colorCd.setText(item.getColorCd());
            sexCdm.setText(item.getSexCd());
            processState.setText(item.getProcessState());
            specialMark.setText(item.getSpecialMark());
            happenPlace.setText(item.getHappenPlace());
            noticeSdt.setText(item.getNoticeSdt());
            noticeEdt.setText(item.getNoticeEdt());
            careNm.setText(item.getCareNm());

            Glide.with(this).load(items2.get(position).getPopfile()).into(profile);
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LinearLayout linearLayout = findViewById(R.id.layout_focus);
                    if(isVisible){
                        isVisible = false;
                        linearLayout.setVisibility(View.GONE);
                        return;
                    }else{
                        isVisible = true;
                        Glide.with(FocusItem.this).load(items2.get(position).getPopfile()).into(focus);
                        linearLayout.setVisibility(View.VISIBLE);
                        return;
                    }
                }
            });
            focus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final LinearLayout linearLayout = findViewById(R.id.layout_focus);
                    isVisible = false;
                    linearLayout.setVisibility(View.GONE);
                    return;
                }
            });
        }
        setFinishOnTouchOutside(false);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        finish();
    }
}
