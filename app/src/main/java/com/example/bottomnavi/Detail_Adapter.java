package com.example.bottomnavi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Detail_Adapter extends RecyclerView.Adapter<Detail_Adapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;
    private static final String TAG = "ParseAdapter";


    public Detail_Adapter(ArrayList<ParseItem> parseItems, Context context){
        this.parseItems = parseItems;
        this.context = context;
    }
    @NonNull
    @Override
    public Detail_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Detail_Adapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView1.setText(parseItem.getTitle());
        holder.textView2.setText(parseItem.getPrice());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
        String imAdd = parseItem.getImgUrl();

        holder.itemView.setTag(position); //リストビューの各リストを意味
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = holder.textView1.getText().toString();
                String price = holder.textView2.getText().toString();

                Intent intent;
                intent = new Intent(context, Detail.class);
                intent.putExtra("img", imAdd);
                intent.putExtra("title", title);
                intent.putExtra("price", price);

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
        TextView textView1;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.pricetext);

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
