package com.ast.www.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ast.www.R;
import com.ast.www.model.bean.VideoRlvImageBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/11 20:18
 * Title:
 * Text:
 */

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private List<VideoRlvImageBean.MediaBean> mList=new ArrayList<>();
    private List<Integer> mHeights=new ArrayList<>();

    public void setmList(List<VideoRlvImageBean.MediaBean> mList) {
        this.mList = mList;
        getRandomHeight(mList);
        notifyDataSetChanged();
    }

    public WaterFallAdapter(Context context){
        this.mContext = context;
    }

    public void getRandomHeight(List<VideoRlvImageBean.MediaBean> mList){
        for(int i=0; i < mList.size();i++){
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int)(300+Math.random()*400));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_video_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        VideoRlvImageBean.MediaBean mediaBean = mList.get(position);
        holder.mImageView.setImageURI(mediaBean.getMediaPictureSrc());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        SimpleDraweeView mImageView;

        public ViewHolder(View view){
            //需要设置super
            super(view);
       mImageView = (SimpleDraweeView) view.findViewById(R.id.video_image);
        }
    }

    /**
     * 监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //条目监听
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);

        void OnItemLongClick(View view, int position);
    }


}
