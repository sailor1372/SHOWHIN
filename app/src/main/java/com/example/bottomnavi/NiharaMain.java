package com.example.bottomnavi;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NiharaMain extends AppCompatActivity {

    String dbname = "showhin";
    String tablename = "p_list";
    String sql;
    SQLiteDatabase db;
    Cursor resultset;
    ArrayList<Nihara_listItems> listItems = new ArrayList<>();


    String str_img = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nihara_activity_main);

        db = openOrCreateDatabase(dbname,MODE_PRIVATE,null);
        ListView listView = (ListView) findViewById(R.id.listView);

        try{
            sql = "select IMG, DATE, TITLE, PRICE, BARCODE, WEBURL from " + tablename;
            resultset = db.rawQuery(sql,null);
            int count = resultset.getCount();

            for (int i = 0; i < count; i++){
                resultset.moveToNext();
                String str_img = resultset.getString(0);
                String str_date = resultset.getString(1);
                String str_title = resultset.getString(2);
                String str_price = resultset.getString(3);
                String str_barcode = resultset.getString(4);
                String str_webUrl = resultset.getString(5);

                Log.d("OK", str_img + "   " + str_date + "   " + str_title + "   " + str_price + "   " + str_barcode);
                // サイトロゴ画像
                Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.amazon_logo);
                // 共有ボタン画像
                Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.share);
                // お気に入りボタン画像
                Bitmap bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.heartred);

                Nihara_listItems item = new Nihara_listItems(str_img, bmp2, bmp3, bmp4, str_date, str_barcode, str_title, str_price, str_webUrl);
                listItems.add(item);

            }
            Nihara_adapter adapter = new Nihara_adapter(this, R.layout.nihara_list_items, listItems);

            //ListViewにadapterをセット
            listView.setAdapter(adapter);
        }catch (Exception e){

        }
        /*
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
        String imgUrl = intent.getStringExtra("imgUrl");
        String d_text = intent.getStringExtra("d_text");
        String barcode = intent.getStringExtra("barcode");
        */

        //　商品画像リスト

        // 商品名リスト

        // 商品価格リスト

        //

        // ListViewのインスタンスを生成


        // リストビューに表示する要素を設定


       // ImageView showhin = findViewById(R.id.showhinIV);

        /*
        for(int i = 0; i < 10; i++){
            // 商品画像

            //Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.home);
            //Picasso.get().load(imgUrl).into(showhin);
            // サイトロゴ画像
            Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.amazon_logo);
            // 共有ボタン画像
            Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.home);
            // お気に入りボタン画像
            Bitmap bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);


        }
        */


    }

}