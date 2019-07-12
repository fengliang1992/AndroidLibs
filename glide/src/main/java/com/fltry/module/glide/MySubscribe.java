package com.fltry.module.glide;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MySubscribe<T> {

    public MySubscribe(Observable<T> observable, final OnDataCallBack<T> callBack) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        callBack.succeed(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
