package com.ast.www.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.constom.Userinfoview;
import com.ast.www.model.bean.DetailCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:郭凯奇
 * 时间: 2017/8/4 19:35
 * Title:
 * Text:
 */

public class DetailRlvadapter extends RecyclerView.Adapter<DetailRlvadapter.MyViewHolder> {

    //图片类型
    public final static int TYPE_GOD = 1;
    //视频类型
    public final static int TYPE_HOT = 2;

    public final static int TYPE_COMMENT = 3;


    private OnItemClickListener onItemClickListener;
    private Context context;
    List<DetailCommentBean.CommentBean> list;

    public void setList(List<DetailCommentBean.CommentBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public DetailRlvadapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == TYPE_GOD) {
            return TYPE_GOD;
        } else if (list.get(position).getType() == TYPE_HOT) {
            return TYPE_HOT;
        } else if (list.get(position).getType() == TYPE_COMMENT) {
            return TYPE_COMMENT;
        }
        return 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        MyViewHolder holder = null;
        view = LayoutInflater.from(context).inflate(R.layout.item_detail_comment, parent, false);
        holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_GOD:
            case TYPE_HOT:
                 onItemEventClick(holder);
                 holder.itemhot.setVisibility(View.VISIBLE);
                 holder.itemcomment.setText(list.get(position).getCommentContent());
                break;
            case TYPE_COMMENT:
                onItemEventClick(holder);
                holder.itemhot.setVisibility(View.GONE);
                holder.itemcomment.setText(list.get(position).getCommentContent());
                break;
        }
    }

    protected void onItemEventClick(RecyclerView.ViewHolder holder) {
        final int position = holder.getLayoutPosition();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position);
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


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemcomment;
        Userinfoview itemuser;
        ImageView itemhot;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemcomment = (TextView) itemView.findViewById(R.id.item_comment);
            itemuser = (Userinfoview) itemView.findViewById(R.id.item_user);
            itemhot = (ImageView) itemView.findViewById(R.id.item_hot);
        }

    }

}
