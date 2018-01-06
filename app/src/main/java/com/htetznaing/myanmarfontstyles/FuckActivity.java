package com.htetznaing.myanmarfontstyles;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;

public class FuckActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    Button install,change;
    String path = "/sdcard/Android/data/com.htetznaing.myanmarfontstyles/";
    String font;
    int image;
    LOL pff;
    Typeface mm;
    TextView textView;
    String fontName;
    AdRequest adRequest;
    AdView Banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuck);

        if (ContextCompat.checkSelfPermission(FuckActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        pff = new LOL();

        mm = Typeface.createFromAsset(getAssets(),"kason.ttf");
        textView = (TextView) findViewById(R.id.textView);
        textView.setTypeface(mm);

        font = getIntent().getStringExtra("name");
        image = getIntent().getIntExtra("image",R.drawable.icon);

        imageView = (ImageView) findViewById(R.id.iv);
        install = (Button) findViewById(R.id.install);
        change = (Button) findViewById(R.id.change);

        imageView.setImageResource(image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        install.setOnClickListener(this);
        change.setOnClickListener(this);

        adRequest = new AdRequest.Builder().build();
        Banner = (AdView) findViewById(R.id.adView);
        Banner.loadAd(adRequest);

        fontName = getFontName(font);
        textView.setText("Install ကိုႏွိပ္ၿပီး"+fontName+" APK ကို Install လုပ္ေပးပါ။\n" +
                "ၿပီးလွ်င္ Change Font ကိုႏွိပ္ၿပီး\n" +
                fontName+" ကိုေ႐ြးေပးလိုက္ပါ။\n" +
                "အဲ့ေဖာင့္နာမည္နဲ႔ ႏွစ္ခုရွိေနတာကိုေတြ႕ရပါလိမ့္မယ္။\n" +
                "အေပၚတစ္ခုကိုအရင္ေ႐ြးပါ မရရင္ေအာက္တစ္ခုကိုေ႐ြးေပးလိုက္ပါ။\n" +
                "တစ္ခုပဲေပၚေသးရင္ ဖုန္းကို Restart ခ်ၿပီး(ပိတ္ၿပီးျပန္ဖြင့္) မွျပန္လာေ႐ြးပါ။");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.install:
                Install();
                break;
            case R.id.change:
                Change();
                break;
        }
    }

    public void Install(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path+font)),"application/vnd.android.package-archive");
        startActivity(intent);
    }

    public String getFontName(String font){
        String lol = "Font";
        switch (font){
            case "tg.apk":lol = "Tagu(zFont)";break;
            case "ks.apk":lol = "Kason(zFont)";break;
            case "nayon.apk":lol = "Nayon(zFont)";break;
            case "ws.apk":lol = "Warso(zFont)";break;
            case "wg.apk":lol = "Wargaung(zFont)";break;
            case "ttl.apk":lol = "Tawthalin(zFont)";break;
            case "tdg.apk":lol = "Thadingyut(zFont)";break;
            case "tsm.apk":lol = "Tansaungmone(zFont)";break;
            case "nattaw.apk":lol = "Nattaw(zFont)";break;
            case "pyatho.apk":lol = "Pyatho(zFont)";break;
            case "tabodwe.apk":lol = "Tabodwe(zFont)";break;
            case "tabaung.apk":lol = "Tabaung(zFont)";break;
        }

        return lol;
    }


    public void Change(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.flipfont.FontListProgressActivity");
            intent.setComponent(componentName);
            startActivity(intent);
        }catch (Exception e){
            startActivityForResult(new Intent(Settings.ACTION_DISPLAY_SETTINGS),0);
        }
    }

    public void sendEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","app4mm@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Myanmar Font Styles[FlipFont]");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Enter your feedback here!.");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
