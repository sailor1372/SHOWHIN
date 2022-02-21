package com.example.bottomnavi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;
    private static final String TAG = "ParseAdapter";


    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context){
        this.parseItems = parseItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView1.setText(parseItem.getTitle());
        holder.textView2.setText(parseItem.getPrice());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
        holder.logoView.setImageResource(R.drawable.amazon_logo);
        String imAdd = parseItem.getImgUrl();

        holder.itemView.setTag(position); //커스텀 리스트 뷰의 각각의 리스트를 의미
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = holder.textView1.getText().toString();
                String price = holder.textView2.getText().toString(); //holder로 가져온 값을 변수에 넣기
                String webUrl = parseItem.getWebUrl();

                Intent intent;
                intent = new Intent(context, Detail.class); //look_memo.class부분에 원하는 화면 연결
                intent.putExtra("img", imAdd);
                intent.putExtra("title", title);
                intent.putExtra("price", price);
                intent.putExtra("webUrl", webUrl);

                context.startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        ImageView logoView;
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.pricetext);
            logoView = itemView.findViewById(R.id.logoView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Log.d(TAG,"--------------------TOUCH----------------------"  +
                                ":   "   + pos);
                    }

                }
            });
        }
    }
}
