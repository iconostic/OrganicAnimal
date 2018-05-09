package com.icono.organicanimal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class FocusShelter extends AppCompatActivity {

    public static String APIKey_staticmap = "AIzaSyC3eLZ3ivFjl3sy9cZvZUAzwFspeeTHw68";
    public static String APIKey_geocode = "AIzaSyBsbu2i3697YwHOITqNRJPErjF_2X3rLRA";

   // static FocusShelter focusShelter;

    static ArrayList<a_Fragment3_Item2> items;
    static ArrayList<a_Fragment3_Item2> items2;

    String elseitem = "한국동물구조관리협회";

    TextView tv;
    ImageView iv;

    TextView txt_toolbar;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    FloatingActionButton fab;
    FloatingActionButton fab2;

    Toolbar toolbar;

    TextView footer;

    static int position;
    static boolean isItems;

    static String sido;
    static String gungu;

    String lat = "";
    String lng = "";


    public static void setItems3(ArrayList<a_Fragment3_Item2> items3, ArrayList<a_Fragment3_Item2> items6, int pos, boolean items2is) {
        items = items3;
        items2 = items6;
        position = pos;
        isItems = items2is;
    }

    public static void setJuso(String si, String gun){
        sido = si;
        gungu = gun;
    }

    public FocusShelter() {
    }

    public FocusShelter(ArrayList<a_Fragment3_Item2> items3) {
        this.items = items3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        txt_toolbar = findViewById(R.id.toolbar_txt);
        txt_toolbar.setText(R.string.app_name);

//        drawerLayout = findViewById(R.id.layout_drawer);
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
//        drawerLayout.addDrawerListener(drawerToggle);
//
//        drawerToggle.syncState();

     //   LinearLayout layout = findViewById(R.id.viewview);


        //focusShelter = this;

        //items = (ArrayList<a_Fragment3_Item2>) getIntent().getSerializableExtra("items");


        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FocusShelter.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                SelecActivity.sa.finish();
            }
        });
    }
//    public static FocusShelter getInstance(){
//        return focusShelter;
//    }

    @Override
    protected void onStart() {
        super.onStart();

        if(isItems){
            if(items != null){
                final String url = jsonUrl(items.get(position).getAddr());
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadJson(url);
                    }
                });
                thread.start();
                try{
                    thread.join();
                }catch (Exception e) {
                    Log.i("myerror", "thread fail");
                }

            }else{
                Log.i("myerror","shelter in items : null");
            }
            final String imgPath = getimgurl(items.get(position).getAddr());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(FocusShelter.this).load(imgPath).into(iv);

                                tv.setText(items.get(position).getOrgNm());
                                tv.append("\n"+items.get(position).getTel());

                                tv.setTextSize(20);
                                tv.append("\n"+items.get(position).getAddr());
                            }
                        });
                    }catch (Exception e){
                        Log.i("myerror","glide fail");
                    }

                }
            }).start();
        }else{
            if(items2 != null){
                if(items2.get(position).getOrgNm().equals(elseitem)){
                    final String url = jsonUrl(items2.get(position).getAddr());
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            loadJson(url);
                        }
                    });
                    thread.start();
                    try{
                        thread.join();
                    }catch (Exception e) {
                        Log.i("myerror", "thread fail");
                    }

                }
            }else{
                Log.i("myerror","shelter in items2 : null");
            }

            final String imgPath = getimgurl(items2.get(position).getAddr());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(FocusShelter.this).load(imgPath).into(iv);

                                tv.setText(items2.get(position).getOrgNm());
                                tv.append("\n"+items2.get(position).getTel());

                                tv.setTextSize(20);
                                tv.append("\n"+items2.get(position).getAddr());
                            }
                        });
                    }catch (Exception e){
                        Log.i("myerror","glide fail");
                    }

                }
            }).start();
        }

    }

    public static String encodeURIComponent(String component)   {
        String result = null;
        try {
            result = URLEncoder.encode(component, "UTF-8");
        }catch (UnsupportedEncodingException e) {
            result = component;
        }

        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        items = null;
        items2 = null;
        position = 0;

        finish();
    }

    public String getimgurl(String address){
        String s = "\\(";
        String addr = encodeURIComponent(address.split(s)[0]);
        String url = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center="+addr+
                "&zoom=15&size=500x500&maptype=roadmap"+
                "&markers=color:blue%7Clabel:S%7C"+lat +","+lng+
                "&key="+APIKey_staticmap;
        Log.i("myerror","imgurl : " + url);
        return url;
    }

    public String jsonUrl(String address){
        String url = "https://maps.googleapis.com/maps/api/geocode/json?"+
                "address="+ address
                +"&key="+APIKey_geocode;

        return url;
    }

    public void loadJson(String url){
        StringBuffer buffer = new StringBuffer();
        StringBuffer result = new StringBuffer();
        String location = "";

        try {
            URL urlJSON = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlJSON.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            JSONArray jsonArray = new JSONObject(buffer.toString()).getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonData = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
                lat = jsonData.optString("lat");
                lng = jsonData.optString("lng");
                result.append(lat);
                result.append(lng);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("myerror","lat : " + lat + " / " + "lng : " + lng);
    }
}

