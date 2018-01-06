package com.htetznaing.myanmarfontstyles;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    String path = "/sdcard/Android/data/com.htetznaing.myanmarfontstyles/";
    LOL pff;
    AdView Banner;
    AdRequest adRequest;
    InterstitialAd iAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adRequest = new AdRequest.Builder().build();
        Banner = (AdView) findViewById(R.id.adView);
        Banner.loadAd(adRequest);

        iAd = new InterstitialAd(this);
        iAd.setAdUnitId("ca-app-pub-1325188641119577/9224527870");
        iAd.loadAd(adRequest);
        iAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadAD();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                loadAD();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                loadAD();
            }
        });

        pff = new LOL();

        b1 = (ImageView) findViewById(R.id.b1);
        b2 = (ImageView) findViewById(R.id.b2);
        b3 = (ImageView) findViewById(R.id.b3);
        b4 = (ImageView) findViewById(R.id.b4);
        b5 = (ImageView) findViewById(R.id.b5);
        b6 = (ImageView) findViewById(R.id.b6);
        b7 = (ImageView) findViewById(R.id.b7);
        b8 = (ImageView) findViewById(R.id.b8);
        b9 = (ImageView) findViewById(R.id.b9);
        b10 = (ImageView) findViewById(R.id.b10);
        b11 = (ImageView) findViewById(R.id.b11);
        b12 = (ImageView) findViewById(R.id.b12);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"၁၂ လရာသီ စာလုံးအလွမ်ားကိုသင့္ဖုန္းမွာ Root မလို အလြယ္တကူအသုံးျပဳႏိုင္မယ့္\n" +
                        "Myanmar Font Styles App ေလးပါ။\n" +
                        "ေဖာင့္အလွ (၁၂) မ်ိဳးပါဝင္ၿပီး Font Style ပါတဲ့ဖုန္းေတြမွာ Root မလိုပဲ အသုံးျပဳႏိုင္ပါသည္။\n" +
                        "Download Free at Google Play Store : https://play.google.com/store/apps/details?id=com.htetznaing.myanmarfontstyles");
                startActivity(new Intent(Intent.createChooser(intent,"Myanmar Font Styles App")));
            }
        });

        work();
    }

    public void work(){
        if (checkPermissions()==true) {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
                pff.Assets2SD(MainActivity.this, "icon.png",path, "htetz.zip");
                if (f.exists()) {
                    pff.ABUnzip(path+"htetz.zip",path);
                }
                f.delete();
            }
        }
    }

    private boolean checkPermissions() {
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        final List<String> listPermissionsNeeded = new ArrayList<>();
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Need to Allow Write Storage Permission!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this, listPermissionsNeeded.toArray
                            (new String[listPermissionsNeeded.size()]), 5217);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            Log.d("TAG","Permission"+"\n"+String.valueOf(false));
            return false;
        }
        Log.d("Permission","Permission"+"\n"+String.valueOf(true));
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 5217: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    checkPermissions();
                    Toast.makeText(this, "You need to Allow Write Storage Permission!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        showAD();
        switch (view.getId()){
            case R.id.b1:
                next("tg.apk",R.drawable.tagu);
                break;
            case R.id.b2:
                next("ks.apk",R.drawable.kason);
                break;
            case R.id.b3:
                next("nayon.apk",R.drawable.nayon);
                break;
            case R.id.b4:
                next("ws.apk",R.drawable.warso);
                break;
            case R.id.b5:
                next("wg.apk",R.drawable.wg);
                break;
            case R.id.b6:
                next("ttl.apk",R.drawable.ttl);
                break;
            case R.id.b7:
                next("tdg.apk",R.drawable.tdg);
                break;
            case R.id.b8:
                next("tsm.apk",R.drawable.tsm);
                break;
            case R.id.b9:
                next("nattaw.apk",R.drawable.nattaw);
                break;
            case R.id.b10:
                next("pyatho.apk",R.drawable.pyatho);
                break;
            case R.id.b11:
                next("tabodwe.apk",R.drawable.tabodwe);
                break;
            case R.id.b12:
                next("tabaung.apk",R.drawable.tb);
                break;
        }
    }

    public void next(String font,int image){
        File file = new File("/sdcard/Android/data/com.htetznaing.myanmarfontstyles/"+font);
        if (file.exists()) {
            Intent intent = new Intent(MainActivity.this, FuckActivity.class);
            intent.putExtra("name", font);
            intent.putExtra("image", image);
            startActivity(intent);
        }else{
            work();
            Toast.makeText(this, "Something was wrong! Please try again :(", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadAD(){
        if (!iAd.isLoaded()){
            iAd.loadAd(adRequest);
        }
    }

    public void showAD(){
        if (iAd.isLoaded()){
            iAd.show();
        }else{
            iAd.loadAd(adRequest);
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to Exit ?");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image,null);
        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showAD();
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              showAD();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
