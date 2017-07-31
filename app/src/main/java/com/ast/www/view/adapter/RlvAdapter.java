package com.ast.www.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.constom.Userinfoview;

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
                VideoHolder vh = (VideoHolder) holder;
                vh.userinfoview.setTime("视频");
                String s = Environment.getExternalStorageDirectory().getPath() + "/b.flv";
                vh.update(position);
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


        public ImageHolder(View itemView) {
            super(itemView);
            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.joke_user_info);
            this.gridView = (GridView) itemView.findViewById(R.id.gv);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);

        }
    }


    class VideoHolder extends RecyclerView.ViewHolder {
        public Userinfoview userinfoview;
        public TextView itemtext;
        public RelativeLayout rlayPlayerControl;
        private RelativeLayout rlayPlayer;

        public VideoHolder(View itemView) {
            super(itemView);
            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.video_user_info);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);
            rlayPlayerControl = (RelativeLayout) itemView.findViewById(R.id.adapter_player_control);
            rlayPlayer = (RelativeLayout) itemView.findViewById(R.id.adapter_super_video_layout);
//            if (rlayPlayer!=null){
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlayPlayer.getLayoutParams();
//                layoutParams.height = (int) (SuperPlayerUtils.getScreenWidth((Activity) context) * 0.5652f);//这值是网上抄来的，我设置了这个之后就没有全屏回来拉伸的效果，具体为什么我也不太清楚
//                rlayPlayer.setLayoutParams(layoutParams);
//            }
        }
        public void update(final int position) {
            //点击回调 播放视频
            rlayPlayerControl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (playclick != null)
                        playclick.onPlayclick(position, rlayPlayerControl);
                }
            });
        }
    }
    private onPlayClick playclick;

    public void setPlayClick(onPlayClick playclick) {
        this.playclick = playclick;
    }

    public interface onPlayClick {
        void onPlayclick(int position, RelativeLayout image);
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
