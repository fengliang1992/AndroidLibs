package com.fltry.module.picture;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.fltry.module.lib_common.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class PictureActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private File file;
    private ImageView mPicSbIv1;
    private ImageView mPicSbIv2;
    private RadioGroup mPicSbRg1;
    private ImageView mPicPhotoIv1;
    /**
     * 拍照
     */
    private Button mPicPhotoBtn1;
    /**
     * 相册
     */
    private Button mPicPhotoBtn2;
    /**
     * 展示大图片
     */
    private Button mPicLargeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("图片的各类处理");
        initView();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
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
        mPicSbIv1.setScaleType(scaleType);
        mPicSbIv2.setScaleType(scaleType);
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
                mPicPhotoIv1.setImageURI(Uri.fromFile(file));
            } else if (requestCode == 2) {
                cropImageUri = PhotoUtils.getCropImageUri(PictureActivity.this, "myHead.png");
                PhotoUtils.startPhotoZoom(PictureActivity.this, data.getData(), cropImageUri);
            } else if (requestCode == 3) {
                try {
                    Bitmap headShot = BitmapFactory.decodeStream(getContentResolver().openInputStream(cropImageUri));
                    mPicPhotoIv1.setImageBitmap(headShot);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void initView() {
        mPicSbIv1 = (ImageView) findViewById(R.id.pic_sb_iv1);
        mPicSbIv2 = (ImageView) findViewById(R.id.pic_sb_iv2);
        mPicSbRg1 = (RadioGroup) findViewById(R.id.pic_sb_rg1);
        mPicSbRg1.setOnCheckedChangeListener(this);
        mPicPhotoIv1 = (ImageView) findViewById(R.id.pic_photo_iv1);
        mPicPhotoBtn1 = (Button) findViewById(R.id.pic_photo_btn1);
        mPicPhotoBtn1.setOnClickListener(this);
        mPicPhotoBtn2 = (Button) findViewById(R.id.pic_photo_btn2);
        mPicPhotoBtn2.setOnClickListener(this);
        mPicLargeBtn = (Button) findViewById(R.id.pic_large_btn);
        mPicLargeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pic_photo_btn1) {
            useCamera();
        } else if (v.getId() == R.id.pic_photo_btn2) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(intent, 2);//打开相册
        } else if (v.getId() == R.id.pic_large_btn) {
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
    }
}
