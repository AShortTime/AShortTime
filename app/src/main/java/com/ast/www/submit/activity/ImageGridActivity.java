package com.ast.www.submit.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.ast.www.R;
import com.ast.www.submit.adapter.ImageGridAdapter;
import com.ast.www.submit.bean.ImageItem;
import com.ast.www.submit.utils.AlbumHelper;
import com.ast.www.submit.utils.Bimp;
import com.ast.www.submit.utils.FileUtils;

import org.zackratos.ultimatebar.UltimateBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * 相册夹 照片页面
 */
public class ImageGridActivity extends Activity {
	public static final String EXTRA_IMAGE_LIST = "imagelist";

	// ArrayList<Entity> dataList;
	List<ImageItem> dataList;
	GridView gridView;
	ImageGridAdapter adapter;
	AlbumHelper helper;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity.this, "最多选择10张图片",Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_grid);

		UltimateBar ultimateBar = new UltimateBar(this);
		ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.appBlue));

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		dataList = (List<ImageItem>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);

		initView();

		initData();

	}

	private void initData() {
		/**
		 * 取消按钮
		 */
		toolbar_titleLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				cancleDialog();
			}
		});
		/**
		 * 提交按钮
		 */
		toolbar_titleRight.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ArrayList<String> list = new ArrayList<String>();
				Collection<String> c = adapter.map.values();
				Iterator<String> it = c.iterator();
				//将图片地址 遍历保存
				int count = 0;
				if (c.size()!=0){
					for (; it.hasNext();) {
						//判断将 9张图片进行保存
						if (Bimp.drr.size() < 10) {
							Bimp.drr.add(it.next());
						}
						++count;
					}
					//将图片缓存 遍历保存
					while (true) {
						if (Bimp.max == Bimp.drr.size()) {
							break;
						}else {
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

				Intent intent = new Intent(ImageGridActivity.this, PublishedActivity.class);
				startActivity(intent);
				finish();
			}

		});

		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity.this, dataList, mHandler);
		gridView.setAdapter(adapter);
		adapter.setTextCallback(new ImageGridAdapter.TextCallback() {
			public void onListen(int count) {
				toolbar_titleRight.setText("完成" + "(" + count + ")");
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.notifyDataSetChanged();
			}

		});
	}

	private TextView toolbar_title,toolbar_titleLeft,toolbar_titleRight;

	private void initView() {
		toolbar_title = (TextView) findViewById(R.id.item_title);
		toolbar_title.setText("相册");
		toolbar_titleLeft= (TextView) findViewById(R.id.item_title_left);
		toolbar_titleLeft.setText("取消");
		toolbar_titleRight= (TextView) findViewById(R.id.item_title_right);
		toolbar_titleRight.setText("完成");

		gridView = (GridView) findViewById(R.id.gridview);

	}

	/**
	 * 取消编辑Dialog
	 */
	public void cancleDialog(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("是否取选择，若取消该页面不做保存？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (adapter!=null){
					adapter.map.clear();
				}
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

}
