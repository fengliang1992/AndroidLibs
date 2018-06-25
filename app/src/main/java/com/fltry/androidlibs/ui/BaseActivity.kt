package com.fltry.androidlibs.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import butterknife.ButterKnife
import com.fltry.androidlibs.R

/**
 * Created by tol on 2018/3/21.
 * base类
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit  var mContext: Context
    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    var toolbarTitle: TextView? = null
        private set
    private var mToolbar: Toolbar? = null

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    val toolbar: Toolbar?
        get() = findViewById<View>(R.id.toolbar) as Toolbar

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected val isShowBacking: Boolean
        get() = true

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract val layoutId: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(layoutId)

        mToolbar = findViewById(R.id.toolbar)
        toolbarTitle = findViewById(R.id.toolbar_title)
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar)
        }
        if (toolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            toolbarTitle!!.text = title
            //设置默认的标题不显示
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
        initSdk();
    }

    private fun initSdk() {
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != toolbar && isShowBacking) {
            showBack()
        }
    }

    /**
     * 设置头部标题
     *
     * @param title
     */
    fun setToolBarTitle(title: CharSequence) {
        if (toolbarTitle != null) {
            toolbarTitle!!.text = title
        } else {
            toolbar!!.title = title
            setSupportActionBar(toolbar)
        }
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private fun showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        toolbar!!.setNavigationIcon(R.mipmap.fhj_hs)
        toolbar!!.setNavigationOnClickListener { onBackPressed() }
    }
}
