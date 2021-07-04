package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckListPage extends AppCompatActivity {
    private Button btnNew, btnBack;
    private TextView txtV;
    private ListView listView;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list_page);
        SysApplication.getInstance().addActivity(this);
        initViews();
        initListeners();

        sp = getSharedPreferences("Text", MODE_PRIVATE);
        final int amount = sp.getInt("amount", 0);
        if (amount != 0) {
            String[] n = null;
            try {
                FileInputStream fis = openFileInput("number.txt");
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                n = new String(buffer).split("@");
            } catch (IOException e) {
                e.printStackTrace();
            }

            final int[] numbers = new int[amount];
            for (int i = 0; i < amount; i++) {
                numbers[i] = Integer.parseInt(n[i]);
            }

            final String[] titles = new String[amount];
            final String[] times = new String[amount];
            final String[] contents = new String[amount];

            for (int i = 0; i < amount; i++) {
                try {
                    FileInputStream fis = openFileInput("file" + numbers[i] + ".txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    String[] content = new String(buffer).split("<<>>");
                    titles[i] = content[0];
                    times[i] = content[1];
                    contents[i] = content[2];
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            List<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < amount; i++) {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put("itemTitle", titles[i]);
                item.put("itemTime", times[i]);
                myList.add(item);
            }

            SimpleAdapter adapter = new SimpleAdapter(CheckListPage.this, myList, R.layout.titlelist, new String[]{"itemTitle", "itemTime"}, new int[]{R.id.title, R.id.time,});
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(CheckListPage.this, CheckPage.class);
                    intent.putExtra("number", numbers[position]);
                    intent.putExtra("title", titles[position]);
                    intent.putExtra("time", times[position]);
                    intent.putExtra("content", contents[position]);
                    intent.putExtra("numbers", numbers);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                }
            });
            txtV.setText("您目前总共有" + amount + "条便签");
        }
        else
            txtV.setText("暂无便签");

    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.textList);
        btnNew = (Button) findViewById(R.id.btnNew);
        btnBack = (Button) findViewById(R.id.btnBack);
        txtV = (TextView) findViewById(R.id.txtTip);
    }

    private void initListeners() {
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckListPage.this, NewPage.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckListPage.this, IndexPage.class));
            }
        });
    }
}
