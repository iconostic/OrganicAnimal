package com.icono.organicanimal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    ImageView img_dog;
    ImageView img_cat;

    View view;
    MaterialDialog dig;
    Spinner spinner, spinner2;

    ArrayAdapter adapter, adapter2;
    String sido = "";
    String gungu = "";
    String kind = "";

    int digyear = 0;
    int digmonth = 0;
    int digday = 0;

    int year = 0;
    int month = 0;
    int day = 0;
    int year2 = 0;
    int month2 = 0;
    int day2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GregorianCalendar calendar = new GregorianCalendar();
        digyear = calendar.get(java.util.Calendar.YEAR);
        digmonth = calendar.get(java.util.Calendar.MONTH);
        digday = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        img_dog = findViewById(R.id.img_dog);
        img_cat = findViewById(R.id.img_cat);

        img_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kind = "dog";
                materialdialog();
            }
        });

        img_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kind = "cat";
                materialdialog();
            }
        });
    }

    public void materialdialog(){
        final boolean wrapInScrollView = true;
        dig = new MaterialDialog.Builder(this)
                .title("정보입력")
                .customView(R.layout.activity_custom_dialog, wrapInScrollView)
                .positiveText("Go")
                .canceledOnTouchOutside(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        if(year > year2 && month > month2 && day > day2){
                            Toast.makeText(MainActivity.this, "잘못된 날짜 입니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(year2 == digyear && month2 == digmonth && day2 == digday){
                            Toast.makeText(MainActivity.this, "당일은 검색할 수 없습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        SelecActivity selec = new SelecActivity();
                        Intent intent = new Intent(MainActivity.this, selec.getClass());
                        intent.putExtra("kind", kind);
                        intent.putExtra("sido", sido);
                        intent.putExtra("gungu", gungu);
                        intent.putExtra("year",year);
                        intent.putExtra("month",month);
                        intent.putExtra("day",day);
                        intent.putExtra("year2",year2);
                        intent.putExtra("month2",month2);
                        intent.putExtra("day2",day2);
                        startActivity(intent);
                    }
                }).show();
        view = dig.getCustomView();

        DatePicker.OnDateChangedListener listener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                if(i == digyear){
                    year = digyear;
                }else{
                    year = i;
                }
                if(i1 == digmonth){
                    month = digmonth;
                }else{
                    month = i1+1;
                }
                if(i2 == digday){
                    day = digday;
                }else{
                    day = i2;
                }
            }
        };

        DatePicker.OnDateChangedListener listener2 = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                if(i == digyear){
                    year2 = digyear;
                }else{
                    year2 = i;
                }
                if(i1 == digmonth){
                    month2 = digmonth;
                }else{
                    month2 = i1+1;
                }
                if(i2 == digday){
                    day2 = digday;
                }else{
                    day2 = i2;
                }
            }
        };
        DatePicker date1 = view.findViewById(R.id.datepicker1);
        DatePicker date2 = view.findViewById(R.id.datepicker2);

        date1.init(digyear, digmonth, digday, listener);
        date2.init(digyear, digmonth, digday-1, listener2);

        spinner = view.findViewById(R.id.spinner);
        spinner2 = view.findViewById(R.id.spinner2);

        adapter = ArrayAdapter.createFromResource(this,R.array.sido,R.layout.spinner_selected);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapter.getItem(i).equals("서울특별시")){
                    sido = "서울특별시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.seoul,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("부산광역시")){
                    sido = "부산광역시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.busan,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("대구광역시")){
                    sido = "대구광역시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.deagu,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("인천광역시")){
                    sido = "인천광역시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.incheon,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("광주광역시")){
                    sido = "광주광역시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.gwangju,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("세종특별자치시")){
                    sido = "세종특별자치시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.sejong,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("대전광역시")){
                    sido = "대전광역시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.deajeon,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("울산광역시")){
                    sido = "울산광역시";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.ulsan,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("경기도")){
                    sido = "경기도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.kyungki,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("강원도")){
                    sido = "강원도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.kangwon,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("충청북도")){
                    sido = "충청북도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.chungbuk,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("충청남도")){
                    sido = "충청남도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.chunnam,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("전라북도")){
                    sido = "전라북도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.jeonbuk,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("전라남도")){
                    sido = "전라남도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.jeonnam,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("경상북도")){
                    sido = "경상북도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.kyungbuk,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("경상남도")){
                    sido = "경상남도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.kyungnam,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if(adapter.getItem(i).equals("제주특별자치도")){
                    sido = "제주특별자치도";
                    adapter2 = ArrayAdapter.createFromResource(view.getContext(),R.array.jeju,R.layout.spinner_selected);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            gungu = adapter2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}