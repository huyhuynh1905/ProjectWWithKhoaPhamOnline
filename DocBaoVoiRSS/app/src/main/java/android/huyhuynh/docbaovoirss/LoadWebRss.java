package android.huyhuynh.docbaovoirss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoadWebRss extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_web_rss);
        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkweb");

        webView.loadUrl(link);
        //Kich vào đường dẫn trong webview không bị văng ra trình duyệt:
        webView.setWebViewClient(new WebViewClient());
    }
}
