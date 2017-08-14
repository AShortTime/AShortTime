package com.ast.www.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.constom.Userinfoview;
import com.ast.www.folding.Util;
import com.ast.www.model.bean.RecommendHotBean;
import com.ast.www.model.util.Utils;
import com.ast.www.presenter.HomePresenter;
import com.ast.www.view.iview.IBaseView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ast.www.R.mipmap.detailfoot;
import static com.ast.www.R.mipmap.detailtop;

/**
 * 作者:郭凯奇
 * 时间: 2017/7/28 10:52
 * Title:
 * Text:
 */

public class RlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //图片类型
    public final static int TYPE_IMAGE = 3;
    //视频类型
    public final static int TYPE_VIDEO = 1;

    public final static int TYPE_JOKE = 2;

    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<RecommendHotBean.ResourceBean> list;

    public RlvAdapter(Context context) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        switch (getItemViewType(position)) {
            case TYPE_IMAGE: {
                onItemEventClick(holder);
                final ImageHolder imageh = (ImageHolder) holder;
//                设置用户信息
                imageh.userinfoview.setUsername(list.get(position).getUser().getUserName());
                imageh.userinfoview.setTime(list.get(position).getUptime());
                if (!TextUtils.isEmpty(list.get(position).getUser().getUserHead())) {
                    imageh.userinfoview.setUserhead(list.get(position).getUser().getUserHead());
                }
                //设置内容
                imageh.itemtext.setText(list.get(position).getDescription());
                imageh.image.setImageURI(list.get(position).getSrc());
                imageh.initListiner(position);
                //设置神评
                if (list.get(position).getCommentList().size() == 0) {
                    imageh.itemView.findViewById(R.id.item_god).setVisibility(View.GONE);
                } else {
                    imageh.itemView.findViewById(R.id.item_god).setVisibility(View.VISIBLE);
                    imageh.godusername.setText(list.get(position).getCommentList().get(0).getUser().getUserName());
                    imageh.godcontext.setText(list.get(position).getCommentList().get(0).getCommentContent());
                }

                //设置赞踩评论
                if(!list.get(position).isnice()&&!list.get(position).isbade()){
                    imageh.topnum.setEnabled(true);
                    imageh.footnum.setEnabled(true);
                }

                imageh.topnum.setText(list.get(position).getNiceNum() + "");
                imageh.footnum.setText(list.get(position).getBadNum() + "");
                imageh.topnum.setChecked(list.get(position).isnice());
                imageh.footnum.setChecked(list.get(position).isbade());
                imageh.tweetnum.setText(list.get(position).getCommentNum() + "");
                imageh.topnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(position).setIsnice(true);
                        HomePresenter hp = new HomePresenter();
                        HashMap<String, String> map = new HashMap<>();
                        String niceKey = list.get(position).getNiceKey();
                        Log.e("redisPictureNiceKey ", niceKey);
                        map.put("redisPictureNiceKey", niceKey);
                        hp.get("picture/AddNice", map, null);
                        list.get(position).setNiceNum(list.get(position).getNiceNum() + 1);
                        imageh.topnum.setText(list.get(position).getNiceNum() + "");
                        imageh.topnum.setEnabled(false);
                        imageh.footnum.setEnabled(false);
                    }
                });
                imageh.footnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(position).setIsbade(true);
                        HomePresenter hp = new HomePresenter();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("redisPictureBadKey", list.get(position).getBadKey());
                        hp.get("picture/AddBad", map, null);
                        list.get(position).setBadNum(list.get(position).getBadNum() + 1);
                        imageh.footnum.setText(list.get(position).getBadNum() + "");
                        imageh.topnum.setEnabled(false);
                        imageh.footnum.setEnabled(false);

                    }
                });
            }

            //废弃点赞
//                if (imageh.topnum.isEnabled() | imageh.footnum.isEnabled()) {
//                    imageh.topfootrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                            switch (checkedId) {
//                                //赞
//                                case R.id.top_num: {
//                                    list.get(position).setIsnice(true);
//                                    HomePresenter hp = new HomePresenter();
//                                    HashMap<String, String> map = new HashMap<>();
//                                    String niceKey = list.get(position).getNiceKey();
//                                    Log.e("nincekey", niceKey);
//                                    map.put("nicekey", niceKey);
//                                    hp.get("picture/AddNice", map, null);
//                                    list.get(position).setNiceNum(list.get(position).getNiceNum() + 1);
//                                    imageh.topnum.setText(list.get(position).getNiceNum() + "");
//                                    imageh.topnum.setEnabled(false);
//                                    imageh.footnum.setEnabled(false);
//                                    imageh.topfootrg.setOnCheckedChangeListener(null);
//                                }
//                                break;
//                                //踩
//                                case R.id.foot_num: {
//                                    list.get(position).setIsbade(true);
//                                    HomePresenter hp = new HomePresenter();
//                                    HashMap<String, String> map = new HashMap<>();
//                                    map.put("badkey", list.get(position).getBadKey());
//                                    hp.get("picture/AddBad", map, null);
//                                    list.get(position).setBadNum(list.get(position).getBadNum() + 1);
//                                    imageh.footnum.setText(list.get(position).getBadNum() + "");
//                                    imageh.topnum.setEnabled(false);
//                                    imageh.footnum.setEnabled(false);
//                                    imageh.topfootrg.setOnCheckedChangeListener(null);
//                                }
//                                break;
//                            }
//                        }
//                    });
//                }

            break;
            case TYPE_VIDEO: {
                onItemEventClick(holder);
                final VideoHolder vh = (VideoHolder) holder;

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
                vh.update(position, src);
                vh.initListiner(position);

                //设置神评
                if (list.get(position).getCommentList().size() == 0) {
                    vh.itemView.findViewById(R.id.item_god).setVisibility(View.GONE);
                } else {
                    vh.itemView.findViewById(R.id.item_god).setVisibility(View.VISIBLE);
                    vh.godusername.setText(list.get(position).getCommentList().get(0).getUser().getUserName());
                    vh.godcontext.setText(list.get(position).getCommentList().get(0).getCommentContent());
                }

                //设置赞踩评论
                if(!list.get(position).isnice()&&!list.get(position).isbade()){
                    vh.topnum.setEnabled(true);
                    vh.footnum.setEnabled(true);
                }
                vh.topnum.setText(list.get(position).getNiceNum() + "");
                vh.footnum.setText(list.get(position).getBadNum() + "");
                vh.topnum.setChecked(list.get(position).isnice());
                vh.footnum.setChecked(list.get(position).isbade());
                vh.tweetnum.setText(list.get(position).getCommentNum() + "");
                vh.topnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(position).setIsnice(true);
                        HomePresenter hp = new HomePresenter();
                        HashMap<String, String> map = new HashMap<>();
                        String niceKey = list.get(position).getNiceKey();
                        Log.e("nincekey", niceKey);
                        map.put("nicekey", niceKey);
                        hp.get("media/addNice", map, null);
                        list.get(position).setNiceNum(list.get(position).getNiceNum() + 1);
                        vh.topnum.setText(list.get(position).getNiceNum() + "");
                        vh.topnum.setEnabled(false);
                        vh.footnum.setEnabled(false);
                    }
                });
                vh.footnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(position).setIsbade(true);
                        HomePresenter hp = new HomePresenter();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("badkey", list.get(position).getBadKey());
                        hp.get("media/addBad", map, null);
                        list.get(position).setBadNum(list.get(position).getBadNum() + 1);
                        vh.footnum.setText(list.get(position).getBadNum() + "");
                        vh.topnum.setEnabled(false);
                        vh.footnum.setEnabled(false);

                    }
                });
            }
            break;
            case TYPE_JOKE: {
                onItemEventClick(holder);
                final ImageHolder jokeh = (ImageHolder) holder;
//                设置用户信息
                jokeh.userinfoview.setUsername(list.get(position).getUser().getUserName());
                jokeh.userinfoview.setTime(list.get(position).getUptime());
                if (!TextUtils.isEmpty(list.get(position).getUser().getUserHead())) {
                    jokeh.userinfoview.setUserhead(list.get(position).getUser().getUserHead());
                }

//                设置内容
                jokeh.image.setVisibility(View.GONE);
                jokeh.itemtext.setText(list.get(position).getContent());
                jokeh.initListiner(position);
                //设置神评
                if (list.get(position).getCommentList().size() == 0) {
                    jokeh.itemView.findViewById(R.id.item_god).setVisibility(View.GONE);
                } else {
                    jokeh.itemView.findViewById(R.id.item_god).setVisibility(View.VISIBLE);
                    jokeh.godusername.setText(list.get(position).getCommentList().get(0).getUser().getUserName());
                    jokeh.godcontext.setText(list.get(position).getCommentList().get(0).getCommentContent());
                }
                //设置赞踩评论
                if(!list.get(position).isnice()&&!list.get(position).isbade()){
                    jokeh.topnum.setEnabled(true);
                    jokeh.footnum.setEnabled(true);
                }
                jokeh.topnum.setText(list.get(position).getNiceNum() + "");
                jokeh.footnum.setText(list.get(position).getBadNum() + "");
                jokeh.topnum.setChecked(list.get(position).isnice());
                jokeh.footnum.setChecked(list.get(position).isbade());
                jokeh.tweetnum.setText(list.get(position).getCommentNum() + "");
                jokeh.topnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(position).setIsnice(true);
                        HomePresenter hp = new HomePresenter();
                        HashMap<String, String> map = new HashMap<>();
                        String niceKey = list.get(position).getNiceKey();
                        Log.e("nincekey", niceKey);
                        map.put("nicekey", niceKey);
                        hp.get("character/AddNice", map, null);
                        list.get(position).setNiceNum(list.get(position).getNiceNum() + 1);
                        jokeh.topnum.setText(list.get(position).getNiceNum() + "");
                        jokeh.topnum.setEnabled(false);
                        jokeh.footnum.setEnabled(false);
                    }
                });
                jokeh.footnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(position).setIsbade(true);
                        HomePresenter hp = new HomePresenter();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("badkey", list.get(position).getBadKey());
                        hp.get("character/AddBad", map, null);
                        list.get(position).setBadNum(list.get(position).getBadNum() + 1);
                        jokeh.footnum.setText(list.get(position).getBadNum() + "");
                        jokeh.topnum.setEnabled(false);
                        jokeh.footnum.setEnabled(false);
                    }
                });
                break;
            }
        }
    }

    protected void onItemEventClick(RecyclerView.ViewHolder holder) {
        final int position = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendHotBean.ResourceBean resourceBean = list.get(position);
                onItemClickListener.OnItemClick(v, position, resourceBean);
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
        public SimpleDraweeView image;

        //悬浮按钮
        FloatingActionButton mshare;
        FloatingActionButton mfavorite;

        View itemView;
        TextView godusername, godcontext;


        CheckBox topnum, footnum;
        TextView tweetnum;

        public ImageHolder(View itemView) {
            super(itemView);
            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.joke_user_info);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);
            this.image = (SimpleDraweeView) itemView.findViewById(R.id.joke_image);
            this.mshare = (FloatingActionButton) itemView.findViewById(R.id.share);
            this.mfavorite = (FloatingActionButton) itemView.findViewById(R.id.favorite);

            //初始化神评控件
            this.itemView = itemView;
            this.godusername = (TextView) itemView.findViewById(R.id.god_user_name);

            this.godcontext = (TextView) itemView.findViewById(R.id.god_context);
            //初始化赞踩评论控件
            this.topnum = (CheckBox) itemView.findViewById(R.id.top_num);
            this.footnum = (CheckBox) itemView.findViewById(R.id.foot_num);
            this.tweetnum = (TextView) itemView.findViewById(R.id.tweet_num);
        }
        public void initListiner(final int postion) {
            mshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("分享__RlvAdapter", "分享" + postion);
                }
            });
            mfavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomePresenter hp = new HomePresenter();
                    SharedPreferences sp = Utils.getSharedPrefers(context);
                    String userId = sp.getString("userId","0");
                    if (!TextUtils.isEmpty(userId)){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("collectionUserId",userId);
                        map.put("collectionCommentId", list.get(postion).getId()+"");
                        map.put("collectionDictionaryValue", list.get(postion).getDictionaryValue());
                        hp.post("user/addCollection", map, null);
                    }
                }
            });

        }

    }


    class VideoHolder extends RecyclerView.ViewHolder {
        public Userinfoview userinfoview;
        public TextView itemtext;
        public RelativeLayout rlayPlayerControl;

        //悬浮按钮
        FloatingActionButton mshare;
        FloatingActionButton mfavorite;
        //
        View itemView;
        TextView godusername, godcontext;
        //赞踩评论
        CheckBox topnum, footnum;
        TextView tweetnum;

        public VideoHolder(View itemView) {
            super(itemView);
            this.mshare = (FloatingActionButton) itemView.findViewById(R.id.share);
            this.mfavorite = (FloatingActionButton) itemView.findViewById(R.id.favorite);


            this.userinfoview = (Userinfoview) itemView.findViewById(R.id.video_user_info);
            this.itemtext = (TextView) itemView.findViewById(R.id.content);
            this.rlayPlayerControl = (RelativeLayout) itemView.findViewById(R.id.adapter_player_control);

            //初始化神评控件
            this.itemView = itemView;
            this.godusername = (TextView) itemView.findViewById(R.id.god_user_name);
            this.godcontext = (TextView) itemView.findViewById(R.id.god_context);
            //初始化赞踩评论控件
            this.topnum = (CheckBox) itemView.findViewById(R.id.top_num);
            this.footnum = (CheckBox) itemView.findViewById(R.id.foot_num);
            this.tweetnum = (TextView) itemView.findViewById(R.id.tweet_num);
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
            mfavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomePresenter hp = new HomePresenter();
                    SharedPreferences sp = Utils.getSharedPrefers(context);
                    String userId = sp.getString("userId","");
                    if (!TextUtils.isEmpty(userId)){
                        HashMap<String, String> map = new HashMap<>();
                        map.put("collectionUserId",userId);
                        map.put("collectionCommentId", list.get(postion).getId()+"");
                        map.put("collectionDictionaryValue", list.get(postion).getDictionaryValue());
                        hp.post("user/addCollection", map, null);
                    }

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
