package com.example.bottomnavi;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    private String htmlContentInStringFormat;
    private int count;
    private ArrayList<String> pname = new ArrayList();

    ////////////////////////////
    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItem = new ArrayList<>();
    private ProgressBar progressBar;


    ///////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2);


        //////////////////////////////////
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycleView);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapter(parseItem, this);
        recyclerView.setAdapter(adapter);




        Content content = new Content();
        content.execute();


    }

    private class Content extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(ListActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(ListActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
            TextView result = findViewById(R.id.result_text);
            result.setText(count + "件の検索結果");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        @Override
        protected Void doInBackground(Void... Voids) {
            Intent intent = getIntent();
            String code = intent.getStringExtra("barcode");
            //test
            //String code = "4902778261095";

            try{
                String url = "https://www.amazon.co.jp/s?k=" + code + "&s=price-asc-rank&__mk_ja_JP=カタカナ&qid=1624503335&ref=sr_st_price-asc-rank";

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("img.s-image");

                int size = data.size();
                count = size;
                for(int i = 0; i < size; i++){

                    //イメージの収集 -> img.s-imgの数量の通りArrayListを生成して追加
                    String imgUrl = data.select("img.s-image")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String title = data.select("img.s-image")
                            .eq(i)
                            .attr("alt");
                    String price = doc.select("span.a-price-whole")
                            .eq(i)
                            .text();
                    String webUrl = doc.select("span.rush-component")
                            .select("a")
                            .eq(i)
                            .attr("href");
                    parseItem.add(new ParseItem(imgUrl, title, price, webUrl));
                    Log.d("items","img: " + imgUrl + " . tilte: " + title + " webUrl: " + webUrl);

                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
