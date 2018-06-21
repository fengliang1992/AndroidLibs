package com.fltry.androidlibs.utils.picture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.fltry.androidlibs.R;
import com.fltry.androidlibs.ui.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

public class PictureActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.pic_sb_iv1)
    ImageView picSbIv1;
    @BindView(R.id.pic_sb_iv2)
    ImageView picSbIv2;
    @BindView(R.id.pic_sb_rg1)
    RadioGroup picSbRg1;
    @BindView(R.id.pic_photo_iv1)
    ImageView picPhotoIv1;

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("图片的各类处理");

        init();
    }

    private void init() {
        picSbRg1.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @OnClick({R.id.pic_photo_btn1, R.id.pic_photo_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pic_photo_btn1:
                useCamera();
                break;
            case R.id.pic_photo_btn2:
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, 2);//打开相册
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER;
        switch (checkedId) {
            case R.id.pic_sb_rb1:
                scaleType = ImageView.ScaleType.CENTER;
                break;
            case R.id.pic_sb_rb2:
                scaleType = ImageView.ScaleType.CENTER_INSIDE;
                break;
            case R.id.pic_sb_rb3:
                scaleType = ImageView.ScaleType.CENTER_CROP;
                break;
            case R.id.pic_sb_rb4:
                scaleType = ImageView.ScaleType.MATRIX;
                break;
            case R.id.pic_sb_rb5:
                scaleType = ImageView.ScaleType.FIT_XY;
                break;
            case R.id.pic_sb_rb6:
                scaleType = ImageView.ScaleType.FIT_START;
                break;
            case R.id.pic_sb_rb7:
                scaleType = ImageView.ScaleType.FIT_CENTER;
                break;
            case R.id.pic_sb_rb8:
                scaleType = ImageView.ScaleType.FIT_END;
                break;
        }
        picSbIv1.setScaleType(scaleType);
        picSbIv2.setScaleType(scaleType);
    }


    /**
     * 使用相机
     */
    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myImgs/" + System.currentTimeMillis() + ".png");
        file.getParentFile().mkdirs();
        //改变Uri  com.fltry.androidlibs.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.fltry.androidlibs.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 1);
    }

    private static Uri cropImageUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                picPhotoIv1.setImageURI(Uri.fromFile(file));
            } else if (requestCode == 2) {
                cropImageUri = PhotoUtils.getCropImageUri(PictureActivity.this, "myHead.png");
                PhotoUtils.startPhotoZoom(PictureActivity.this, data.getData(), cropImageUri);
            } else if (requestCode == 3) {
                try {
                    Bitmap headShot = BitmapFactory.decodeStream(getContentResolver().openInputStream(cropImageUri));
                    picPhotoIv1.setImageBitmap(headShot);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
