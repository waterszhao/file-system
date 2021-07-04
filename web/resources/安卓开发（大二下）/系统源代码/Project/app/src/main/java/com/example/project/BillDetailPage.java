package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BillDetailPage extends AppCompatActivity {
    private Switch in_outcome;
    private Button btnNew, btnBack, btnTime;
    private TextView txtBillNum, txtNum;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail_page);
        SysApplication.getInstance().addActivity(this);
        initViews();
        initListener();

        Intent intent = getIntent();
        String choseTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String type= "-";
        Bundle bundle = intent.getExtras();

        if(bundle!=null) {
            choseTime = bundle.getString("choseTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            type= bundle.getString("type", "-");
        }

        String choseYearMonth = choseTime.substring(0,7);
        String choseDay = choseTime.substring(8,10);

        if(type.equals("-")){
            in_outcome.setText("财账类型：          支出");
            in_outcome.setChecked(false);
        }
        else{
            in_outcome.setText("财账类型：          收入");
            in_outcome.setChecked(true);
        }

        String[] bills = null;
        try {
            FileInputStream fis = openFileInput("bill.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            String allBills = new String(buffer);
            allBills = allBills.substring(0, allBills.length() - 1);
            bills = allBills.split("@");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int amount = 0;
        for (int i = 0; i < bills.length; i++) {
            String[] bill = bills[i].split("<<>>");
            if (bill[0].equals(type) && bill[2].substring(0, 7).equals(choseYearMonth)&&bill[2].substring(8,10).compareTo(choseDay)<=0)
                amount++;
        }

        final String[] types = new String[amount];
        final String[] nums = new String[amount];
        final String[] times = new String[amount];
        final String[] comments = new String[amount];

        Double totalNum = 0.0;
        for (int i = 0,j = 0; i+j < bills.length; ) {
            String[] bill = bills[i+j].split("<<>>");
            if (bill[0].equals(type) && bill[2].substring(0, 7).equals(choseYearMonth)&&bill[2].substring(8,10).compareTo(choseDay)<=0) {
                types[i] = bill[0];
                nums[i] = bill[1];
                times[i] = bill[2];
                comments[i] = bill[3];
                totalNum += Double.parseDouble(bill[1]);
                i++;
            }
            else
                j++;
        }

        List<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < amount; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("itemNum", types[i] + nums[i]);
            item.put("itemTime", times[i]);
            myList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(BillDetailPage.this, myList, R.layout.billlist, new String[]{"itemNum", "itemTime"}, new int[]{R.id.txtNum, R.id.txtTime,});
        listView.setAdapter(adapter);
        final String[] finalBills = bills;
        SharedPreferences spBill = getSharedPreferences("Bill", MODE_PRIVATE);
        final int Famount = spBill.getInt("amount",0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BillDetailPage.this, CheckBillPage.class);
                intent.putExtra("num", nums[position]);
                intent.putExtra("type", types[position]);
                intent.putExtra("time", times[position]);
                intent.putExtra("comment", comments[position]);
                intent.putExtra("amount", Famount);
                intent.putExtra("position", position);
                intent.putExtra("bills", finalBills);
                startActivity(intent);
            }
        });
        btnTime.setText(choseTime);
        txtBillNum.setText(choseYearMonth+"起到"+choseTime+"为止总共有"+amount+"笔记录");
        txtNum.setText("共 "+type+totalNum+" 元");

        final String finalChoseTime = choseTime;

        in_outcome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    in_outcome.setText("财账类型：          收入");
                }
                else{
                    in_outcome.setText("财账类型：          支出");
                }
                Intent intent = new Intent(BillDetailPage.this,BillDetailPage.class);
                if(txtNum.getText().toString().contains("-"))
                    intent.putExtra("type","+");
                else
                    intent.putExtra("type","-");
                intent.putExtra("choseTime", finalChoseTime);
                startActivity(intent);
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(BillDetailPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Intent intent = new Intent(BillDetailPage.this,BillDetailPage.class);
                        if(txtNum.getText().toString().contains("-"))
                            intent.putExtra("type","-");
                        else
                            intent.putExtra("type","+");
                        String month = ""+(monthOfYear+1);
                        if(monthOfYear+1<10)
                            month = "0"+(monthOfYear+1);
                        intent.putExtra("choseTime",year + "-" + month + "-" + dayOfMonth);
                        startActivity(intent);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.billList);
        in_outcome = (Switch) findViewById(R.id.in_outcome);
        btnNew = (Button) findViewById(R.id.btnNew);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnTime = (Button) findViewById(R.id.btnTime);
        txtBillNum = (TextView) findViewById(R.id.txtBillNum);
        txtNum = (TextView) findViewById(R.id.txtNum);
    }

    private void initListener() {

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillDetailPage.this, BillListPage.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillDetailPage.this, IndexPage.class));
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(BillDetailPage.this,BillListPage.class));
        super.onBackPressed();
    }
}
