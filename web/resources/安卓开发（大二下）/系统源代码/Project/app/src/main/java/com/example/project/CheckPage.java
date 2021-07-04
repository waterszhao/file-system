package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.String.valueOf;

public class CheckPage extends AppCompatActivity {
    private Button btnSave, btnDelete, btnBack;
    private EditText etTitle, etContent,etSize;
    private TextView time;
    private SeekBar seekBar;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_page);
        SysApplication.getInstance().addActivity(this);

        initViews();
        initListeners();

        final Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        final int number = bundle.getInt("number", 0);
        int contentSize = (int)etContent.getTextSize();
        seekBar.setProgress(contentSize);
        etSize.setText(valueOf((int)(0.4*seekBar.getProgress()+30)));


        sp = getSharedPreferences("Text", MODE_PRIVATE);

        time.setText("编辑于："+bundle.getString("time"));
        etTitle.setText(bundle.getString("title", "error"));
        etContent.setText(bundle.getString("content", "error"));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput("file" + number + ".txt", MODE_PRIVATE);
                    String title;
                    if (!etTitle.getText().toString().equals(""))
                        title = etTitle.getText().toString();
                    else
                        title = "无标题";

                    String textContent = etContent.getText().toString();
                    fos.write((title + "<<>>" + bundle.getString("time") + "<<>>" + textContent).getBytes());
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(CheckPage.this, "保存成功！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CheckPage.this, CheckListPage.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CheckPage.this);
                builder.setTitle("信息");
                builder.setMessage("确定要删除吗");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            int[] numbers = bundle.getIntArray("numbers");
                            int amount = bundle.getInt("amount", 0);
                            String content = "";
                            for (int i = 0; i < amount; i++) {
                                if (numbers[i] == bundle.getInt("number")) {
                                    continue;
                                }
                                content += numbers[i];
                                content += "@";
                            }
                            char[] ar = content.toCharArray();
                            String newContent = "";
                            for (int i = 0; i < ar.length - 1; i++)
                                newContent += ar[i];

                            deleteFile("file" + number + ".txt");
                            deleteFile("number.txt");
                            FileOutputStream fos = openFileOutput("number.txt", MODE_APPEND);
                            fos.write(newContent.getBytes());
                            fos.flush();
                            fos.close();
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putInt("amount", amount - 1);
                            ed.commit();
                            Toast.makeText(CheckPage.this, "删除成功！", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CheckPage.this, CheckListPage.class));
                        } catch (Exception e) {
                            Toast.makeText(CheckPage.this, "删除失败！", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
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

    private void initViews() {
        time = (TextView) findViewById(R.id.time);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = (Button) findViewById((R.id.btnBack));
        btnDelete = (Button) findViewById(R.id.btnDelete);
        etTitle = (EditText) findViewById(R.id.edtxtTitle);
        etContent = (EditText) findViewById(R.id.edtxtContent);
        etSize = (EditText)findViewById(R.id.etSize);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
    }

    private void initListeners() {
        etSize.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() != KeyEvent.ACTION_UP) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && !etSize.getText().toString().equals("")) {
                        int size = Integer.parseInt(etSize.getText().toString());
                        if (size <= 70 && size >= 30)
                            seekBar.setProgress((int) (2.5 * size - 75));
                        else if (size < 30)
                            seekBar.setProgress(0);
                        else
                            seekBar.setProgress(100);
                    } else if (keyCode == KeyEvent.KEYCODE_0) {
                        String content = etSize.getText().toString();
                        content += 0;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_1) {
                        String content = etSize.getText().toString();
                        content += 1;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_2) {
                        String content = etSize.getText().toString();
                        content += 2;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_3) {
                        String content = etSize.getText().toString();
                        content += 3;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_4) {
                        String content = etSize.getText().toString();
                        content += 4;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_5) {
                        String content = etSize.getText().toString();
                        content += 5;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_6) {
                        String content = etSize.getText().toString();
                        content += 6;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_7) {
                        String content = etSize.getText().toString();
                        content += 7;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_8) {
                        String content = etSize.getText().toString();
                        content += 8;
                        etSize.setText(content);
                    } else if (keyCode == KeyEvent.KEYCODE_9) {
                        String content = etSize.getText().toString();
                        content += 9;
                        etSize.setText(content);
                    } else if (etSize.getText().toString().equals(""))
                        etSize.setText(valueOf((int)etContent.getTextSize()));
                    else if (keyCode == KeyEvent.KEYCODE_DEL) {
                        String content = etSize.getText().toString();
                        content = content.substring(0, content.length() - 1);
                        etSize.setText(content);
                    }
                }
                return true;
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int p = 0;
                double from = 30.0;
                double to = 70.0;
                if (progress == 0) {
                    p = (int)from;
                } else if (progress == seekBar.getMax()) {
                    p = (int)to;
                } else {
                    p = (int)(0.4*progress+30);
                }
                etContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,p);
                etSize.setText(valueOf(p));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
