package com.minitube.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Cargar tema guardado
        SharedPreferences prefs = getSharedPreferences("theme", MODE_PRIVATE);
        boolean dark = prefs.getBoolean("dark", false);

        if (dark) {
            setTheme(android.R.style.Theme_Black);
        } else {
            setTheme(android.R.style.Theme_Light);
        }

        super.onCreate(savedInstanceState);

        // Layout raíz
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        // Menú horizontal
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

        Button tema = new Button(this);
        tema.setText(dark ? "Claro" : "Oscuro");

        // Añadir botones al menú
        menu.addView(feed);
        menu.addView(shorts);
        menu.addView(canales);
        menu.addView(listas);
        menu.addView(tema);

        // WebView
        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);

        root.addView(menu);
        root.addView(web, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        setContentView(root);

        // Acciones
        feed.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/"));
        shorts.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/shorts"));
        canales.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/feed/channels"));
        listas.setOnClickListener(v -> web.loadUrl("https://www.youtube.com/feed/library"));

        // Botón cambiar tema
        tema.setOnClickListener(v -> {
            SharedPreferences.Editor e = prefs.edit();
            e.putBoolean("dark", !dark);
            e.apply();
            recreate(); // Reinicia la Activity con el nuevo tema
        });

        // Página inicial
        web.loadUrl("https://www.youtube.com/");
    }

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) web.goBack();
        else super.onBackPressed();
    }
}
