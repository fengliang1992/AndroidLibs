package com.fltry.module.rretrofit;

import com.fltry.module.rretrofit.base.APIService;
import com.fltry.module.rretrofit.base.GlobalThrowable;
import com.fltry.module.rretrofit.base.OnHttpCallBack;
import com.fltry.module.rretrofit.base.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ILoginModelImp implements ILoginModel {

    @Override
    public void login(String userName, String password, String sign, String code, final OnHttpCallBack<String> callBack) {
        RetrofitUtils.newInstance(RetrofitActivity.BASE_URL)
                .create(APIService.class)
                .getLogin(userName, password, sign, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        callBack.success("登录成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed(GlobalThrowable.globalThrowable(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
