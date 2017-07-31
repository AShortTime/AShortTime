package com.ast.www.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.constom.Userinfoview;
//import com.superplayer.library.SuperPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/28 10:52
 * Title:
 * Text:
 */

public class RlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //图片类型
    public final static int TYPE_IMAGE = 0;
    //视频类型
    public final static int TYPE_VIDEO = 1;

    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<String> list;

    public RlvAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }


    @Override
    public int getItemViewType(int position) {
        if (position%2!=0) {
            return TYPE_IMAGE;
        }
        return TYPE_VIDEO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_IMAGE:
                view = LayoutInflater.from(context).inflate(R.layout.item_rlv_joke, parent, false);
                holder = new ImageHolder(view);
                break;
            case TYPE_VIDEO:
                view = LayoutInflater.from(context).inflate(R.layout.item_rlv_video, parent, false);
                holder = new VideoHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_IMAGE:

                onItemEventClick(holder);
                ((ImageHolder) holder).userinfoview.setTime("图片");

                break;
            case TYPE_VIDEO:


                onItemEventClick(holder);
                ((VideoHolder) holder).userinfoview.setTime("视频");
                break;

        }
    }

    protected void onItemEventClick(RecyclerView.ViewHolder holder) {
        final int position = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(v, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.OnItemLongClick(v, position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        public Userinfoview userinfoview;
        public TextView itemtext;
        public GridView gridView;
//        public SuperPlayer superPlayer;

        public ImageHolder(View itemView) {
            super(itemView);
            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.joke_user_info);
           // this.gridView = (GridView) itemView.findViewById(R.id.gv);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);


        }

    }

    class VideoHolder extends RecyclerView.ViewHolder {
        public Userinfoview userinfoview;
        public TextView itemtext;
//        public SuperPlayer superPlayer;

        public VideoHolder(View itemView) {
            super(itemView);
            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.video_user_info);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);
            //this.superPlayer = (SuperPlayer) itemView.findViewById(R.id.super_player);
        }

    }

    /**
     * 监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void OnItemClick(View view, int position);

        void OnItemLongClick(View view, int position);
    }
}
