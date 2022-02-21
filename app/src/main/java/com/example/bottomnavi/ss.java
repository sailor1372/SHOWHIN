package com.example.bottomnavi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss);
        // ListView에 표시되는 목록 항목을 ArrayList에서 준비
        ArrayList data = new ArrayList <> ();
        data.add ( "국어");
        data.add ( "사회");
        data.add ( "산수");
        data.add ( "이과");
        data.add ( "생활");
        data.add ( "음악");
        data.add ( "미술");
        data.add ( "가정");
        data.add ( "체육");

        //리스트 항목과 ListView를 매핑 ArrayAdapter를 준비한다
        ArrayAdapter adapter = new ArrayAdapter <> (this, android.R.layout.simple_list_item_1, data);

        // ListView에 ArrayAdapter를 설정
        ListView listView = (ListView) findViewById (R.id.ss);
        listView.setAdapter (adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String datah = (String) parent.getItemAtPosition(position);
                Log.d("ddk", "click" + position + "   " + datah);
                Toast.makeText(ss.this,"good", Toast.LENGTH_SHORT).show();
            }
        });

    }


}