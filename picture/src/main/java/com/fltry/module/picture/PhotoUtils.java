package com.fltry.module.picture;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

/**
 * Created by tol on 2018-06-21.
 */

public class PhotoUtils {

    //crop 	                                String 	发送裁剪信号
    //aspectX 	                            int 	X方向上的比例
    //aspectY 	                            int 	Y方向上的比例
    //outputX 	                            int 	裁剪区的宽
    //outputY 	                            int 	裁剪区的高
    //scale 	                            boolean 	是否保留比例
    //return-data 	                        boolean 	是否将数据保留在Bitmap中返回
    //data 	                                Parcelable 	相应的Bitmap数据
    //circleCrop 	                        String 	圆形裁剪区域？
    //MediaStore.EXTRA_OUTPUT ("output") 	URI 	将URI指向相应的file:///...
    public static void startPhotoZoom(Activity activity, Uri uri,Uri cropImageUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, 3);
    }

    //裁剪图片存放地址的Uri
    public static Uri getCropImageUri(Activity activity, String fileName) {
        File CropPhoto = new File(activity.getExternalCacheDir(), fileName);
        try {
            if (CropPhoto.exists()) {
                CropPhoto.delete();
            }
            CropPhoto.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(CropPhoto);
    }
}
