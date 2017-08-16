package com.ast.www.submit.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.ast.www.R;
import com.ast.www.folding.Util;
import com.ast.www.model.util.Utils;
import com.ast.www.submit.persenter.PublishedPersenter;
import com.ast.www.submit.bean.CodeBean;
import com.ast.www.submit.utils.Bimp;
import com.ast.www.submit.utils.FileUtils;
import com.ast.www.view.activity.LogInActivity;

import org.zackratos.ultimatebar.UltimateBar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PublishedActivity extends Activity implements OnClickListener {

	public static final String TAG=PublishedActivity.class.getSimpleName();
	private GridView noScrollgridview;
	private GridAdapter adapter;
	private PublishedPersenter persenter;
	/**
	 * 拍照回调结果码
	 */
	private static final int TAKE_PICTURE = 0x000000;

	private TextView toolbar_title,toolbar_titleLeft,toolbar_titleRight;

	String path;
	private EditText editContent;
	private PopupWindows popupWindows;

	/**
	 * 获取焦点
	 * 刷新GridView
	 */
	@Override
	protected void onResume() {
		super.onResume();
		if (adapter!=null){
			adapter.notifyDataSetChanged();
		}
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimg);
		/**
		 * 沉浸式
		 */
		UltimateBar ultimateBar = new UltimateBar(this);
		ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.appBlue));

		initViews();

		initDatas();
	}

	/**
	 * 初始化控件
	 */
	private void initViews() {
		editContent = (EditText) findViewById(R.id.selectimg_edit);
		toolbar_title = (TextView) findViewById(R.id.item_title);
		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		toolbar_titleLeft= (TextView) findViewById(R.id.item_title_left);
		toolbar_titleRight= (TextView) findViewById(R.id.item_title_right);
		/**
		 * 取消编辑
		 */
		toolbar_titleLeft.setOnClickListener(this);
		/**
		 * 发表图片
		 */
		toolbar_titleRight.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	public void initDatas() {
		persenter = new PublishedPersenter(this);

		toolbar_title.setText("发表文章");
		toolbar_titleLeft.setText("取消");
		toolbar_titleRight.setText("发表");

		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		noScrollgridview.setAdapter(adapter);
		/**
		 * GridView 点击监听
		 */
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				if (arg2 == Bimp.bmp.size()) {
					//展示popupWindows
					popupWindows = new PopupWindows(PublishedActivity.this, noScrollgridview);
				} else {
					//跳转编辑页面
					Intent intent = new Intent(PublishedActivity.this, PhotoActivity.class);
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}

			}
		});

	}

	/**
	 * GridView 适配器
	 */
	@SuppressLint("HandlerLeak")
	public class GridAdapter extends BaseAdapter {

		private LayoutInflater inflater; // 视图容器
		private int selectedPosition = -1;// 选中的位置
		private boolean shape;
		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}


		public int getCount() {
			return (Bimp.bmp.size() + 1);
		}

		public Object getItem(int arg0) {

			return null;
		}

		public long getItemId(int arg0) {

			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		/**
		 * ListView Item设置
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			final int coord = position;
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.item_published_grida,parent, false);
				holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == Bimp.bmp.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_addpic_unfocused));
				if (position == 10) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.bmp.get(position));
			}

			return convertView;
		}

		public class ViewHolder {

			public ImageView image;
		}
	}


	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	/**
	 * 更新图片
	 */
	public void updateIms(){
		while (true) {
			if (Bimp.max == Bimp.drr.size()) {
				adapter.notifyDataSetChanged();
				break;
			} else {
				try {
					String path = Bimp.drr.get(Bimp.max);
					Bitmap bm = Bimp.revitionImageSize(path);
					Bimp.bmp.add(bm);
					String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
					FileUtils.saveBitmap(bm, "" + newStr);
					Bimp.max += 1;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * PopupWindos 弹窗
	 */
	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);

			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					photo2();
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(PublishedActivity.this, TestPicActivity.class);
					startActivity(intent);
					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});

		}

	}



	@Override
	public void onClick(View v) {

		switch (v.getId()){
			case R.id.item_title_left:
				cancleDialog();
				break;
			/**
			 * 发表页面
			 */
			case R.id.item_title_right:
				uploadPic();
				break;
		}
	}


	/**
	 * 拍照方法
	 */
	public void photo2() {

		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file1 = new File(Environment.getExternalStorageDirectory()
				+ "/myimage");
		if (!file1.exists()) {
			file1.mkdir();
		}
		File file = new File(file1, String.valueOf(System.currentTimeMillis()) + ".jpg");
		path = file.getPath();
		Uri imageUri = FileProvider.getUriForFile(this,getPackageName() + ".provider", file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}


	/**
	 * 上传图片数据
	 */
	public void uploadPic() {

		Log.i(TAG, "uploadPic: 上传文字图片数据");

		List<File> pathList = new ArrayList<>();
		/**
		 * 获取图片资源
		 */
		for (int i = 0; i < Bimp.drr.size(); i++) {
			String Str = Bimp.drr.get(i).substring(Bimp.drr.get(i).lastIndexOf("/") + 1, Bimp.drr.get(i).lastIndexOf("."));
			pathList.add(new File(FileUtils.SDPATH + Str + ".JPEG"));
		}


		MultipartBody.Builder setType = new MultipartBody.Builder().setType(MultipartBody.FORM);
		//上传用户
        SharedPreferences users = Utils.getSharedPrefers(this);
        String userId = users.getString("userId", "");
        if (userId.equals("")){
            toLogin();
            return;
        }

        setType.addFormDataPart("userId",userId);
		/**
		 * 图片非Null 状态
		 * 数据类型：图片 3
		 * 则 文字数据作为图片描述传输
		 */
		if (pathList.size()!=0){
			setType.addFormDataPart("description",editContent.getText().toString().trim());//图片描述
			setType.addFormDataPart("dictionaryValue","3");//资源类型

			for (int i = 0; i < pathList.size(); i++) {
				RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), pathList.get(i));
                setType.addFormDataPart("file",userId+"_"+getTime()+".jpg", imageBody);
			}

		}
		/**
		 * 图片为Null 状态
		 * 数据类型：段子 2
		 * 则 文字数据作为段子数据传输
		 */
		else {
			String content = editContent.getText().toString().trim();
			setType.addFormDataPart("description","");//图片描述
			setType.addFormDataPart("dictionaryValue","2");//资源类型
			if (!TextUtils.isEmpty(content)){
				setType.addFormDataPart("content",content);//资源类型
			}else {
				Toast.makeText(this, "你没有上传数据呦！~", Toast.LENGTH_SHORT).show();
				return;
			}
		}

		List<MultipartBody.Part> parts = setType.build().parts();
		/**
		 * 调用P层 上传方法
		 */
		persenter.uploadData("picture/picchaUpload",parts);
	}

	public String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 提示用户进行登录
     */
    private void toLogin() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("发表失败");
        builder.setMessage("很抱歉，您好像未登录，无法发表作品？让我带你去登录吧！~");
        builder.setPositiveButton("前去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(PublishedActivity.this, LogInActivity.class));
            }
        });
        builder.setNegativeButton("拒绝，并取消发表", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    /**
	 * 上传成功回调
	 * @param uploadBean
	 */
	public void upLoad(CodeBean uploadBean){
		Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();

		FileUtils.deleteDir();
		Bimp.max = 0;
		Bimp.drr.clear();
		Bimp.bmp.clear();

		startActivity(new Intent(this,SubmitSuccesActivity.class));
		finish();
	}




	/**
	 * 取消编辑Dialog
	 */
	public void cancleDialog(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("是否取消编辑，若取消该页面不做保存？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Bimp.bmp.clear();
				Bimp.drr.clear();
				Bimp.max = 0;
				FileUtils.deleteDir();
				finish();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create();
		builder.show();
	}


	/**
	 * 页面销毁回调
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			/**
			 * 拍照 销毁回调
			 */
			case TAKE_PICTURE:
				if (Bimp.drr.size() < 9 && resultCode == -1) {
					Bimp.drr.add(path);
				}
				break;
		}
	}

	/**
	 * 返回键点击监听
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (popupWindows!=null&&popupWindows.isShowing()){
			popupWindows.dismiss();
			return true;
		}else {
			cancleDialog();
			return false;
		}

	}
}
