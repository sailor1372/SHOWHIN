package com.example.bottomnavi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {


    Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        scanCode();
    }

    private void scanCode() {


        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(true);  //回転のロック
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("バーコードをスキャンしています");  //撮影時
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                String contents = data.getStringExtra("SCAN_RESULT");              //01変数の格納
                String cc = result.getContents();                                        //02変数の格納
                //  Toast.makeText(this,cc, Toast.LENGTH_SHORT).show();           //02 result.getContents()
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                //アマゾンから検索
                builder.setNeutralButton("AMAZON 検索", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), contents + "を検索します", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        intent.putExtra("barcode", contents);
                        startActivityForResult(intent, 1001);//액티비티 띄우기

                    /*
                        //Webを起動
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.co.jp/s?k=" + contents + "&__mk_ja_JP=カタカナ&ref=nb_sb_noss"));
                        //楽天なら
                        //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.rakuten.co.jp/search/mall/" + contents));
                        startActivity(intent);

                     */
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Toast.makeText(this, "No Results", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}