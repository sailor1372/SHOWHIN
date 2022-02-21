package com.example.bottomnavi;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.example.bottomnavi.MainActivity.barcode;

public class Detail extends AppCompatActivity {
	int f = 0;

	/////sql////////////////////////
	String dbname = "showhin";
	String tablename = "p_list";
	String sql;
	SQLiteDatabase db;
	//////////////////////////////



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		db = openOrCreateDatabase(dbname,MODE_PRIVATE,null);
		sql = "create table if not exists " + tablename + " (IMG varchar(40), DATE varchar(40), TITLE varchar(200), PRICE varchar(20), BARCODE varchar(20), WEBURL varchar(200));";
		db.execSQL(sql);

		////////////////////////////////////////////////////////////
		Intent intent = getIntent();

		String title = intent.getStringExtra("title");
		String price = intent.getStringExtra("price");
		String imgUrl = intent.getStringExtra("img");
		String webUrl = intent.getStringExtra("webUrl");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd\nE, HH:mm", Locale.ENGLISH);
		String d_text = sdf.format(Calendar.getInstance().getTime());



		///////////////////////////////////////////////////////////////

		//商品画像
		ImageView img = findViewById(R.id.imgView);

		//スキャンした日付
		TextView date = findViewById(R.id.txt5);
		date.setText("0000/00/00 00:00");

		//JANコード
		TextView jan = findViewById(R.id.jancodeView);
		jan.setText("janコード : ");

		//商品名
		TextView nameView = findViewById(R.id.nameView);
		nameView.setText("製品名 : ");

		//値段
		TextView priceView = findViewById(R.id.priceView);
		priceView.setText("¥");

		Button amazon = findViewById(R.id.amazon);

		//下のボタン
		ImageButton btn1 = findViewById(R.id.btn_1);
		ImageButton btn2 = findViewById(R.id.btn_2);
		ImageButton btn3 = findViewById(R.id.btn_3);
		ImageButton btn4 = findViewById(R.id.btn_4);

		//共有ボタン
		ImageButton share = findViewById(R.id.imageButton);


		///////////////////////////////////////////////////////////

		Picasso.get().load(imgUrl).into(img);
		nameView.setText("製品名 : " + title);
		date.setText(d_text);
		priceView.setText(price);
		jan.setText(barcode);


		///////////////////////////////////////////////////////////
		share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT,"This is my text to send");
				sendIntent.setType("text/plain");

				Intent shareIntent = Intent.createChooser(sendIntent, null);
				startActivity(shareIntent);
			}
		});
		amazon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://www.amazon.co.jp" + webUrl));
				Log.d("s", "https://www.amazon.co.jp" + webUrl);
				startActivity(i);
			}
		});


		//お気に入りボタン
		ImageButton fav = findViewById(R.id.imageButton2);
		fav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					f++;
					if(f % 2 == 1){
						//ボタンを押されたときに色を変える
						fav.setImageResource(R.drawable.heartred);


						sql = "insert into " + tablename + "(IMG, DATE, TITLE, PRICE, BARCODE, WEBURL)" +
								" values('" + imgUrl + "','" + d_text + "','" + title + "','" + price + "','" + barcode + "','" + webUrl + "');";
						db.execSQL(sql);

						//お気に入りリストに追加

						//Thread.sleep(7000);

						Intent intent = new Intent(getApplicationContext(),NiharaMain.class); //look_memo.class부분에 원하는 화면 연결
						/*
						intent.putExtra("title", title);
						intent.putExtra("price", price);
						intent.putExtra("imgUrl", imgUrl);
						intent.putExtra("d_text", d_text);
						intent.putExtra("barcode", barcode);
						*/
						startActivityForResult(intent,1001);//액티비티 띄우기

						Log.d("追加しました。", sql);
					}else{
						//もういちど押されたとき
						fav.setImageResource(R.drawable.heartw);
					}
					//お気に入り画面に追加
				}catch (Exception e){
					e.printStackTrace();
					Log.d("error", "------------------------  : " + e);

				}

				
			}
		});
		/*

		//↓↓ 下のボタン ↓↓//
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
				Fragment1 fragment1 = new Fragment1();
				transaction.replace(R.id.frame, fragment1);
				transaction.commit();

				Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
				startActivityForResult(intent,1001);//액티비티 띄우기
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

		*/
	}
}