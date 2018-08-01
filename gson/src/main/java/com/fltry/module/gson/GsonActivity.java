package com.fltry.module.gson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fltry.module.lib_common.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class GsonActivity extends BaseActivity implements View.OnClickListener {

    String json = "{'id':1,'name':'小明','sex':'男'}";
    String json2 = "[{'id':1,'name':'小明','sex':'男'},{'id':1,'name':'小红','sex':'女'}]";
    Gson gson;
    private TextView mGsonTv1;
    /**
     * 解析普通对象
     */
    private Button mGsonBtn1;
    private TextView mGsonTv2;
    /**
     * 解析数组
     */
    private Button mGsonBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("Gson的使用");

        gson = new Gson();
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gson;
    }

    private void initView() {
        mGsonTv1 = (TextView) findViewById(R.id.gson_tv1);
        mGsonBtn1 = (Button) findViewById(R.id.gson_btn1);
        mGsonBtn1.setOnClickListener(this);
        mGsonTv2 = (TextView) findViewById(R.id.gson_tv2);
        mGsonBtn2 = (Button) findViewById(R.id.gson_btn2);
        mGsonBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.gson_btn1) {
            Student student = gson.fromJson(json, Student.class);
            mGsonTv1.setText(student.toString());
        } else if (v.getId() == R.id.gson_btn2) {
            ArrayList<Student> students = gson.fromJson(json2, new TypeToken<ArrayList<Student>>() {
            }.getType());
            mGsonTv2.setText(students.toString());
        }
    }
}
