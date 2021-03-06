package com.ast.www.view.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.constom.Userinfoview;
import com.ast.www.model.bean.RecommendHotBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/28 10:52
 * Title:
 * Text:
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //图片类型
    public final static int TYPE_IMAGE = 3;
    //视频类型
    public final static int TYPE_VIDEO = 1;

    public final static int TYPE_JOKE = 2;

    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<RecommendHotBean.ResourceBean> list;

    public UserAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }


    public List<RecommendHotBean.ResourceBean> getList() {
        return list;
    }

    public void setList(List<RecommendHotBean.ResourceBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getDictionaryValue().equals(TYPE_IMAGE + "")) {
            return TYPE_IMAGE;
        } else if (list.get(position).getDictionaryValue().equals(TYPE_VIDEO + "")) {
            return TYPE_VIDEO;
        } else if (list.get(position).getDictionaryValue().equals(TYPE_JOKE + "")) {
            return TYPE_JOKE;
        }
        return TYPE_JOKE;
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
            case TYPE_JOKE:
                view = LayoutInflater.from(context).inflate(R.layout.item_rlv_joke, parent, false);
//                view.findViewById(R.id.gv).setVisibility(View.GONE);
                view.findViewById(R.id.joke_image).setVisibility(View.GONE);
                holder = new ImageHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        Log.e("itemViewType", itemViewType+"11111" );
        switch (getItemViewType(position)) {
            case TYPE_IMAGE:
                onItemEventClick(holder);

                ImageHolder imageh = (ImageHolder) holder;
//                设置用户信息
                imageh.userinfoview.setUsername(list.get(position).getUser().getUserName());
                imageh.userinfoview.setTime(list.get(position).getUptime());
                if (!TextUtils.isEmpty(list.get(position).getUser().getUserHead())) {
                    imageh.userinfoview.setUserhead(list.get(position).getUser().getUserHead());
                }
                //设置内容
                imageh.itemtext.setText(list.get(position).getDescription());
                imageh.image.setImageURI(list.get(position).getPictureSrc());



                break;
            case TYPE_VIDEO:
                onItemEventClick(holder);
                VideoHolder vh = (VideoHolder) holder;

                //设置用户信息
                vh.userinfoview.setUsername(list.get(position).getUser().getUserName());
                vh.userinfoview.setTime(list.get(position).getUptime());
                if (!TextUtils.isEmpty(list.get(position).getUser().getUserHead())) {
                    vh.userinfoview.setUserhead(list.get(position).getUser().getUserHead());
                }

                //设置内容
                vh.itemtext.setText(list.get(position).getDescription());
                if (!TextUtils.isEmpty(list.get(position).getPictureSrc())) {
                    SimpleDraweeView draweeView = (SimpleDraweeView) vh.rlayPlayerControl.findViewById(R.id.adapter_super_video_iv_cover);
                    draweeView.setImageURI(list.get(position).getPictureSrc());
                }
                String src = list.get(position).getSrc();
                vh.update(position,src);
                vh.initListiner(position);
                break;
            case TYPE_JOKE:
                onItemEventClick(holder);
                ImageHolder jokeh = (ImageHolder) holder;
//                设置用户信息
                jokeh.userinfoview.setUsername(list.get(position).getUser().getUserName());
                jokeh.userinfoview.setTime(list.get(position).getUptime());
                if (!TextUtils.isEmpty(list.get(position).getUser().getUserHead())) {
                    jokeh.userinfoview.setUserhead(list.get(position).getUser().getUserHead());
                }

//                设置内容
                jokeh.image.setVisibility(View.GONE);
                jokeh.itemtext.setText(list.get(position).getContent());

                break;
        }
    }

    protected void onItemEventClick(RecyclerView.ViewHolder holder) {
        final int position = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendHotBean.ResourceBean resourceBean = list.get(position);
                onItemClickListener.OnItemClick(v, position,resourceBean);
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
        ////public GridView gridView;
        public SimpleDraweeView image;

        //悬浮按钮
        FloatingActionButton mshare;
        FloatingActionButton mdelete;


        public ImageHolder(View itemView) {
            super(itemView);
            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.joke_user_info);
//                this.gridView = (GridView) itemView.findViewById(R.id.gv);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);
            this.image = (SimpleDraweeView) itemView.findViewById(R.id.joke_image);

            this.mshare = (FloatingActionButton) itemView.findViewById(R.id.share);
            this.mdelete = (FloatingActionButton) itemView.findViewById(R.id.favorite);
        }

    }


    class VideoHolder extends RecyclerView.ViewHolder {
        public Userinfoview userinfoview;
        public TextView itemtext;
        public RelativeLayout rlayPlayerControl;

        //悬浮按钮
        FloatingActionButton mshare;
        FloatingActionButton mdelete;

        View foot;

        public VideoHolder(View itemView) {
            super(itemView);
            this.mshare = (FloatingActionButton) itemView.findViewById(R.id.share);
//            this.mdelete = (FloatingActionButton) itemView.findViewById(R.id.delete);
//            this.foot = itemView.findViewById(R.id.delete);


            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.video_user_info);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);
            this.rlayPlayerControl = (RelativeLayout) itemView.findViewById(R.id.adapter_player_control);

        }

        public void update(final int position, final String videosrc) {
            //点击回调 播放视频
            rlayPlayerControl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (playclick != null)
                        playclick.onPlayclick(position, rlayPlayerControl, videosrc);
                }
            });
        }

        public void initListiner(final int postion) {
            mshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("分享__RlvAdapter", "分享" + postion);
                }
            });
            mdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("删除__RlvAdapter", "删除" + postion);
                }
            });

        }
    }

    private onPlayClick playclick;

    public void setPlayClick(onPlayClick playclick) {
        this.playclick = playclick;
    }

    public interface onPlayClick {
        void onPlayclick(int position, RelativeLayout image, String videosrc);
    }

    /**
     * 监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//条目监听
    public interface OnItemClickListener {
        void OnItemClick(View view, int position, RecommendHotBean.ResourceBean resourceBean);

        void OnItemLongClick(View view, int position);
    }

}
