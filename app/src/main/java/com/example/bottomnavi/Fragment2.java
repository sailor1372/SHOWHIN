package com.example.bottomnavi;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {

    // リストビューに表示する要素を設定
    ArrayList<Nihara_listItems> listItems = new ArrayList<>();

    String dbname = "showhin";
    String tablename = "p_list";
    String sql;
    SQLiteDatabase db;
    Cursor resultset;


    public Fragment2(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nihara_activity_main,container,false);


        // ListViewのインスタンスを生成
        ListView listView = (ListView) view.findViewById(R.id.listView);
        db = getActivity().openOrCreateDatabase(dbname,MODE_PRIVATE,null);
        listItems.clear();


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
            Nihara_adapter adapter = new Nihara_adapter(getContext(), R.layout.nihara_list_items, listItems);

            adapter.notifyDataSetChanged();
            //ListViewにadapterをセット
            listView.setAdapter(adapter);

            /*
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            */

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String data = (String) parent.getItemAtPosition(position);
                    //Log.d("ddk", "click" + position + "   " + data);
                    Nihara_listItems nlist = (Nihara_listItems) parent.getItemAtPosition(position);

                    String img = nlist.getImgUrl();
                    String title = nlist.getName();
                    String date = nlist.getDate();
                    String price = nlist.getPrice();
                    String code = nlist.getJancode();
                    String webUrl = nlist.getWebUrl();

                    Intent intent = new Intent(getContext(), Detail_2.class); //look_memo.class부분에 원하는 화면 연결
                    intent.putExtra("title", title);
                    intent.putExtra("price", price);
                    intent.putExtra("img", img);
                    intent.putExtra("date", date);
                    intent.putExtra("barcode", code);
                    intent.putExtra("webUrl", webUrl);

                    getContext().startActivity(intent);
                    //listItems.add(nlist);
                    //adapter.notifyDataSetChanged();
                    //listView.setAdapter(adapter);

                    //Toast.makeText(getContext(),"position :  " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getContext(),"error : " + e, Toast.LENGTH_SHORT).show();
        }
        return view;
    }

}
