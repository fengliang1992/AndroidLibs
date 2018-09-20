package com.fltry.module.gson;

import android.view.View;

import com.fltry.module.gson.databinding.ActivityGsonBinding;
import com.fltry.module.lib_common.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class GsonActivity extends BaseActivity {

    String json = "{'id':1,'name':'小明','sex':'男'}";
    String json2 = "[{'id':1,'name':'小明','sex':'男'},{'id':1,'name':'小红','sex':'女'}]";
    Gson gson;
    ActivityGsonBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gson;
    }

    @Override
    protected String title() {
        return "Gson的使用";
    }

    @Override
    protected void initView() {
        mBinding = (ActivityGsonBinding) dataBinding;
        gson = new Gson();
    }

    public void gson1(View v) {
        Student student = gson.fromJson(json, Student.class);
        mBinding.gsonTv1.setText(student.toString());
    }

    public void gson2(View v) {
        ArrayList<Student> students = gson.fromJson(json2, new TypeToken<ArrayList<Student>>() {
        }.getType());
        mBinding.gsonTv2.setText(students.toString());
    }
}
