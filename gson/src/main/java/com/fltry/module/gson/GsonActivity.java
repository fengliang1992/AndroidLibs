package com.fltry.module.gson;

import android.content.Intent;
import android.view.View;

import com.fltry.module.gson.databinding.ActivityGsonBinding;
import com.fltry.module.lib_common.AlertDialogUtils;
import com.fltry.module.lib_common.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class GsonActivity extends BaseActivity<ActivityGsonBinding> {

    String json = "{'id':1,'name':'小明','sex':'男'}";
    String json2 = "[{'id':1,'name':'小明','sex':'男'},{'id':1,'name':'小红','sex':'女'}]";
    Gson gson;

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
        String str = this.getIntent().getStringExtra("flag");//本段代码用于测试app之间的跳转
        if (null != str && !str.equals(""))
            AlertDialogUtils.getMyAlert(mContext, "接收数据", str).show();
        gson = new Gson();
    }

    public void gson1(View v) {
        Student student = gson.fromJson(json, Student.class);
        dataBinding.gsonTv1.setText(student.toString());
    }

    public void gson2(View v) {
        ArrayList<Student> students = gson.fromJson(json2, new TypeToken<ArrayList<Student>>() {
        }.getType());
        dataBinding.gsonTv2.setText(students.toString());
    }
}
