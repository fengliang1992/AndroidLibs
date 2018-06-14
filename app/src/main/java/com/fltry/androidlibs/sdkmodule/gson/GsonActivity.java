package com.fltry.androidlibs.sdkmodule.gson;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GsonActivity extends BaseActivity {

    String json = "{'id':1,'name':'小明','sex':'男'}";
    String json2 = "[{'id':1,'name':'小明','sex':'男'},{'id':1,'name':'小红','sex':'女'}]";
    Gson gson;
    @BindView(R.id.gson_tv1)
    TextView gsonTv1;
    @BindView(R.id.gson_tv2)
    TextView gsonTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("Gson的使用");

        gson = new Gson();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gson;
    }

    @OnClick({R.id.gson_btn1, R.id.gson_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gson_btn1:
                Student student = gson.fromJson(json, Student.class);
                gsonTv1.setText(student.toString());
                break;
            case R.id.gson_btn2:
                ArrayList<Student> students = gson.fromJson(json2, new TypeToken<ArrayList<Student>>() {
                }.getType());
                gsonTv2.setText(students.toString());
                break;
        }
    }
}
