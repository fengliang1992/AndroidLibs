package com.fltry.module.picture;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.fltry.module.lib_common.BaseActivity;
import com.fltry.module.picture.databinding.ActivityPictureBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class PictureActivity extends BaseActivity<ActivityPictureBinding> implements RadioGroup.OnCheckedChangeListener {

    private File file;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected String title() {
        return "图片的各类处理";
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER;
        if (checkedId == R.id.pic_sb_rb1) {
            scaleType = ImageView.ScaleType.CENTER;
        } else if (checkedId == R.id.pic_sb_rb2) {
            scaleType = ImageView.ScaleType.CENTER_INSIDE;
        } else if (checkedId == R.id.pic_sb_rb3) {
            scaleType = ImageView.ScaleType.CENTER_CROP;
        } else if (checkedId == R.id.pic_sb_rb4) {
            scaleType = ImageView.ScaleType.MATRIX;
        } else if (checkedId == R.id.pic_sb_rb5) {
            scaleType = ImageView.ScaleType.FIT_XY;
        } else if (checkedId == R.id.pic_sb_rb6) {
            scaleType = ImageView.ScaleType.FIT_START;
        } else if (checkedId == R.id.pic_sb_rb7) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        } else if (checkedId == R.id.pic_sb_rb8) {
            scaleType = ImageView.ScaleType.FIT_END;
        }
        dataBinding.include1.picSbIv1.setScaleType(scaleType);
        dataBinding.include1.picSbIv2.setScaleType(scaleType);
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
                dataBinding.include2.picPhotoIv1.setImageURI(Uri.fromFile(file));
            } else if (requestCode == 2) {
                cropImageUri = PhotoUtils.getCropImageUri(PictureActivity.this, "myHead.png");
                PhotoUtils.startPhotoZoom(PictureActivity.this, data.getData(), cropImageUri);
            } else if (requestCode == 3) {
                try {
                    Bitmap headShot = BitmapFactory.decodeStream(getContentResolver().openInputStream(cropImageUri));
                    dataBinding.include2.picPhotoIv1.setImageBitmap(headShot);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void initView() {
        RadioGroup mPicSbRg1 = (RadioGroup) findViewById(R.id.pic_sb_rg1);
        mPicSbRg1.setOnCheckedChangeListener(this);
    }

    public void camera(View v) {
        useCamera();
    }

    public void photo(View v) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 2);//打开相册
    }

    public void largePicture(View v) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            InputStream is = getResources().getAssets().open("qingming.jpg");
            BitmapFactory.decodeStream(is, null, options);
            LargeImageView largeImageView = new LargeImageView(mContext);
            Dialog dialog = new Dialog(mContext);
            largeImageView.setInputStream(is, options.outWidth, options.outHeight);
            dialog.setContentView(largeImageView);
            dialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void picLargeWeb(View v) {
        startActivity(new Intent(mContext, BigPicActivity.class));
    }
}
