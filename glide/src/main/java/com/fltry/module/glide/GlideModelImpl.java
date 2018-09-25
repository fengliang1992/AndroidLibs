package com.fltry.module.glide;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GlideModelImpl implements GlideModel {

    @Override
    public void loadMovie(int count, final OnDataCallBack<Movice> callBack) {
        RetrofitUtils.newInstance("https://api.douban.com")
                .create(ApiService.class)
                .getMovice(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movice>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movice movice) {
                        callBack.succeed(movice);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
