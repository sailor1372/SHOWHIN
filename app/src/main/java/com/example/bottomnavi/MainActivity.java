package com.example.bottomnavi;

/*
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

*/
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static String barcode;
    ImageButton btn1, btn2, btn3, btn4, btn5;

    Button scanBtn;    //ホーム画面のスキャンボタンの定義

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (ImageButton) findViewById(R.id.btn_1);
        btn2 = (ImageButton) findViewById(R.id.btn_2);
        btn3 = (ImageButton) findViewById(R.id.btn_3);
        btn4 = (ImageButton) findViewById(R.id.btn_4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.frame, fragment1);
                transaction.commit();

                //    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                //    startActivityForResult(intent, 1001);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment2 fragment2 = new Fragment2();
                transaction.replace(R.id.frame, fragment2);
                transaction.commit();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.frame, fragment3);
                transaction.commit();

              //      Intent intent = new Intent(getApplicationContext(), ss.class);
              //      startActivityForResult(intent, 1001);//액티비티 띄우기
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment4 fragment4 = new Fragment4();
                transaction.replace(R.id.frame, fragment4);
                transaction.commit();
            }
        });
    }

    ///////////////////////////////////////////////
    @Override
    public void onClick(View v) {


    }

    public void scanCode() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(true);  //回転のロック
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("バーコードをスキャンしています");  //撮影時
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("スキャン完了");
                String contents = data.getStringExtra("SCAN_RESULT");              //01変数の格納
                String cc = result.getContents();
                barcode = contents;
                //  Toast.makeText(this,cc, Toast.LENGTH_SHORT).show();           //02 result.getContents()
                builder.setPositiveButton("もう一度", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                //アマゾンから検索
                builder.setNeutralButton("AMAZON 検索", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),contents + "を検索します", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                        intent.putExtra("barcode",contents);
                        startActivityForResult(intent,1001);//액티비티 띄우기

                    /*
                        //Webを起動
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.co.jp/s?k=" + contents + "&__mk_ja_JP=カタカナ&ref=nb_sb_noss"));
                        //楽天なら
                        //  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.rakuten.co.jp/search/mall/" + contents));
                        startActivity(intent);

                     */
                    } });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
            else {
                Toast.makeText(this,"No Results", Toast.LENGTH_SHORT).show();
            }

        }else {
            super.onActivityResult(requestCode,resultCode,data);

        }
    }
}