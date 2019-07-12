package com.fltry.module.glide;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GlideModelImpl implements GlideModel {

    @Override
    public void loadMovie(int count, final OnDataCallBack<Movice> callBack) {
        Observable<Movice> movies = RetrofitUtils.newInstance("https://douban.uieee.com")
                .create(ApiService.class)
                .getMovice(count);
        new MySubscribe<>(movies, callBack);
    }
}
