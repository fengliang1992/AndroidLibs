package com.fltry.androidlibs.utils.filemanage

import android.os.Bundle
import android.widget.GridView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import com.fltry.androidlibs.R
import com.fltry.androidlibs.ui.BaseActivity
class FileManageActivity(override val layoutId: Int = R.layout.activity_file_manage) : BaseActivity() {

    @BindView(R.id.fm_gv1) lateinit var fmGv1: GridView
    @BindView(R.id.fm_rl2) lateinit var fmRl2: RelativeLayout
    @BindView(R.id.fm_tv3) lateinit var fmTv3: TextView
    @BindView(R.id.fm_tv4) lateinit var fmTv4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarTitle!!.setText("文件管理器")
    }



}
