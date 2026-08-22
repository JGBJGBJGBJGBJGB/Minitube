package com.minitube.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        LinearLayout menu = new LinearLayout(this);
        menu.setOrientation(LinearLayout.HORIZONTAL);

        Button feed = new Button(this);
        feed.setText("Feed");

        Button shorts = new Button(this);
        shorts.setText("Shorts");

        Button canales = new Button(this);
        canales.setText("Canales");

        Button listas = new Button(this);
        listas.setText("Listas");

        menu.addView(feed);
        menu.addView(shorts);
        menu.addView(canales);
        menu.addView(listas);

        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);

        root.addView(menu);
        root.addView(web, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        setContentView(root);

        feed.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/"));
        shorts.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/shorts"));
        canales.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/feed/channels"));
        listas.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/feed/library"));

        web.loadUrl("https://www.youtube.com/");
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack();
        else super.onBackPressed();
    }
}
