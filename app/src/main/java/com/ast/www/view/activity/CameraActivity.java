package com.ast.www.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.submit.activity.PublishedActivity;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.lisenter.ErrorLisenter;
import com.cjt2325.cameralibrary.lisenter.JCameraLisenter;

import org.zackratos.ultimatebar.UltimateBar;

import java.io.File;

import static android.R.attr.bitmap;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/8/8 0008
 */

public class CameraActivity extends BaseAvtivity {

    private JCameraView jCameraView;

    @Override
    protected void createmPresenter() {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
    }

    /**
     * 初始化ui
     **/
    @Override
    protected void initUI() {
        jCameraView = (JCameraView) findViewById(R.id.jcamera_camera);
        //设置保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        //相机 视频 是否可用
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_RECORDER);
        jCameraView.setTip("JCameraView Tip");
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        jCameraView.setErrorLisenter(new ErrorLisenter() {

            /**
             * 错误回调
             */
            @Override
            public void onError() {

            }

            /**
             * 权限错误回调
             */
            @Override
            public void AudioPermissionError() {

            }
        });


        jCameraView.setJCameraLisenter(new JCameraLisenter() {

            /**
             * 拍照成功回调
             */
            @Override
           public void captureSuccess(Bitmap bitmap) {
//                Toast.makeText(CameraActivity.this, "图片获取成功", Toast.LENGTH_SHORT).show();
//                descCamera(bitmap);
                //获取图片bitmap
//                Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
//                String path = FileUtil.saveBitmap("JCamera", bitmap);
//                Intent intent = new Intent();
//                intent.putExtra("path", path);
//                setResult(101, intent);
//                finish();
            }

            /**
             * 视频成功回调
             * @param url
             * @param firstFrame
             */
            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                descCamera(url,firstFrame);
                //获取视频路径
//                String path = FileUtil.saveBitmap("JCamera", firstFrame);
//                Log.i("CJT", "url = " + url + ", Bitmap = " + path);
//                Intent intent = new Intent();
//                intent.putExtra("path", path);
//                setResult(101, intent);
//                finish();
            }

            @Override
            public void quit() {
                //退出按钮
                CameraActivity.this.finish();
            }
        });
    }

    /**
     * 初始化数据
     **/
    @Override
    protected void initData() {

    }

    /**
     * 初始化监听
     **/
    @Override
    protected void initListener() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_camera;
    }


    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onResume() {
        Log.i("CJT", "onResume");
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("CJT", "onPause");
        super.onPause();
        jCameraView.onPause();
    }

    public void descCamera(final String url, final Bitmap bitmap){
        //初始化View视图
        View view=View.inflate(this,R.layout.layout_camera_dialog,null);
        //初始化控件
        ImageView iv= (ImageView) view.findViewById(R.id.layout_iv_camera);
        iv.setImageBitmap(bitmap);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /**
         * 初始化Dialog
         */
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("作品描述");
        builder.setView(view);
        builder.setPositiveButton("发表，Go!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**
                 * 上传数据
                 */
                submitCamera(url,bitmap);
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("不，不发表了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 视频上传接口
     */
    private void submitCamera(String url,Bitmap bitmap) {

    }

}
