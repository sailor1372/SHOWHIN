package com.example.bottomnavi;

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

import com.squareup.picasso.Picasso;


public class Detail_2 extends AppCompatActivity {
	int f = 0;

	/////sql////////////////////////
	String dbname = "showhin";
	String tablename = "p_list";
	String sql;
	SQLiteDatabase db;
	//////////////////////////////

	Fragment2 f2 = new Fragment2();


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
		String d_text = intent.getStringExtra("date");
		String barcode = intent.getStringExtra("barcode");
		String webUrl = intent.getStringExtra("webUrl");


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
		fav.setImageResource(R.drawable.heartred);
		fav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					f++;
					if(f % 2 == 1){
						//ボタンを押されたときに色を変える
						fav.setImageResource(R.drawable.heartw);

						//sql = "delete from " + tablename +";";
						sql = "delete from " + tablename  +
						" where DATE = '" + d_text + "' AND TITLE = '" + title + "' AND PRICE = '" + price + "' AND BARCODE = '" + barcode + "';";
						db.execSQL(sql);
						//f2.listItems.remove("nlist");
						Log.d("sql",sql);
						Toast.makeText(getApplicationContext(),"削除しました。", Toast.LENGTH_SHORT).show();
						finish();
						Intent intent = new Intent(getApplicationContext(), MainActivity.class);
						startActivityForResult(intent, 1001);


					}else{
						//もういちど押されたとき
						fav.setImageResource(R.drawable.heartred);

						sql = "insert into " + tablename + "(IMG, DATE, TITLE, PRICE, BARCODE, WEBURL)" +
								" values('" + imgUrl + "','" + d_text + "','" + title + "','" + price + "','" + barcode + "','" + webUrl + "');";
						db.execSQL(sql);

						Toast.makeText(getApplicationContext(),"追加しました。", Toast.LENGTH_SHORT).show();

					}
					//お気に入り画面に追加
				}catch (Exception e){
					e.printStackTrace();
					Log.d("error", "------------------------  : " + e);

				}
				
			}
		});

	}
}