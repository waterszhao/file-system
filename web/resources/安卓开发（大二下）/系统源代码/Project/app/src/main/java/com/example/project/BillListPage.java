package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillListPage extends AppCompatActivity {
    private Button btnNew,btnBack, btnDetail;
    private TextView txtTip,txtIn,txtOut,txtInNum,txtOutNum;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list_page);
        SysApplication.getInstance().addActivity(this);
        initViews();
        initListener();

        SharedPreferences spBill = getSharedPreferences("Bill", MODE_PRIVATE);
        final int amount = spBill.getInt("amount",0);
        final int income = spBill.getInt("income",0);
        final int outcome = spBill.getInt("outcome",0);

        if(amount != 0){
            String [] bills = null;
            try {
                FileInputStream fis = openFileInput("bill.txt");
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                String allBills = new String(buffer);
                allBills = allBills.substring(0,allBills.length()-1);
                bills = allBills.split("@");
            } catch (IOException e) {
                e.printStackTrace();
            }

            final String[] types = new String[amount];
            final String[] nums = new String[amount];
            final String[] times = new String[amount];
            final String[] comments = new String[amount];

            Double totalIncome = 0.0,totalOutcome = 0.0;
            for(int i = 0;i < amount;i++){
                String []bill = bills[i].split("<<>>");
                types[i] = bill[0];
                nums[i] = bill[1];
                times[i] = bill[2];
                comments[i] = bill[3];
                if(types[i].equals("-"))
                    totalOutcome += Double.parseDouble(bill[1]);
                else
                    totalIncome += Double.parseDouble(bill[1]);
            }

            List<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
            for (int i=0; i<amount; i++) {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put("itemNum", types[i]+nums[i]);
                item.put("itemTime", times[i]);
                myList.add(item);
            }

            SimpleAdapter adapter = new SimpleAdapter(BillListPage.this, myList, R.layout.billlist, new String[] {"itemNum", "itemTime"}, new int[] {R.id.txtNum, R.id.txtTime,});
            listView.setAdapter(adapter);
            final String[] finalBills = bills;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(BillListPage.this,CheckBillPage.class);
                    intent.putExtra("num", nums[position]);
                    intent.putExtra("type",types[position]);
                    intent.putExtra("time",times[position]);
                    intent.putExtra("comment",comments[position]);
                    intent.putExtra("amount",amount);
                    intent.putExtra("position",position);
                    intent.putExtra("bills", finalBills);
                    startActivity(intent);
                }
            });

            txtTip.setText("总共有"+amount+"笔收支记录，其中：");
            txtIn.setText("收入 "+income+" 笔");
            txtOut.setText("支出 "+outcome+" 笔");
            txtInNum.setText("共 +"+totalIncome+" 元");
            txtOutNum.setText("共 -"+totalOutcome+" 元");
        }
        else
            txtTip.setText("暂无收支记录");
    }
    void initViews(){
        listView = (ListView)findViewById(R.id.billList);
        btnNew = (Button)findViewById(R.id.btnNew);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnDetail = (Button)findViewById(R.id.btnDetail);
        txtTip = (TextView) findViewById(R.id.txtTip);
        txtIn = (TextView) findViewById(R.id.txtIn);
        txtOut = (TextView) findViewById(R.id.txtOut);
        txtOutNum = (TextView) findViewById(R.id.txtOutNum);
        txtInNum = (TextView) findViewById(R.id.txtInNum);
    }
    void initListener(){

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillListPage.this,NewBill.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillListPage.this,IndexPage.class));
            }
        });
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillListPage.this,BillDetailPage.class));
            }
        });
    }
    public void onBackPressed() {
        startActivity(new Intent(BillListPage.this,IndexPage.class));
        super.onBackPressed();
    }
}
