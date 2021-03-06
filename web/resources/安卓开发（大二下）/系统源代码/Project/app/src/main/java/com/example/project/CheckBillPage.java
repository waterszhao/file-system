package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class CheckBillPage extends AppCompatActivity {
    private Switch in_outCome;
    private EditText etComment;
    private TextView txtNum, txtSign, txtTime;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnPoint, btnB, btnSave, btnBack, btnDelete, btnPlus, btnMinus, btnEqual;
    SharedPreferences spBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bill_page);
        SysApplication.getInstance().addActivity(this);
        initViews();
        initListeners();

        spBill = getSharedPreferences("Bill", MODE_PRIVATE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String originalNum = bundle.getString("num", "error");
        final String originalType = bundle.getString("type", "error");
        final String time = bundle.getString("time", "error");
        String originalComment = bundle.getString("comment", "error");
        final int amount = bundle.getInt("amount", -1);
        final String[] bills = bundle.getStringArray("bills");
        final int position = bundle.getInt("position", 0);

        final int income = spBill.getInt("income", 0);
        final int outcome = spBill.getInt("outcome", 0);

        txtTime.setText(time);
        txtSign.setText(originalType);
        if(originalType.equals("+"))
            in_outCome.setText("   ???????????????          ??????");
        else
            in_outCome.setText("   ???????????????          ??????");
        txtNum.setText(originalNum);
        etComment.setText(originalComment);
        in_outCome.setChecked((originalType.equals("+")));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                if (!content.equals("")) {
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
                            String comment = etComment.getText().toString();
                            if (comment.equals(""))
                                comment = "?????????";
                            String wholeText = type + "<<>>" + num + "<<>>" + time + "<<>>" + comment;

                            String allBills = "";
                            for (int i = 0; i < amount; i++) {
                                if (i != position)
                                    allBills += bills[i];
                                else
                                    allBills += wholeText;
                                allBills += "@";
                            }
                            FileOutputStream fos = openFileOutput("bill.txt", MODE_PRIVATE);
                            fos.write(allBills.getBytes());
                            fos.flush();
                            fos.close();

                            SharedPreferences.Editor editor = spBill.edit();

                            if (!type.equals(originalType)) {
                                if (type.equals("+")) {
                                    editor.putInt("income", income + 1);
                                    editor.putInt("outcome", outcome - 1);
                                } else {
                                    editor.putInt("income", income - 1);
                                    editor.putInt("outcome", outcome + 1);
                                }
                            }
                            editor.commit();
                            Toast.makeText(CheckBillPage.this, "???????????????", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CheckBillPage.this, IndexPage.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(CheckBillPage.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                    }

                }else
                    Toast.makeText(CheckBillPage.this,"????????????????????????",Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckBillPage.this);
                builder.setTitle("??????");
                builder.setMessage("??????????????????");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            String allBills = "";
                            for (int i = 0; i < amount; i++) {
                                if (i != position)
                                    allBills = allBills + bills[i] + "@";
                            }

                            FileOutputStream fos = openFileOutput("bill.txt", MODE_PRIVATE);
                            fos.write(allBills.getBytes());
                            fos.flush();
                            fos.close();

                            SharedPreferences.Editor editor = spBill.edit();
                            if (originalType.equals("+")) {
                                editor.putInt("income", income - 1);
                            } else {
                                editor.putInt("outcome", outcome - 1);
                            }
                            editor.putInt("amount", amount - 1);
                            editor.commit();
                            Toast.makeText(CheckBillPage.this, "???????????????", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CheckBillPage.this, IndexPage.class));
                        } catch (IOException e) {
                            Toast.makeText(CheckBillPage.this, "???????????????", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNeutralButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    private void initViews() {
        in_outCome = (Switch) findViewById(R.id.in_outCome);
        txtNum = (TextView) findViewById(R.id.txtNum);
        txtSign = (TextView) findViewById(R.id.txtSign);
        txtTime = (TextView) findViewById(R.id.time);
        etComment = (EditText) findViewById(R.id.etComment);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnB = (Button) findViewById(R.id.btnB);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnDelete = (Button) findViewById(R.id.btnDelete);
    }

    private void initListeners() {
        in_outCome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    in_outCome.setText("???????????????          ??????");
                    txtSign.setText("+");
                }
                else{
                    in_outCome.setText("???????????????          ??????");
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
                if (!content.equals("0"))
                    content += 0;
                txtNum.setText(content);
            }
        });
        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = txtNum.getText().toString();
                String last = content.substring(content.length() - 1);
                String first = content.substring(0, 1);
                int numOfPlus = content.split("[+]").length - 1;
                int numOfMinus = content.split("[-]").length - 1;
                if (first.equals("-"))
                    numOfMinus--;
                int counter = content.split("[.]").length - 1;
                if ((counter <= (numOfMinus + numOfPlus)) && !content.equals("")
                        && !last.equals("+")
                        && !last.equals("-")) {
                    content += ".";
                    txtNum.setText(content);
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNum.getText().toString().equals((""))) {
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
                if (!content.equals("")) {
                    if (!content.contains("+") && !content.contains("-")) {//???????????????
                        content += "+";
                        txtNum.setText(content);
                    } else if (content.contains("+")) {//?????????
                        if (!content.substring(content.length() - 1, content.length()).equals("+")) {//????????????????????????????????????????????????
                            String[] nums = content.split("[+]");
                            double result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                            txtNum.setText(result + "+");
                        } else {//????????????????????????????????????
                        }
                    } else {//?????????

                        if (content.substring(content.length() - 1, content.length()).equals("-")) {//??????????????????????????????
                            txtNum.setText(content.substring(0, content.length() - 1) + "+");
                        } else if (content.substring(0, 1).equals("-")) {//???????????????
                            txtNum.setText(content + "+");
                        } else {//???????????????
                            String[] nums = content.split("[-]");
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
                if (!content.equals("")) {
                    if (!content.contains("+") && !content.contains("-")) {//???????????????
                        content += "-";
                        txtNum.setText(content);
                    } else if (content.contains("+")) {//?????????
                        if (!content.substring(content.length() - 1, content.length()).equals("+")) {//????????????????????????????????????????????????
                            String[] nums = content.split("[+]");
                            double result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                            txtNum.setText(result + "-");
                        } else {//????????????????????????????????????
                            txtNum.setText(content.substring(0, content.length() - 1) + "-");
                        }
                    } else {//?????????
                        if (content.substring(content.length() - 1, content.length()).equals("-")) {//??????????????????????????????
                        } else {//????????????
                            if (content.substring(0, 1).equals("-")) {//???????????????
                                if (content.substring(1, content.length()).contains("-")) {//????????????
                                    String[] nums = content.split("[-]");
                                    double result = Double.parseDouble(nums[1]) + Double.parseDouble(nums[2]);
                                    txtNum.setText("-" + result + "-");
                                } else {//????????????
                                    txtNum.setText(content + "-");
                                }
                            } else {//??????????????????
                                String[] nums = content.split("[-]");
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
                if (!content.equals("")) {
                    if (!content.contains("+") && !content.contains("-")) {//???????????????
                    } else if (content.contains("+")) {//?????????
                        if (!content.substring(content.length() - 1, content.length()).equals("+")) {//????????????????????????????????????????????????
                            String[] nums = content.split("[+]");
                            double result = Double.parseDouble(nums[0]) + Double.parseDouble(nums[1]);
                            txtNum.setText(result + "");
                        } else {//????????????????????????????????????
                        }
                    } else {//?????????
                        if (content.substring(content.length() - 1, content.length()).equals("-")) {//??????????????????????????????
                        } else {//????????????
                            if (content.substring(0, 1).equals("-")) {//???????????????
                                if (content.substring(1, content.length()).contains("-")) {//????????????
                                    String[] nums = content.split("[-]");
                                    double result = Double.parseDouble(nums[1]) + Double.parseDouble(nums[2]);
                                    txtNum.setText("-" + result);
                                } else {//????????????,????????????
                                }
                            } else {//??????????????????
                                String[] nums = content.split("[-]");
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
