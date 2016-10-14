package com.example.notes_2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by my on 2016/10/11.
 */
public class Content extends Activity implements View.OnClickListener {
    private Button okButton,cancleButton;
    private EditText contentWrite;
    private DBHelper dbHelper;
    private Cursor cursor;
    private int _id=0;
    private Time time;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        SimpleDateFormat   formatter=new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        String string=formatter.format(curDate);
        Log.e("0",string);
        init();
    }
    private void init(){
        dbHelper=new DBHelper(this);
        cursor=dbHelper.select();
        okButton = (Button) findViewById(R.id.btn_ok);
        cancleButton = (Button) findViewById(R.id.btn_cancle);
        contentWrite = (EditText) findViewById(R.id.et_content);
        okButton.setOnClickListener(this);
        cancleButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                addData() ;
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.btn_cancle:
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
    private void addData(){
        if (contentWrite.getText().toString().equals("")){
            Toast.makeText(Content.this,"内容不能为空",Toast.LENGTH_SHORT).show();
        }else{

            dbHelper.insert(contentWrite.getText().toString());
            cursor.requery();
            contentWrite.setText("");
            _id=0;
        }
    }
}
