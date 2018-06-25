package com.fltry.androidlibs.sdkmodule.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import butterknife.OnClick
import com.fltry.androidlibs.R
import com.fltry.androidlibs.ui.BaseActivity

class RetrofitActivity : BaseActivity() {
    lateinit var retrofitEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarTitle!!.text = "retrofit框架的使用"

        retrofitEt = findViewById(R.id.retrofit_et)
        retrofitEt.setText("http://10.10.10.107:8004/areas")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_retrofit
    }

    @OnClick(R.id.retrofit_et)
    fun onClick(v: View) {
        when (v.id) {
            R.id.retrofit_btn -> {
                Log.i("aaa", "adsfasdfsadf")
            }
        }
    }
}
