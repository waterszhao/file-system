package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.valueOf;

public class NewPage extends AppCompatActivity {
    private Button btnSave,btnBack;
    private EditText etTitle,etContent,etSize;
    private SeekBar seekBar;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        SysApplication.getInstance().addActivity(this);

        initViews();
        initListeners();
        int contentSize = (int)etContent.getTextSize();
        seekBar.setProgress(contentSize);
        etSize.setText(valueOf((int)(0.4*seekBar.getProgress()+30)));
        sp = getSharedPreferences("Text", MODE_PRIVATE);
        final int number = sp.getInt("number",0);
        final int amount = sp.getInt("amount",0);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etContent.getText().toString().equals("")||!etTitle.getText().toString().equals("")){
                    try {
                        FileOutputStream fos = openFileOutput("file"+(number+1)+".txt", MODE_PRIVATE);
                        FileOutputStream fos1 = openFileOutput("number.txt",MODE_APPEND);
                        String num;
                        if(amount == 0)
                            num = (number+1)+"";
                        else
                            num = "@" + (number+1);
                        fos1.write(num.getBytes());
                        fos1.flush();
                        fos1.close();

                        String title;
                        if(!etTitle.getText().toString().equals(""))
                            title = etTitle.getText().toString();
                        else
                            title = "无标题";

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = df.format(new Date());
                        String textContent = etContent.getText().toString();

                        String wholeText = title + "<<>>" + time + "<<>>" + textContent;
                        fos.write(wholeText.getBytes());
                        fos.flush();
                        fos.close();
                        SharedPreferences.Editor editorText = sp.edit();

                        editorText.putInt("amount",amount+1);
                        editorText.putInt("number",number+1);
                        editorText.commit();
                        Toast.makeText(NewPage.this,"保存成功！",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NewPage.this,IndexPage.class));
                    }  catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else
                    Toast.makeText(NewPage.this,"内容和标题不能都为空！",Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        btnSave = (Button)findViewById(R.id.btnSave);
        btnBack = (Button)findViewById((R.id.btnBack));
        etTitle = (EditText)findViewById(R.id.edtxtTitle);
        etContent = (EditText)findViewById(R.id.edtxtContent);
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
    }
}
