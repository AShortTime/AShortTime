package com.ast.www.submit.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.ast.www.R;
import com.ast.www.submit.PublishedPersenter;
import com.ast.www.submit.bean.CodeBean;
import com.ast.www.submit.utils.Bimp;
import com.ast.www.submit.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PublishedActivity extends Activity implements OnClickListener {

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private PublishedPersenter persenter;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimg);
		Init();
	}

	private TextView toolbar_title,toolbar_titleLeft,toolbar_titleRight;

	public void Init() {

		persenter = new PublishedPersenter(this);

		toolbar_title = (TextView) findViewById(R.id.item_title);
		toolbar_title.setText("发表文章");
		toolbar_titleLeft= (TextView) findViewById(R.id.item_title_left);
		toolbar_titleLeft.setText("取消");
		toolbar_titleLeft.setOnClickListener(this);
		toolbar_titleRight= (TextView) findViewById(R.id.item_title_right);
		toolbar_titleRight.setText("发表");
		toolbar_titleRight.setOnClickListener(this);


		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		adapter.update();
		noScrollgridview.setAdapter(adapter);
		noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arg2 == Bimp.bmp.size()) {
					new PopupWindows(PublishedActivity.this, noScrollgridview);
				} else {
					Intent intent = new Intent(PublishedActivity.this, PhotoActivity.class);
					intent.putExtra("ID", arg2);
					startActivity(intent);
				}
			}
		});

		/**
		 * 发表
		 */
		toolbar_titleRight.setOnClickListener(this);
	}


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

		public void update() {
			loading();
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
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.mipmap.icon_addpic_unfocused));
				if (position == 9) {
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
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						/**
						 * true：
						 * 如果图片长度与集合长度相等，更新适配器
						 * false：
						 *
						 */
						if (Bimp.max == Bimp.drr.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							try {
								String path = Bimp.drr.get(Bimp.max);
								System.out.println(path);
								Bitmap bm = Bimp.revitionImageSize(path);
								Bimp.bmp.add(bm);
								String newStr = path.substring(
										path.lastIndexOf("/") + 1,
										path.lastIndexOf("."));
								FileUtils.saveBitmap(bm, "" + newStr);
								Bimp.max += 1;
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							} catch (IOException e) {

								e.printStackTrace();
							}
						}
					}
				}
			}).start();
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

	protected void onRestart() {
		adapter.update();
		super.onRestart();
	}

	public class PopupWindows extends PopupWindow {

		public PopupWindows(Context mContext, View parent) {

			View view = View
					.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(LayoutParams.FILL_PARENT);
			setHeight(LayoutParams.FILL_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
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


	private static final int TAKE_PICTURE = 0x000000;

	//相机权限
	private String[] permissions_camera= {android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE};



	String path;
	/**
	 * 拍照方法
	 */
	public void photo2() {
		//判断 版本 当系统大于23时，申请动态权限
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
			//判断是否授权
			int i = ContextCompat.checkSelfPermission(this, permissions_camera[0]);
			//GRANTED 授权 DINIED 拒绝
			if (i== PackageManager.PERMISSION_DENIED){
				showDialogTipUserRequestPermission();
				return;
			}
		}

		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file1 = new File(Environment.getExternalStorageDirectory()
				+ "/myimage");
		if (!file1.exists()) {
			file1.mkdir();
		}
		File file = new File(file1, String.valueOf(System.currentTimeMillis()) + ".jpg");
		path = file.getPath();
		//Uri imageUri = Uri.fromFile(file);
		Uri imageUri = FileProvider.getUriForFile(this,getPackageName() + "" +
				".provider", file);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
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
				submitContent();
				break;
		}

	}

	/**
	 * 上传数据
	 */
	private void submitContent() {

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < Bimp.drr.size(); i++) {
			String Str = Bimp.drr.get(i).substring(
					Bimp.drr.get(i).lastIndexOf("/") + 1,
					Bimp.drr.get(i).lastIndexOf("."));
			list.add(FileUtils.SDPATH + Str + ".JPEG");
		}
		uploadPic(list);
	}

	/**
	 * 上传图片数据
	 * @param list
	 */
	public void uploadPic(List<String> list) {
		List<File> pathList = new ArrayList<>();
		for (int x = 0; x < list.size(); x++) {
			File file = new File(list.get(x));
			pathList.add(file);
		}
		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("description", "上传图片")
				.addFormDataPart("dictionaryValue", 3+"")
				.addFormDataPart("userId", 1+"");
		for (int i = 0; i < pathList.size(); i++) {
			RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), pathList.get(i));
			builder.addFormDataPart("file", "wodepic", imageBody);
		}
		List<MultipartBody.Part> parts = builder.build().parts();
		/**
		 * 调用P层 上传方法
		 */
		persenter.uploadData("picture/picchaUpload",parts);
	}

	/**
	 * 上传成功回调
	 * @param uploadBean
	 */
	public void upLoad(CodeBean uploadBean){
		Toast.makeText(this, "图片上传成功", Toast.LENGTH_SHORT).show();
		FileUtils.deleteDir();
		Bimp.max = 0;
		Bimp.drr.clear();
		Bimp.bmp.clear();

		startActivity(new Intent(this,SubmitSuccesActivity.class));
		finish();
	}

	/**
	 * 用户申请授权回调
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode==0){
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
				//如果授权失败
				if (grantResults[0]!=PackageManager.PERMISSION_GRANTED){
					//检测是否允许继续申请授权
					//系统方法
					boolean isP = shouldShowRequestPermissionRationale(permissions_camera[0]);
					//是否被设置为不在申请
					//false：继续要求用户自己手动申请
					//true ：关闭页面
					if (!isP){
						//展示手动授权页面
						showDialogToSettings();
					}else {

					}

				}else {
					Toast.makeText(this, "相机授权成功", Toast.LENGTH_SHORT).show();
					photo2();
				}
			}
		}
	}

	/**
	 * 展示 设置相机权限Dialog
	 */
	private void showDialogTipUserRequestPermission() {
		new AlertDialog.Builder(this)
				.setTitle("相机权限不可用")
				.setMessage("由于拍照需要获取摄像头，进行图片拍摄;\n否则，您将无法正常使用摄像头")
				.setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//请求权限 跳转到授权回调
						ActivityCompat.requestPermissions(PublishedActivity.this,permissions_camera,0);
					}
				})
				.setNegativeButton("取消授权", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.setCancelable(false)
				.show();
	}

	/**
	 * 展示 Dialog 跳转至 设置
	 */
	private void showDialogToSettings() {
		new AlertDialog.Builder(this)
				.setTitle("自动授权失败")
				.setMessage("请在设置-应用-权限 中，允许应用使用摄像头权限")
				.setPositiveButton("前去设置", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.setNegativeButton("不，算了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				})
				.setCancelable(false)
				.show();
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
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case TAKE_PICTURE:
				if (Bimp.drr.size() < 9 && resultCode == -1) {
					Bimp.drr.add(path);
				}
				break;
		}
	}

}
