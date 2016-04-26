package com.example.tacademy.samplewebview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.edit_url);
        webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {     //url로 페이지 접속전에 이 페이지로 접속할꺼니? 라고 알려주는거
                 if(url.startsWith("market://"))                                    //웹뷰가 처리 못하는거면 내가 할게!
                 {
                     Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                     startActivity(intent);
                     return  true;              //자신이 했으니까 리턴 트루
                 }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
      //  webView.addJavascriptInterface(new Myobject(),"myobject");
        webView.loadUrl("http://www.google.com");
        Button btn=(Button)findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=editText.getText().toString();
                if(!TextUtils.isEmpty(url))
                {
                    webView.loadUrl(url);
                }

            }
        });
        btn=(Button)findViewById(R.id.btn_prev);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoBack())
                {
                    webView.goBack();
                }
            }
        });

        btn=(Button)findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward())
                {
                    webView.goForward();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.resumeTimers();

    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.pauseTimers();
    }

    class Myobject{
        int count;
        public int getCount(){
            return count;
        }
        public void setCount(int count)
        {
            this.count=count;
        }
    }
}
