package com.ast.www.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ast.www.R;
import com.ast.www.submit.activity.PublishedActivity;
import com.ast.www.submit.activity.SubmitSuccesActivity;
import com.ast.www.submit.utils.Bimp;
import com.ast.www.submit.utils.FileUtils;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Effect :提交前的 创作页面
 * 获取动态权限
 * <p>
 * Created by Administrator
 * Time by 2017/7/31 0031
 */

public class SubmitActivity extends BaseAvtivity implements View.OnClickListener {


    private TextView left_tv;
    private TextView title;

    @Override
    protected void createmPresenter() {

    }

    /**
     * 初始化ui
     **/
    @Override
    protected void initUI() {

        /**
         * 首先先获取权限
         */
        checkCameraPermission();


        findViewById(R.id.iv_submit_capture).setOnClickListener(this);
        findViewById(R.id.iv_submit_text).setOnClickListener(this);

        left_tv = (TextView) findViewById(R.id.item_title_left);
        title = (TextView) findViewById(R.id.item_title);

        left_tv.setText("取消");
        title.setText("创作");

        left_tv.setOnClickListener(this);
    }

    /**
     * 查询权限
     */
    private void checkCameraPermission() {
        /**
         * 判断系统版本
         * 是否申请动态权限
         */
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            //判断是否授权
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                    .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager
                            .PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                            .PERMISSION_GRANTED){
                /**
                 * 授权成功
                 */
            }else {
                /**
                 * 无应用授权
                 * 是否给以权限
                 */
                showPermissionDialog();
            }
        }
    }

    /**
     * 展示是否给以权限询问
     */
    private void showPermissionDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("询问");
        builder.setMessage("提交页面，需求使用 摄像机 存储等权限，是否给予？否则无法使用");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /**
                 * 授权申请
                 * 不具有获取权限，需要进行权限申请
                 */
                ActivityCompat.requestPermissions(SubmitActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.create();
        builder.show();
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
        return R.layout.activity_submit;
    }


    @Override
    public void onClick(View v) {
        /**
         * 获取权限
         */

        switch (v.getId()){
            case R.id.item_title_left:
                finish();
                break;
            case R.id.iv_submit_capture:
                /**
                 * 视频上传
                 */
                startActivity(new Intent(this,CameraActivity.class));
                finish();
                break;
            case R.id.iv_submit_text:
                /**
                 * 文字图片
                 */
                startActivity(new Intent(this,PublishedActivity.class));
                finish();
                break;
        }
    }




    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码


    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
