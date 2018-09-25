package com.fltry.module.glide;


import android.text.Editable;
import android.text.TextWatcher;

public class GlideVM {

    public GlideModel glideModel;
    IGlideView iGlideView;

    public GlideVM(IGlideView iGlideView) {
        this.iGlideView = iGlideView;
        glideModel = new GlideModelImpl();
    }

    public void gitMovies() {
        if (iGlideView.getCount().equals("")) {
            iGlideView.httpFailed("请输入每页的数量");
            return;
        }
        glideModel.loadMovie(Integer.parseInt(iGlideView.getCount()), new OnDataCallBack<Movice>() {
            @Override
            public void succeed(Movice data) {
                iGlideView.httpSucceed(data);
            }

            @Override
            public void failed(String error) {
                iGlideView.httpFailed(error);
            }
        });
    }

    public TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            gitMovies();
        }
    };

}
