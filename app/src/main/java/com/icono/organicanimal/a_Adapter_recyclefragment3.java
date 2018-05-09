package com.icono.organicanimal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alfo6-10 on 2018-03-21.
 */

public class a_Adapter_recyclefragment3 extends RecyclerView.Adapter {

    private Context context;

    private ArrayList<ArrayList<a_Fragment3_Item>> items4;
    ArrayList<ArrayList<a_Fragment3_Item2>> items5;
    ArrayList<String> items2 = new ArrayList<>();

    ArrayList<a_Fragment3_Item2> items3 = new ArrayList<>();
    ArrayList<a_Fragment3_Item2> items6 = new ArrayList<>();

    String CareNm;

    String addr;
    String htel;
    String memberNm;
    String orgNm;
    String tel;

    String item;
    String elseitem = "한국동물구조관리협회";
    ArrayList<a_Fragment3_Item2> items8;
    ArrayList<ArrayList<a_Fragment3_Item2>> items9 = new ArrayList<>();


    public a_Adapter_recyclefragment3(Context context, ArrayList<ArrayList<a_Fragment3_Item>> items4, ArrayList<ArrayList<a_Fragment3_Item2>> items5) {
        this.context = context;
        this.items4 = items4;
        this.items5 = items5;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_fragment_page3_recycler_item, parent, false);
        a_Adapter_recyclefragment3.VH vh = new a_Adapter_recyclefragment3.VH(view);
        CareNm = "";
        if(items4.size()<=0){
            CareNm = elseitem;
        }else{
            for(ArrayList<a_Fragment3_Item> t : items4){
                for(a_Fragment3_Item item2 : t){
                    items2.add(item2.getCareNm());
                }
            }
        }
        for(ArrayList<a_Fragment3_Item2> t : items5) {
            for (a_Fragment3_Item2 itme2 : t) {
                items3.add(itme2);
            }
        }

        final String needUrl4 = getUrl();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    onXml4(needUrl4);
                }catch (Exception e){
                    Log.i("myerror","thread fail");
                }
            }
        });
        thread.start();

        try{
            thread.join();
        }catch (Exception e){
            Log.i("myerror","thread join fail");
        }

        for(ArrayList<a_Fragment3_Item2> t : items9){
            for(a_Fragment3_Item2 item2 : t){
                items6.add(item2);
            }
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final VH vh = (VH)holder;
        if(items2 != null){
            if(items2.size()<=0){
                vh.txt_result.setText(elseitem);
            }else{
                item = items2.get(position);
                vh.txt_result.setText(item);
            }
        }
        if(items3 != null){
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String shelter = vh.txt_result.getText().toString();

                    if(shelter.equals(elseitem)){
                        int pos = 0;

                        for(int i =0; i<items6.size();i++){
                            String OrgNm = items6.get(i).getOrgNm();
                            OrgNm = delSpace(OrgNm);
                            if(OrgNm.equals(elseitem)){
                                pos = i;
                            }
                            OrgNm = "";
                        }
                        Log.i("myerror", "items6.size : " + items6.size() + "pos : " + pos);
                        intoFocus(pos, false);
                    }else{
                        int pos = 0;

                        for(int i=0; i<items3.size();i++){
                            String OrgNm = items3.get(i).getOrgNm();
                            OrgNm = delSpace(OrgNm);
                            if(OrgNm.equals(shelter)){
                                pos = i;
                            }
                            OrgNm = "";
                        }
                        Log.i("myerror", "items6.size : " + items3.size() + "pos : " + pos);
                        intoFocus(pos, true);
                    }
                }
            });
        }
    }

    public static String delSpace(String str){
        String result = "";

        for(int i = 0;i<str.length();i++){
            if(str.charAt(i) != ' '){
                result += str.charAt(i);
            }
        }
        return result;
    }

    public void intoFocus(int pos, boolean isItems){
        Intent intent = new Intent(context, FocusShelter.class);
        context.startActivity(intent);
        FocusShelter.setItems3(items3, items6, pos, isItems);
       // Log.i("myerror","recyfrag3 in focus: " + focusShelter.items.toString());
        //Intent intent = new Intent(context, FocusShelter.class);
        //intent.putExtra("items",items3);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(intent);
    }

    public String getUrl(){
        return "http://openapi.animal.go.kr/openapi/service/rest/recordAgencySrvc/recordAgency?addr=%EA%B2%BD%EA%B8%B0%EB%8F%84%20%EC%96%91%EC%A3%BC%EC%8B%9C&pageNo=1&numOfRows=50&ServiceKey=G949YEG0RijThW3AnQQe0PjEbK%2FNCpKbcCaR08%2F%2F5DjxChRH2um%2FE%2BS0uvsSbVSBOeDCqh0e3%2BxNX34fvtPuRg%3D%3D";
    }


    synchronized public void onXml4(String needUrl){
        try{
            URL url = new URL(needUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(is, "utf-8");
            int eventType = xpp.next();
            a_Fragment3_Item2 item = null;
            String tagName = "";

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT :
                        break;
                    case XmlPullParser.START_TAG :
                        tagName = xpp.getName();
                        if(tagName.equals("item")) {
                            item = new a_Fragment3_Item2();
                            items8 = new ArrayList<>();
                        }else if(tagName.equals("addr")){
                            xpp.next();
                            item.setAddr(xpp.getText());
                        }else if(tagName.equals("htel")){
                            xpp.next();
                            item.setHtel(xpp.getText());
                        }else if(tagName.equals("memberNm")){
                            xpp.next();
                            item.setMemberNm(xpp.getText());
                        }else if(tagName.equals("orgNm")){
                            xpp.next();
                            item.setOrgNm(xpp.getText());
                        }else if(tagName.equals("tel")){
                            xpp.next();
                            item.setTel(xpp.getText());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = xpp.getName();
                        if(tagName.equals("items")){
                            //  Log.i("myerror","in end tag with body");
                            //  Log.i("myerror","in end tag with body items4 : "+items4.size());
                            return;
                        }
                        if(tagName.equals("item")){
                            items8.add(item);
                            items9.add(items8);
                            item = null;
                            items8 = null;
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                }
                eventType = xpp.next();
            }
        }catch (Exception e){
            Log.i("myerror","in xml2 " + e.getMessage());
        }
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
