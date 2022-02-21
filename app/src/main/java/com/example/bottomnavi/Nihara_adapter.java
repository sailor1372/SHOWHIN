package com.example.bottomnavi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Nihara_adapter extends ArrayAdapter<Nihara_listItems> {

    private int mResource;
    private List<Nihara_listItems> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context  コンテキスト
     * @param resource リソースID
     * @param items    リストビューの要素
     */
    public Nihara_adapter(Context context, int resource, List<Nihara_listItems> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        Nihara_listItems item = mItems.get(position);

        // 商品画像を設定
        ImageView showhin = (ImageView) view.findViewById(R.id.showhinIV);
        Picasso.get().load(item.getImgUrl()).into(showhin);

        // ロゴ画像を設定
        ImageView logo = (ImageView) view.findViewById(R.id.logoIV);
        logo.setImageBitmap(item.getLogo());

        // 共有画像を設定
        ImageButton share = (ImageButton) view.findViewById(R.id.shareIB);
        share.setImageBitmap(item.getShare());

        // ハート画像を設定
        ImageButton favorite = (ImageButton) view.findViewById(R.id.favoriteIB);
        favorite.setImageBitmap(item.getFavorite());

        // 日付を設定
        TextView date = (TextView) view.findViewById(R.id.dateTV);
        date.setText(item.getDate());

        // JANコードを設定
        TextView jancode = (TextView) view.findViewById(R.id.jancodeTV);
        jancode.setText(item.getJancode());

        // 商品名を設定
        TextView name = (TextView) view.findViewById(R.id.nameTV);
        name.setText(item.getName());

        // 値段を設定
        TextView price = (TextView) view.findViewById(R.id.priceTV);
        price.setText(item.getPrice());

        // お気に入り
        favorite.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                if(i == 0) {
                    i = 1;
                    favorite.setImageResource(R.drawable.heartred);
                    Toast.makeText(getContext(),"（未具現)", Toast.LENGTH_SHORT).show();
                }else{
                    i = 0;
                    favorite.setImageResource(R.drawable.heartw);
                    Toast.makeText(getContext(),"削除しました（未具現)", Toast.LENGTH_SHORT).show();

                }
            }
        });


        // 共有機能
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "共有します。");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                view.getContext().startActivity(shareIntent);
            }
        });
        /*
        showhin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"pic", Toast.LENGTH_SHORT).show();
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"pname", Toast.LENGTH_SHORT).show();
            }
        });

         */



        return view;
    }

}
