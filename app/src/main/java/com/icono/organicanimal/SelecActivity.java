package com.icono.organicanimal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.Task;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class SelecActivity extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener{

    String[] cidocode = {"6110000","6260000","6270000","6280000","6290000","5690000","6300000","6310000","6410000","6420000","6430000","6440000","6450000","6460000","6470000","6480000","6500000"};
    String[] cidoName = {"서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","세종특별자치시","대전광역시","울산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치도"};

    String[] Seoulcode = {"6119999", "3220000", "3240000", "3080000", "3150000", "6119998", "3200000", "3040000", "3160000", "3170000", "3100000", "3090000", "3050000", "3190000", "3130000", "3120000", "3210000", "3030000", "3070000", "3230000", "3140000", "3180000", "3020000", "3110000", "3000000", "3010000", "3060000"};
    String[] SeoulName = {"가정보호","강남구","강동구","강북구","강서구","개별사업","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"};

    String[] Busancode = {"3360000", "3350000", "3400000", "3310000", "3270000", "3300000", "3290000", "3320000", "3390000", "3340000", "3260000", "3380000", "3370000", "3280000", "3250000", "3330000"};
    String[] BusanName = {"강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구"};

    String[] Deagucode = {"3440000", "3470000", "3480000", "3420000", "3450000", "3430000", "3460000", "3410000"};
    String[] DeaguName = {"남구", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구"};

    String[] IncheonCode = {"3570000, 3550000, 3510000, 3530000, 3500000, 3540000, 3560000, 3520000, 3580000, 3490000"};
    String[] IncheonName = {"강화군, 계양구, 남구, 남동구, 동구, 부평구, 서구, 연수구, 옹진군, 중구"};

    String[] GwangjuCode = {"3630000, 3610000, 3590000, 3620000, 3600000"};
    String[] GwangjuName = {"광산구, 남구, 동구, 북구, 서구"};

    String[] DeajunCode = {"3680000, 3640000, 3660000, 3670000, 3650000"};
    String[] DeajunName = {"대덕구, 동구, 서구, 유성구, 중구"};

    String[] UlsanCode = {"3700000, 3710000, 3720000, 3730000, 3690000"};
    String[] UlsanName = {"남구, 동구, 북구, 울주군, 중구"};

    String[] KyungkiCode = {"4160000, 3940000, 3970000, 3900000, 5540000, 3980000, 4020000, 4090000, 3990000, 3920000, 3860000, 3780000, 3740000, 4010000, 3930000, 4080000, 3830000, 5590000, 4170000, 5700000, 4140000, 4000000, 4050000, 5630000, 4030000, 3820000, 4070000, 4060000, 3910000, 5600000, 4040000, 5530000"};
    String[] KyungkiName = {"가평군, 고양시, 과천시, 광명시, 광주시, 구리시, 군포시, 김포시, 남양주시, 동두천시, 부천시, 성남시, 수원시, 시흥시, 안산시, 안성시, 안양시, 양주시, 양평군, 여주시, 연천군, 오산시, 용인시, 용인시 기흥구, 의왕시, 의정부시, 이천시, 파주시, 평택시, 포천시, 하남시, 화성시"};

    String[] KangwonCode = {"4200000, 4340000, 4210000, 4240000, 4230000, 4320000, 4350000, 4270000, 4190000, 4330000, 4290000, 4300000, 4180000, 4220000, 4280000, 4250000, 4310000, 4260000"};
    String[] KangwonName = {"강릉시, 고성군, 동해시, 삼척시, 속초시, 양구군, 양양군, 영월군, 원주시, 인제군, 정선군, 철원군, 춘천시, 태백시, 평창군, 홍천군, 화천군, 횡성군"};

    String[] ChungbukCode = {"4460000, 4480000, 4420000, 4440000, 4430000, 4470000, 4400000, 5570000, 4450000, 5710000, 4390000"};
    String[] ChungbukName = {"괴산군, 단양군, 보은군, 영동군, 옥천군, 음성군, 제천시, 증평군, 진천군, 청주시, 충주시"};

    String[] ChungnamCode = {"5580000, 4500000, 4550000, 4540000, 5680000, 4510000, 4570000, 4530000, 4580000, 4520000, 4560000, 4610000, 4490000, 4590000, 4620000, 4600000"};
    String[] ChungnamName = {"계룡시, 공주시, 금산군, 논산시, 당진시, 보령시, 부여군, 서산시, 서천군, 아산시, 연기군, 예산군, 천안시, 청양군, 태안군, 홍성군"};

    String[] JunbukCode = {"4780000, 4670000, 4710000, 4700000, 4740000, 4790000, 4770000, 4720000, 4680000, 4760000, 4750000, 4640000, 4690000, 4730000"};
    String[] JunbukName = {"고창군, 군산시, 김제시, 남원시, 무주군, 부안군, 순창군, 완주군, 익산시, 임실군, 장수군, 전주시, 정읍시, 진안군"};

    String[] JunnamCode = {"4920000, 4880000, 4860000, 4840000, 4870000, 4830000, 4850000, 4800000, 4950000, 4890000, 4820000, 5010000, 4810000, 4970000, 4940000, 4990000, 4980000, 4910000, 5000000, 4960000, 4930000, 4900000"};
    String[] JunnamName = {"강진군, 고흥군, 곡성군, 광양시, 구례군, 나주시, 담양군, 목포시, 무안군, 보성군, 순천시, 신안군, 여수시, 영광군, 영암군, 완도군, 장성군, 장흥군, 진도군, 함평군, 해남군, 화순군"};

    String[] KyungbukCode = {"5130000, 5050000, 5200000, 5080000, 5140000, 5060000, 5120000, 5240000, 5110000, 5210000, 5070000, 5180000, 5170000, 5090000, 5100000, 5230000, 5260000, 5250000, 5150000, 5190000, 5160000, 5220000, 5020000"};
    String[] KyungbukName = {"경산시, 경주시, 고령군, 구미시, 군위군, 김천시, 문경시, 봉화군, 상주시, 성주군, 안동시, 영덕군, 영양군, 영주시, 영천시, 예천군, 울릉군, 울진군, 의성군, 청도군, 청송군, 칠곡군, 포항시"};

    String[] KyungnamCode = {"5370000, 5470000, 5420000, 5350000, 5430000, 5360000, 5340000, 5450000, 5380000, 5390000, 5310000, 5410000, 5280000, 5670000, 5320000, 5330000, 5440000, 5400000, 5460000, 5480000"};
    String[] KyungnamName = {"거제시, 거창군, 고성군, 김해시, 남해군, 밀양시, 사천시, 산청군, 양산시, 의령군, 진주시, 창녕군, 창원 마산합포회원구, 창원 의창성산구, 창원 진해구, 통영시, 하동군, 함안군, 함양군, 합천군"};

    String[] JejuCode ={"6520000","6510000","6500000"};
    String[] JejuName = {"서귀포시","제주시","제주특별자치도"};

    //개 : 417000 - 고양이 : 422400 - 기타 : 429900

    public static final String APIKey = "G949YEG0RijThW3AnQQe0PjEbK%2FNCpKbcCaR08%2F%2F5DjxChRH2um%2FE%2BS0uvsSbVSBOeDCqh0e3%2BxNX34fvtPuRg%3D%3D";

    public static Context context;

    TabLayout tabs;
    ViewPager pager;
    TextView txt_toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    String kind = "";
    String sido = "";
    String gungu = "";

    int year = 0;
    int month = 0;
    int day = 0;
    int year2 = 0;
    int month2 = 0;
    int day2 = 0;

    Toolbar toolbar;

    String upkind = "";

    ArrayList<a_Fragment1_Item> items;
    ArrayList<a_Fragment1_Item> items2;
    ArrayList<a_Fragment3_Item> items3;

    ArrayList<ArrayList<a_Fragment1_Item>> items4 = new ArrayList<>();
    ArrayList<ArrayList<a_Fragment1_Item>> items5 = new ArrayList<>();
    ArrayList<ArrayList<a_Fragment3_Item>> items6 = new ArrayList<>();

    boolean isFragment = false;
    boolean isFragment2 = false;
    boolean isFragment3 = false;

    ProgressWheel wheel;
    RelativeLayout layout;

    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;
    TextView txt_name, txt_mail;
    ImageView img_header;

    String displayName;
    String email;
    String Photourl;

    public static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec);
        context = this;
        Intent intent = getIntent();

        wheel = findViewById(R.id.progress_wheel);
        layout = findViewById(R.id.wheel_layout);
        wheel.spin();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt_toolbar = findViewById(R.id.toolbar_txt);

        if(intent != null) {
            kind = intent.getStringExtra("kind");
            sido = intent.getStringExtra("sido");
            gungu = intent.getStringExtra("gungu");
            year = intent.getIntExtra("year", 0);
            month = intent.getIntExtra("month", 0);
            day = intent.getIntExtra("day", 0);
            year2 = intent.getIntExtra("year2", 0);
            month2 = intent.getIntExtra("month2", 0);
            day2 = intent.getIntExtra("day2", 0);
        }

        final String needUrl = loadurl();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    onXml(needUrl);
                }catch (Exception e){
                    Log.i("myerror","thread fail");
                }
            }
        });
        thread.start();

        final String needUrl2 = loadurl2();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    onXml2(needUrl2);
                }catch (Exception e){
                    Log.i("myerror","thread2 fail");
                }
            }
        });
        thread2.start();

        final String needUrl3 = loadurl3();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    onXml3(needUrl3);
                }catch (Exception e){
                    Log.i("myerror","thread3 fail");
                }
            }
        });
        thread3.start();

        try {
            thread.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Log.i("myerror","thread fail");
        }

        if(isFragment && isFragment2 && isFragment3){
            if (kind.equals("dog")) {
                txt_toolbar.setText("개");
            } else if (kind.equals("cat")) {
                txt_toolbar.setText("고양이");
            }

            layout.setVisibility(View.GONE);
            wheel.stopSpinning();

            drawerLayout = findViewById(R.id.layout_drawer);
            navigationView = findViewById(R.id.navi);
            navigationView.setItemIconTintList(null);


            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
            drawerLayout.addDrawerListener(drawerToggle);

            drawerToggle.syncState();

            ViewPager viewPager = findViewById(R.id.viewpager);
            a_Adapter_fragment adapter = new a_Adapter_fragment(getSupportFragmentManager());

            a_FragmentPage1 fragment1 = new a_FragmentPage1();
            a_FragmentPage2 fragment2 = new a_FragmentPage2();
            a_FragmentPage3 fragment3 = new a_FragmentPage3();

            fragment1.setItems(items5);
            fragment2.setItems(items4);
            fragment3.setItems(items6);

            adapter.addFragment(fragment1, sido);
            adapter.addFragment(fragment2, gungu);
            adapter.addFragment(fragment3, "보호소");

            viewPager.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);

            View header = navigationView.getHeaderView(0);
            txt_name = header.findViewById(R.id.txt_name);
            txt_mail = header.findViewById(R.id.txt_mail);
            img_header = header.findViewById(R.id.img_header);

            signInButton = header.findViewById(R.id.sign_in_button);

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            if(account == null){
                updateUI(false);

                mGoogleApiClient = new GoogleApiClient.Builder(this)
                        .enableAutoManage(this,this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();

                signInButton.setSize(SignInButton.SIZE_STANDARD);
                signInButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        signIn();
                    }
                });
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch ((item.getItemId())){
                            case R.id.sign_in_button :
                                signIn();
                                break;
                            case R.id.menu_a :
                                Toast.makeText(SelecActivity.this, "준비중", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_b :
                                Toast.makeText(SelecActivity.this, "준비중", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
            }else{
                GoogleSignInAccount account1 = GoogleSignIn.getLastSignedInAccount(this);
                displayName = account1.getDisplayName().toString();
                email = account1.getEmail().toString();
                Photourl = account1.getPhotoUrl().toString();

                txt_name.setText(displayName);
                txt_mail.setText(email);
                Glide.with(this).load(Photourl).into(img_header);
                updateUI(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            txt_name.setText(account.getDisplayName().toString());
            txt_mail.setText(account.getEmail().toString());
            Glide.with(this).load(account.getPhotoUrl()).into(img_header);
            updateUI(true);
        } catch (ApiException e) {
            Log.i("myerror", "signInResult:failed code=" + e.getStatusCode());
            updateUI(false);
        }
    }

    public void handleSignInResult(GoogleSignInResult result){
        Log.i("myerror","handleSignInResult : " + result.isSuccess());
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
            displayName = acct.getDisplayName().toString();
            email = acct.getEmail().toString();
            Photourl = acct.getPhotoUrl().toString();

            txt_name.setText(displayName);
            txt_mail.setText(email);
            Glide.with(this).load(Photourl).into(img_header);
            updateUI(true);
        }else{
            updateUI(false);
            Log.i("myerror","fail : " + result.getStatus());
        }
    }

    public void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
       // Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("myerror","onConnectionFailed : " + connectionResult);
    }

    public void showProgressDialog(){
        layout.setVisibility(View.VISIBLE);
        wheel.spin();
    }

    public void hideProgressDialog(){
        wheel.stopSpinning();
        layout.setVisibility(View.GONE);
    }

    public void updateUI(boolean signeIn){
        if(signeIn){

            signInButton.setVisibility(View.INVISIBLE);
        }else{
            signInButton.setVisibility(View.VISIBLE);
        }
    }

    int checSidoCode(){
        int position = 0;
        for(String temp : cidoName){
            if(sido.equalsIgnoreCase(temp)){
                return position;
            }else {
                position++;
            }
        }
        return -1;
    }

    public String backCode(int position){
        String[] cidocode = {"6110000","6260000","6270000","6280000","6290000","5690000","6300000","6310000","6410000","6420000","6430000","6440000","6450000","6460000","6470000","6480000","6500000"};
        String[] cidoName = {"서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","세종특별자치시","대전광역시","울산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치도"};

        String[] Seoulcode = {"6119999", "3220000", "3240000", "3080000", "3150000", "6119998", "3200000", "3040000", "3160000", "3170000", "3100000", "3090000", "3050000", "3190000", "3130000", "3120000", "3210000", "3030000", "3070000", "3230000", "3140000", "3180000", "3020000", "3110000", "3000000", "3010000", "3060000"};
        String[] SeoulName = {"가정보호","강남구","강동구","강북구","강서구","개별사업","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"};

        String[] Busancode = {"3360000", "3350000", "3400000", "3310000", "3270000", "3300000", "3290000", "3320000", "3390000", "3340000", "3260000", "3380000", "3370000", "3280000", "3250000", "3330000"};
        String[] BusanName = {"강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구"};

        String[] Deagucode = {"3440000", "3470000", "3480000", "3420000", "3450000", "3430000", "3460000", "3410000"};
        String[] DeaguName = {"남구", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구"};

        String[] IncheonCode = {"3570000, 3550000, 3510000, 3530000, 3500000, 3540000, 3560000, 3520000, 3580000, 3490000"};
        String[] IncheonName = {"강화군, 계양구, 남구, 남동구, 동구, 부평구, 서구, 연수구, 옹진군, 중구"};

        String[] GwangjuCode = {"3630000, 3610000, 3590000, 3620000, 3600000"};
        String[] GwangjuName = {"광산구, 남구, 동구, 북구, 서구"};

        String[] DeajunCode = {"3680000, 3640000, 3660000, 3670000, 3650000"};
        String[] DeajunName = {"대덕구, 동구, 서구, 유성구, 중구"};

        String[] UlsanCode = {"3700000, 3710000, 3720000, 3730000, 3690000"};
        String[] UlsanName = {"남구, 동구, 북구, 울주군, 중구"};

        String[] KyungkiCode = {"4160000, 3940000, 3970000, 3900000, 5540000, 3980000, 4020000, 4090000, 3990000, 3920000, 3860000, 3780000, 3740000, 4010000, 3930000, 4080000, 3830000, 5590000, 4170000, 5700000, 4140000, 4000000, 4050000, 5630000, 4030000, 3820000, 4070000, 4060000, 3910000, 5600000, 4040000, 5530000"};
        String[] KyungkiName = {"가평군, 고양시, 과천시, 광명시, 광주시, 구리시, 군포시, 김포시, 남양주시, 동두천시, 부천시, 성남시, 수원시, 시흥시, 안산시, 안성시, 안양시, 양주시, 양평군, 여주시, 연천군, 오산시, 용인시, 용인시 기흥구, 의왕시, 의정부시, 이천시, 파주시, 평택시, 포천시, 하남시, 화성시"};

        String[] KangwonCode = {"4200000, 4340000, 4210000, 4240000, 4230000, 4320000, 4350000, 4270000, 4190000, 4330000, 4290000, 4300000, 4180000, 4220000, 4280000, 4250000, 4310000, 4260000"};
        String[] KangwonName = {"강릉시, 고성군, 동해시, 삼척시, 속초시, 양구군, 양양군, 영월군, 원주시, 인제군, 정선군, 철원군, 춘천시, 태백시, 평창군, 홍천군, 화천군, 횡성군"};

        String[] ChungbukCode = {"4460000, 4480000, 4420000, 4440000, 4430000, 4470000, 4400000, 5570000, 4450000, 5710000, 4390000"};
        String[] ChungbukName = {"괴산군, 단양군, 보은군, 영동군, 옥천군, 음성군, 제천시, 증평군, 진천군, 청주시, 충주시"};

        String[] ChungnamCode = {"5580000, 4500000, 4550000, 4540000, 5680000, 4510000, 4570000, 4530000, 4580000, 4520000, 4560000, 4610000, 4490000, 4590000, 4620000, 4600000"};
        String[] ChungnamName = {"계룡시, 공주시, 금산군, 논산시, 당진시, 보령시, 부여군, 서산시, 서천군, 아산시, 연기군, 예산군, 천안시, 청양군, 태안군, 홍성군"};

        String[] JunbukCode = {"4780000, 4670000, 4710000, 4700000, 4740000, 4790000, 4770000, 4720000, 4680000, 4760000, 4750000, 4640000, 4690000, 4730000"};
        String[] JunbukName = {"고창군, 군산시, 김제시, 남원시, 무주군, 부안군, 순창군, 완주군, 익산시, 임실군, 장수군, 전주시, 정읍시, 진안군"};

        String[] JunnamCode = {"4920000, 4880000, 4860000, 4840000, 4870000, 4830000, 4850000, 4800000, 4950000, 4890000, 4820000, 5010000, 4810000, 4970000, 4940000, 4990000, 4980000, 4910000, 5000000, 4960000, 4930000, 4900000"};
        String[] JunnamName = {"강진군, 고흥군, 곡성군, 광양시, 구례군, 나주시, 담양군, 목포시, 무안군, 보성군, 순천시, 신안군, 여수시, 영광군, 영암군, 완도군, 장성군, 장흥군, 진도군, 함평군, 해남군, 화순군"};

        String[] KyungbukCode = {"5130000, 5050000, 5200000, 5080000, 5140000, 5060000, 5120000, 5240000, 5110000, 5210000, 5070000, 5180000, 5170000, 5090000, 5100000, 5230000, 5260000, 5250000, 5150000, 5190000, 5160000, 5220000, 5020000"};
        String[] KyungbukName = {"경산시, 경주시, 고령군, 구미시, 군위군, 김천시, 문경시, 봉화군, 상주시, 성주군, 안동시, 영덕군, 영양군, 영주시, 영천시, 예천군, 울릉군, 울진군, 의성군, 청도군, 청송군, 칠곡군, 포항시"};

        String[] KyungnamCode = {"5370000, 5470000, 5420000, 5350000, 5430000, 5360000, 5340000, 5450000, 5380000, 5390000, 5310000, 5410000, 5280000, 5670000, 5320000, 5330000, 5440000, 5400000, 5460000, 5480000"};
        String[] KyungnamName = {"거제시, 거창군, 고성군, 김해시, 남해군, 밀양시, 사천시, 산청군, 양산시, 의령군, 진주시, 창녕군, 창원 마산합포회원구, 창원 의창성산구, 창원 진해구, 통영시, 하동군, 함안군, 함양군, 합천군"};

        String[] JejuCode ={"6520000","6510000","6500000"};
        String[] JejuName = {"서귀포시","제주시","제주특별자치도"};

        return  cidocode[position];
    }

    String loadurl(){
        if(kind.equals("dog")){
            upkind = "417000";
        }else if(kind.equals("cat")){
            upkind = "422400";
        }
        String s_month = String.format("%02d",month);
        String s_day = String.format("%02d",day);
        String s_month2 = String.format("%02d",month2);
        String s_day2 = String.format("%02d",day2);

        int position = checSidoCode();
        String upr_cd = "";
        String org_cd = "";
        int pos = 0;
        switch (position){
            case 0: //서울특별시
                upr_cd = backCode(0);
                for(String temp : SeoulName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Seoulcode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 1: //부산광역시
                upr_cd = backCode(1);
                for(String temp : BusanName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Busancode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 2: //대구광역시
                upr_cd = backCode(2);
                for(String temp : DeaguName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Deagucode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 3: //인천광역시
                upr_cd = backCode(3);
                for(String temp : IncheonName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = IncheonCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 4: //광주광역시
                upr_cd = backCode(4);
                for(String temp : GwangjuName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = GwangjuCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 5: //세종특별자치시
                upr_cd = backCode(5);
                org_cd  = "";
                break;
            case 6: //대전광역시
                upr_cd = backCode(6);
                for(String temp : DeajunName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = DeajunCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
            case 7: //울산광역시
                upr_cd = backCode(7);
                for(String temp : UlsanName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = UlsanCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 8: //경기도
                upr_cd = backCode(8);
                for(String temp : KyungkiName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungkiCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 9: //강원도
                upr_cd = backCode(9);
                for(String temp : KangwonName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KangwonCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 11: //충청북도
                upr_cd = backCode(11);
                for(String temp : ChungbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = ChungbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 12: //충청남도
                upr_cd = backCode(12);
                for(String temp : ChungnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = ChungnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 13: //전라북도
                upr_cd = backCode(13);
                for(String temp : JunbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JunbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 14: //전라남도
                upr_cd = backCode(14);
                for(String temp : JunnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JunnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 15: //경상북도
                upr_cd = backCode(15);
                for(String temp : KyungbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 16: //경상남도
                upr_cd = backCode(16);
                for(String temp : KyungnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 17: //제주특별자치도
                upr_cd = backCode(17);
                for(String temp : JejuName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JejuCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
        }
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?" +
                "bgnde="+year+s_month+s_day+"&" +
                "endde="+year2+s_month2+s_day2+"&" +
                "upkind="+upkind+"&" +
                "upr_cd="+upr_cd+"&" +
                "org_cd="+org_cd+"&" +
                "pageNo=1&numOfRows=100&" +
                "ServiceKey="+APIKey;
        return url;
    }

    String loadurl2(){
        if(kind.equals("dog")){
            upkind = "417000";
        }else if(kind.equals("cat")){
            upkind = "422400";
        }
        String s_month = String.format("%02d",month);
        String s_day = String.format("%02d",day);
        String s_month2 = String.format("%02d",month2);
        String s_day2 = String.format("%02d",day2);

        int position = checSidoCode();
        String upr_cd = "";
        String org_cd = "";
        int pos = 0;
        switch (position){
            case 0: //서울특별시
                upr_cd = cidocode[position];
                for(String temp : SeoulName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Seoulcode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 1: //부산광역시
                upr_cd = cidocode[position];
                for(String temp : BusanName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Busancode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 2: //대구광역시
                upr_cd = cidocode[position];
                for(String temp : DeaguName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Deagucode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 3: //인천광역시
                upr_cd = cidocode[position];
                for(String temp : IncheonName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = IncheonCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 4: //광주광역시
                upr_cd = cidocode[position];
                for(String temp : GwangjuName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = GwangjuCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 5: //세종특별자치시
                upr_cd = cidocode[position];
                org_cd  = "";
                break;
            case 6: //대전광역시
                upr_cd = cidocode[position];
                for(String temp : DeajunName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = DeajunCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
            case 7: //울산광역시
                upr_cd = cidocode[position];
                for(String temp : UlsanName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = UlsanCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 8: //경기도
                upr_cd = cidocode[position];
                for(String temp : KyungkiName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungkiCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 9: //강원도
                upr_cd = cidocode[position];
                for(String temp : KangwonName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KangwonCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 11: //충청북도
                upr_cd = cidocode[position];
                for(String temp : ChungbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = ChungbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 12: //충청남도
                upr_cd = cidocode[position];
                for(String temp : ChungnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = ChungnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 13: //전라북도
                upr_cd = cidocode[position];
                for(String temp : JunbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JunbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 14: //전라남도
                upr_cd = cidocode[position];
                for(String temp : JunnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JunnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 15: //경상북도
                upr_cd = cidocode[position];
                for(String temp : KyungbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 16: //경상남도
                upr_cd = cidocode[position];
                for(String temp : KyungnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 17: //제주특별자치도
                upr_cd = cidocode[position];
                for(String temp : JejuName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JejuCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
        }
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?" +
                "bgnde="+year+s_month+s_day+"&" +
                "endde="+year2+s_month2+s_day2+"&" +
                "upkind="+upkind+"&" +
                "upr_cd="+upr_cd+"&" +
                "pageNo=1&numOfRows=50&" +
                "ServiceKey="+APIKey;
        return url;
    }

    String loadurl3(){
        if(kind.equals("dog")){
            upkind = "417000";
        }else if(kind.equals("cat")){
            upkind = "422400";
        }
        String s_month = String.format("%02d",month);
        String s_day = String.format("%02d",day);
        String s_month2 = String.format("%02d",month2);
        String s_day2 = String.format("%02d",day2);

        int position = checSidoCode();
        String upr_cd = "";
        String org_cd = "";
        int pos = 0;
        switch (position){
            case 0: //서울특별시
                upr_cd = cidocode[position];
                for(String temp : SeoulName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Seoulcode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 1: //부산광역시
                upr_cd = cidocode[position];
                for(String temp : BusanName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Busancode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 2: //대구광역시
                upr_cd = cidocode[position];
                for(String temp : DeaguName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = Deagucode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 3: //인천광역시
                upr_cd = cidocode[position];
                for(String temp : IncheonName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = IncheonCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 4: //광주광역시
                upr_cd = cidocode[position];
                for(String temp : GwangjuName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = GwangjuCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 5: //세종특별자치시
                upr_cd = cidocode[position];
                org_cd  = "";
                break;
            case 6: //대전광역시
                upr_cd = cidocode[position];
                for(String temp : DeajunName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = DeajunCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
            case 7: //울산광역시
                upr_cd = cidocode[position];
                for(String temp : UlsanName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = UlsanCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 8: //경기도
                upr_cd = cidocode[position];
                for(String temp : KyungkiName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungkiCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 9: //강원도
                upr_cd = cidocode[position];
                for(String temp : KangwonName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KangwonCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 11: //충청북도
                upr_cd = cidocode[position];
                for(String temp : ChungbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = ChungbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 12: //충청남도
                upr_cd = cidocode[position];
                for(String temp : ChungnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = ChungnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 13: //전라북도
                upr_cd = cidocode[position];
                for(String temp : JunbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JunbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 14: //전라남도
                upr_cd = cidocode[position];
                for(String temp : JunnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JunnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 15: //경상북도
                upr_cd = cidocode[position];
                for(String temp : KyungbukName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungbukCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 16: //경상남도
                upr_cd = cidocode[position];
                for(String temp : KyungnamName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = KyungnamCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
            case 17: //제주특별자치도
                upr_cd = cidocode[position];
                for(String temp : JejuName){
                    if(gungu.equalsIgnoreCase(temp)){
                        org_cd  = JejuCode[pos];
                        pos = 0;
                        break;
                    }else {
                        pos++;
                    }
                }
                break;
        }
        String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/shelter?"+
                "upr_cd="+ upr_cd +
                "&org_cd="+ org_cd +
                "&ServiceKey="+APIKey;
        return url;
    }


    synchronized public void onXml(String needUrl){
        try{
            //Log.i("myerror","in try");
            URL url = new URL(needUrl);
            //Log.i("myerror","after uri");
            InputStream is = url.openStream();
            //Log.i("myerror","after inputstream");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            //Log.i("myerror","after xmlpullparser");

            xpp.setInput(is, "utf-8");
            int eventType = xpp.next();
            a_Fragment1_Item item = null;
            String tagName = "";

            //Log.i("myerror","back while");
            while(eventType != XmlPullParser.END_DOCUMENT){
                // Log.i("myerror","in while");
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT :
                        //Log.i("myerror","in start document");
                        break;
                    case XmlPullParser.START_TAG :
                        // Log.i("myerror","in start tag");
                        tagName = xpp.getName();
                        if(tagName.equals("item")){
                            item = new a_Fragment1_Item();
                            items = new ArrayList<>();
                        }else if(tagName.equals("age")){
                            xpp.next();
                            if(item != null){
                                item.setAge(xpp.getText());
                            }
                        }else if(tagName.equals("careAddr")){
                            xpp.next();
                            if(item != null){
                                item.setCareAddr(xpp.getText());
                            }
                        }else if(tagName.equals("careNm")){
                            xpp.next();
                            if(item != null){
                                item.setCareNm(xpp.getText());
                            }
                        }else if(tagName.equals("colorCd")){
                            xpp.next();
                            if(item != null){
                                item.setColorCd(xpp.getText());
                            }
                        }else if(tagName.equals("desertionNo")){
                            xpp.next();
                            if(item != null){
                                item.setDesertionNo(xpp.getText());
                            }
                        }else if(tagName.equals("filename")){
                            xpp.next();
                            if(item != null){
                                item.setFilename(xpp.getText());
                            }
                        }else if(tagName.equals("happenDt")){
                            xpp.next();
                            if(item != null){
                                item.setHappenDt(xpp.getText());
                            }
                        }else if(tagName.equals("happenPlace")){
                            xpp.next();
                            if(item != null){
                                item.setHappenPlace(xpp.getText());
                            }
                        }else if(tagName.equals("kindCd")){
                            xpp.next();
                            if(item != null){
                                item.setKindCd(xpp.getText());
                            }
                        }else if(tagName.equals("neuterYn")){
                            xpp.next();
                            if(item != null){
                                item.setNeuterYn(xpp.getText());
                            }
                        }else if(tagName.equals("noticeEdt")){
                            xpp.next();
                            if(item != null){
                                item.setNoticeEdt(xpp.getText());
                            }
                        }else if(tagName.equals("noticeNo")){
                            xpp.next();
                            if(item != null){
                                item.setNoticeNo(xpp.getText());
                            }
                        }else if(tagName.equals("noticeSdt")){
                            xpp.next();
                            if(item != null){
                                item.setNoticeSdt(xpp.getText());
                            }
                        }else if(tagName.equals("orgNm")){
                            xpp.next();
                            if(item != null){
                                item.setOrgNm(xpp.getText());
                            }
                        }else if(tagName.equals("popfile")){
                            xpp.next();
                            if(item != null){
                                item.setPopfile(xpp.getText());
                            }
                        }else if(tagName.equals("processState")){
                            xpp.next();
                            if(item != null){
                                item.setProcessState(xpp.getText());
                            }
                        }else if(tagName.equals("sexCd")){
                            xpp.next();
                            if(item != null){
                                item.setSexCd(xpp.getText());
                            }
                        }else if(tagName.equals("specialMark")){
                            xpp.next();
                            if(item != null){
                                item.setSpecialMark(xpp.getText());
                            }
                        }else if(tagName.equals("weight")){
                            xpp.next();
                            if(item != null){
                                item.setWeight(xpp.getText());
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        // Log.i("myerror","in end tag");
                        tagName = xpp.getName();
                        if(tagName.equals("items")){
                            // Log.i("myerror","in end tag with body");
                            // Log.i("myerror","in end tag with body items3 : "+items3.size());
                            isFragment = true;
                            return;
                        }
                        if(tagName.equals("item")){
                            items.add(item);
                            items4.add(items);
                            item = null;
                            items = null;
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                }
                eventType = xpp.next();
            }
        }catch (Exception e){
            Log.i("myerror","in xml " + e.getMessage());
        }
    }

    synchronized public void onXml2(String needUrl){
        try{
            URL url = new URL(needUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(is, "utf-8");
            int eventType = xpp.next();
            a_Fragment1_Item item = null;
            String tagName = "";

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT :
                        break;
                    case XmlPullParser.START_TAG :
                        tagName = xpp.getName();
                        if(tagName.equals("item")){
                            item = new a_Fragment1_Item();
                            items2 = new ArrayList<>();
                        }else if(tagName.equals("age")){
                            xpp.next();
                            if(item != null){
                                item.setAge(xpp.getText());
                            }
                        }else if(tagName.equals("careAddr")){
                            xpp.next();
                            if(item != null){
                                item.setCareAddr(xpp.getText());
                            }
                        }else if(tagName.equals("careNm")){
                            xpp.next();
                            if(item != null){
                                item.setCareNm(xpp.getText());
                            }
                        }else if(tagName.equals("colorCd")){
                            xpp.next();
                            if(item != null){
                                item.setColorCd(xpp.getText());
                            }
                        }else if(tagName.equals("desertionNo")){
                            xpp.next();
                            if(item != null){
                                item.setDesertionNo(xpp.getText());
                            }
                        }else if(tagName.equals("filename")){
                            xpp.next();
                            if(item != null){
                                item.setFilename(xpp.getText());
                            }
                        }else if(tagName.equals("happenDt")){
                            xpp.next();
                            if(item != null){
                                item.setHappenDt(xpp.getText());
                            }
                        }else if(tagName.equals("happenPlace")){
                            xpp.next();
                            if(item != null){
                                item.setHappenPlace(xpp.getText());
                            }
                        }else if(tagName.equals("kindCd")){
                            xpp.next();
                            if(item != null){
                                item.setKindCd(xpp.getText());
                            }
                        }else if(tagName.equals("neuterYn")){
                            xpp.next();
                            if(item != null){
                                item.setNeuterYn(xpp.getText());
                            }
                        }else if(tagName.equals("noticeEdt")){
                            xpp.next();
                            if(item != null){
                                item.setNoticeEdt(xpp.getText());
                            }
                        }else if(tagName.equals("noticeNo")){
                            xpp.next();
                            if(item != null){
                                item.setNoticeNo(xpp.getText());
                            }
                        }else if(tagName.equals("noticeSdt")){
                            xpp.next();
                            if(item != null){
                                item.setNoticeSdt(xpp.getText());
                            }
                        }else if(tagName.equals("orgNm")){
                            xpp.next();
                            if(item != null){
                                item.setOrgNm(xpp.getText());
                            }
                        }else if(tagName.equals("popfile")){
                            xpp.next();
                            if(item != null){
                                item.setPopfile(xpp.getText());
                            }
                        }else if(tagName.equals("processState")){
                            xpp.next();
                            if(item != null){
                                item.setProcessState(xpp.getText());
                            }
                        }else if(tagName.equals("sexCd")){
                            xpp.next();
                            if(item != null){
                                item.setSexCd(xpp.getText());
                            }
                        }else if(tagName.equals("specialMark")){
                            xpp.next();
                            if(item != null){
                                item.setSpecialMark(xpp.getText());
                            }
                        }else if(tagName.equals("weight")){
                            xpp.next();
                            if(item != null){
                                item.setWeight(xpp.getText());
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = xpp.getName();
                        if(tagName.equals("items")){
                            //  Log.i("myerror","in end tag with body");
                            //  Log.i("myerror","in end tag with body items4 : "+items4.size());
                            isFragment2 = true;
                            return;
                        }
                        if(tagName.equals("item")){
                            items2.add(item);
                            items5.add(items2);
                            item = null;
                            items2 = null;
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

    synchronized public void onXml3(String needUrl){
        try{
            URL url = new URL(needUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(is, "utf-8");
            int eventType = xpp.next();
            a_Fragment3_Item item = null;
            String tagName = "";

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT :
                        break;
                    case XmlPullParser.START_TAG :
                        tagName = xpp.getName();
                        if(tagName.equals("item")) {
                            item = new a_Fragment3_Item();
                            items3 = new ArrayList<>();
                        }else if(tagName.equals("careNm")){
                            xpp.next();
                            if(item != null){
                                item.setCareNm(xpp.getText());
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = xpp.getName();
                        if(tagName.equals("items")){
                            //  Log.i("myerror","in end tag with body");
                            //  Log.i("myerror","in end tag with body items4 : "+items4.size());
                            isFragment3 = true;
                            return;
                        }
                        if(tagName.equals("item")){
                            items3.add(item);
                            items6.add(items3);
                            item = null;
                            items3 = null;
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

}

