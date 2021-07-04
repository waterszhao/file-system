package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

public class IndexPage extends AppCompatActivity {
    private Button btnNew,btnCheck,btnExit,btnNewBill,btnCheckBill;
    private TextView txtV,txtIncome,txtOutcome,txtTotal;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);
        SysApplication.getInstance().addActivity(this);
        initViews();
        initListeners();

        sp = getSharedPreferences("Text", MODE_PRIVATE);
        int amountText = sp.getInt("amount",0);
        txtV.setText("总共有"+amountText+"条便签，点击查看->");

        int amountBill = getSharedPreferences("Bill",MODE_PRIVATE).getInt("amount",0);
        if(amountBill!=0) {
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

            Double totalIncome = 0.0, totalOutcome = 0.0;
            for (int i = 0; i < amountBill; i++) {
                String[] bill = bills[i].split("<<>>");
                if (bill[0].equals("+"))
                    totalIncome += Double.parseDouble(bill[1]);
                else
                    totalOutcome += Double.parseDouble(bill[1]);
            }
            String type = "";
            double total = 0.0;
            if (totalIncome >= totalOutcome) {
                total = totalIncome - totalOutcome;
                type = "收入";
            } else {
                total = totalOutcome - totalIncome;
                type = "支出";
            }
            txtIncome.setText("近期总共收入 "+totalIncome+" 元，");
            txtOutcome.setText("支出 "+totalOutcome+" 元，");
            txtTotal.setText("合计 "+type+total+" 元。");
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(IndexPage.this,MainActivity.class));
        super.onBackPressed();
    }
    private void initViews(){
        btnNew = (Button)findViewById(R.id.btnNew);
        btnCheckBill = (Button)findViewById(R.id.btnCheckBill);
        btnCheck = (Button)findViewById(R.id.btnCheck);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnNewBill = (Button)findViewById(R.id.btnNewBill);
        txtV = (TextView) findViewById(R.id.textView2);
        txtIncome = (TextView)findViewById(R.id.txtIncome);
        txtOutcome = (TextView)findViewById(R.id.txtOutcome);
        txtTotal = (TextView)findViewById(R.id.txtTotal);
    }
    private void initListeners(){
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexPage.this, CheckListPage.class);
                startActivity(intent);
            }
        });
        btnCheckBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexPage.this,BillListPage.class);
                startActivity(intent);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexPage.this,NewPage.class);
                startActivity(intent);
            }
        });
        btnNewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexPage.this,NewBill.class));
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IndexPage.this);
                builder.setTitle("信息");
                builder.setMessage("确定退出吗");
                builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SysApplication.getInstance().exit();
                    }
                });
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }
}
