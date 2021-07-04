package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewBill extends AppCompatActivity {
    private Switch in_outCome;
    private EditText etComment;
    private TextView txtNum,txtSign;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnPoint,btnB,btnSave,btnBack,btnPlus,btnMinus,btnEqual;
    private SharedPreferences spBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);
        SysApplication.getInstance().addActivity(this);
        initViews();
        initListeners();

        spBill = getSharedPreferences("Bill",MODE_PRIVATE);
        final int amount = spBill.getInt("amount",0);
        final int income = spBill.getInt("income",0);
        final int outcome = spBill.getInt("outcome",0);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                if(!content.equals("")){
                    btnEqual.performClick();
                    while(content.substring(content.length() - 1).equals("+") || content.substring(content.length() - 1).equals("-") ||
                            content.substring(content.length() - 1).equals(".")){
                        content = content.substring(0, content.length() - 1);
                        txtNum.setText(content);
                    }
                    String num = content;
                    if (!num.contains("-")) {
                        try {
                            String type = txtSign.getText().toString();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String time = df.format(new Date());
                            String comment = etComment.getText().toString();
                            if (comment.equals(""))
                                comment = "无备注";
                            String wholeText = type + "<<>>" + num + "<<>>" + time + "<<>>" + comment + "@";
                            FileOutputStream fos = openFileOutput("bill.txt", MODE_APPEND);
                            fos.write(wholeText.getBytes());
                            fos.flush();
                            fos.close();
                            SharedPreferences.Editor editor = spBill.edit();
                            editor.putInt("amount", amount + 1);
                            if (type.equals("+"))
                                editor.putInt("income", income + 1);
                            else
                                editor.putInt("outcome", outcome + 1);
                            editor.commit();
                            Toast.makeText(NewBill.this, "保存成功！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(NewBill.this, IndexPage.class));
                        } catch (IOException e) {
                            Toast.makeText(NewBill.this, "保存失败！", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(NewBill.this, "金额不能为负值！", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(NewBill.this,"请输入周转金额！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        in_outCome = (Switch)findViewById(R.id.in_outCome);
        txtNum = (TextView) findViewById(R.id.txtNum);
        txtSign = (TextView) findViewById(R.id.txtSign);
        etComment = (EditText)findViewById(R.id.etComment);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btn0 = (Button)findViewById(R.id.btn0);
        btnB = (Button)findViewById(R.id.btnB);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnPoint = (Button)findViewById(R.id.btnPoint);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnBack = (Button)findViewById(R.id.btnBack);
    }
    private void initListeners(){
        in_outCome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    in_outCome.setText("财账类型：            收入");
                    txtSign.setText("+");
                }
                else{
                    in_outCome.setText("财账类型：            支出");
                    txtSign.setText("-");
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 1;
                txtNum.setText(content);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 2;
                txtNum.setText(content);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 3;
                txtNum.setText(content);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 4;
                txtNum.setText(content);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 5;
                txtNum.setText(content);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 6;
                txtNum.setText(content);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 7;
                txtNum.setText(content);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 8;
                txtNum.setText(content);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                content += 9;
                txtNum.setText(content);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                if(!content.equals("0"))
                    content += 0;
                txtNum.setText(content);
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                String last = content.substring(content.length()-1);
                String first = content.substring(0,1);
                int numOfPlus = content.split("[+]").length-1;
                int numOfMinus = content.split("[-]").length-1;
                if(first.equals("-"))
                    numOfMinus--;
                int counter = content.split("[.]").length-1;
                if((counter <= (numOfMinus + numOfPlus))&&!content.equals("")
                        &&!last.equals("+")
                        &&!last.equals("-")){
                    content += ".";
                    txtNum.setText(content);
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNum.getText().toString().equals((""))){
                    String content = txtNum.getText().toString();
                    content = content.substring(0, content.length() - 1);
                    txtNum.setText(content);
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                if(!content.equals("")) {
                    if (!content.contains("+")&&!content.contains("-")) {//没有运算符
                        content += "+";
                        txtNum.setText(content);
                    }
                    else if(content.contains("+")){//有加号
                        if (!content.substring(content.length()-1, content.length()).equals("+")){//加号不在算式尾部，先运算再加符号
                            String []nums = content.split("[+]");
                            double result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                            txtNum.setText(result + "+");
                        }
                        else{//加号在算式尾部，不做处理
                        }
                    }

                    else{//有减号

                        if (content.substring(content.length()-1, content.length()).equals("-")){//减号在末尾，换为加号
                            txtNum.setText(content.substring(0,content.length()-1)+"+");
                        }
                        else if(content.substring(0, 1).equals("-")){//减号在开头
                            txtNum.setText(content + "+");
                        }
                        else{//减号在中间
                            String []nums = content.split("[-]");
                            double result = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
                            txtNum.setText(result + "+");
                        }
                    }
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                if(!content.equals("")) {
                    if (!content.contains("+")&&!content.contains("-")) {//没有运算符
                        content += "-";
                        txtNum.setText(content);
                    }
                    else if(content.contains("+")){//有加号
                        if (!content.substring(content.length()-1, content.length()).equals("+")){//加号不在算式尾部，先运算再加符号
                            String []nums = content.split("[+]");
                            double result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                            txtNum.setText(result + "-");
                        }
                        else{//加号在算式尾部，换为减号
                            txtNum.setText(content.substring(0,content.length()-1)+"-");
                        }
                    }
                    else{//有减号
                        if (content.substring(content.length()-1, content.length()).equals("-")){//减号在末尾，不做处理
                        }
                        else{//不在末尾
                            if(content.substring(0, 1).equals("-")){//开头有减号
                                if(content.substring(1, content.length()).contains("-")){//中间也有
                                    String []nums = content.split("[-]");
                                    double result = Double.parseDouble(nums[1]) + Double.parseDouble(nums[2]);
                                    txtNum.setText("-" + result + "-");
                                }
                                else {//中间没有
                                    txtNum.setText(content + "-");
                                }
                            }
                            else{//开头没有减号
                                String []nums = content.split("[-]");
                                double result = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
                                txtNum.setText(result + "-");
                            }
                        }
                    }
                }
            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                if(!content.equals("")) {
                    if (!content.contains("+")&&!content.contains("-")) {//没有运算符
                    }
                    else if(content.contains("+")){//有加号
                        if (!content.substring(content.length() - 1, content.length()).equals("+")){//加号不在算式尾部，先运算再加符号
                            String []nums = content.split("[+]");
                            double result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                            txtNum.setText(result+"");
                        }
                        else{//加号在算式尾部，不做处理
                        }
                    }
                    else{//有减号
                        if (content.substring(content.length()-1, content.length()).equals("-")){//减号在末尾，不做处理
                        }
                        else{//不在末尾
                            if(content.substring(0, 1).equals("-")){//开头有减号
                                if(content.substring(1, content.length()).contains("-")){//中间也有
                                    String []nums = content.split("[-]");
                                    double result = Double.parseDouble(nums[1]) + Double.parseDouble(nums[2]);
                                    txtNum.setText("-" + result);
                                }
                                else {//中间没有,不做处理
                                }
                            }
                            else{//开头没有减号
                                String []nums = content.split("[-]");
                                double result = Double.parseDouble(nums[0]) - Double.parseDouble(nums[1]);
                                txtNum.setText(result + "");
                            }
                        }
                    }
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
