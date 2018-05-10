package com.icono.organicanimal;

import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class NoticeActivity extends AppCompatActivity {

    WebView webView;
    TextView tv;

    FloatingActionButton flab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorMint));
        }

        webView = findViewById(R.id.webview);
        tv = findViewById(R.id.tv);

        flab = findViewById(R.id.fab);
        flab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                switch (errorCode){
                    case ERROR_AUTHENTICATION:
                        Log.i("myerror","사용자 인증 실패");
                        Toast.makeText(NoticeActivity.this, "ERROR_AUTHENTICATION", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_BAD_URL:
                        Log.i("myerror","잘못된 URL");
                        Toast.makeText(NoticeActivity.this, "ERROR_BAD_URL", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_CONNECT:
                        Log.i("myerror","서버로 연결 실패");
                        Toast.makeText(NoticeActivity.this, "ERROR_CONNECT", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_FAILED_SSL_HANDSHAKE:
                        Log.i("myerror","SSL handshake 수행 실패");
                        Toast.makeText(NoticeActivity.this, "ERROR_FAILED_SSL_HANDSHAKE", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_FILE:
                        Log.i("myerror","일반 파일 오류");
                        Toast.makeText(NoticeActivity.this, "ERROR_FILE", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_FILE_NOT_FOUND:
                        Log.i("myerror","파일을 찾을 수 없습니다");
                        Toast.makeText(NoticeActivity.this, "ERROR_FILE_NOT_FOUND", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_HOST_LOOKUP:
                        Log.i("myerror","서버 또는 프록시 호스트 이름 조회 실패");
                        Toast.makeText(NoticeActivity.this, "ERROR_HOST_LOOKUP", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_IO:
                        Log.i("myerror","서버에서 읽거나 서버로 쓰기 실패");
                        Toast.makeText(NoticeActivity.this, "ERROR_IO", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_PROXY_AUTHENTICATION:
                        Log.i("myerror","프록시에서 사용자 인증 실패");
                        Toast.makeText(NoticeActivity.this, "ERROR_PROXY_AUTHENTICATION", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_REDIRECT_LOOP:
                        Log.i("myerror","너무 많은 리디렉션");
                        Toast.makeText(NoticeActivity.this, "ERROR_REDIRECT_LOOP", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_TIMEOUT:
                        Log.i("myerror","연결 시작 초과");
                        Toast.makeText(NoticeActivity.this, "ERROR_TIMEOUT", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_TOO_MANY_REQUESTS:
                        Log.i("myerror","페이지 로드중 너무 많은 요청 발생");
                        Toast.makeText(NoticeActivity.this, "ERROR_TOO_MANY_REQUESTS", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_UNKNOWN:
                        Log.i("myerror","일반 오류");
                        Toast.makeText(NoticeActivity.this, "ERROR_UNKNOWN", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_UNSUPPORTED_AUTH_SCHEME:
                        Log.i("myerror","지원되지 않는 이증 체계");
                        Toast.makeText(NoticeActivity.this, "ERROR_UNSUPPORTED_AUTH_SCHEME", Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR_UNSUPPORTED_SCHEME:
                        Log.i("myerror","URI가 지원되지 않는 방식");
                        Toast.makeText(NoticeActivity.this, "ERROR_UNSUPPORTED_SCHEME", Toast.LENGTH_SHORT).show();
                        break;
                }
                super.onReceivedError(view, errorCode, description, failingUrl);

                webView.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.confirm();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("알림")
                        .setMessage(message)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.cancel();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            }
        });

        webView.loadUrl("http://iconostic.dothome.co.kr/notice_app.php");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
