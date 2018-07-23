package com.fltry.androidlibs;

import com.fltry.androidlibs.sdkmodule.retrofit.ILoginModel;
import com.fltry.androidlibs.sdkmodule.retrofit.ILoginModelImp;
import com.fltry.androidlibs.sdkmodule.retrofit.bas.OnHttpCallBack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        ILoginModel iLoginModel = new ILoginModelImp();
        iLoginModel.login("", "", "", "", new OnHttpCallBack<String>() {
            @Override
            public void success(String data) {
                assertEquals(1,data );
            }

            @Override
            public void failed(String error) {

            }
        });
    }
}