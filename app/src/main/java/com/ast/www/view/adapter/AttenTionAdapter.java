package com.ast.www.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.bean.AttenTionBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/1.
 */

public class AttenTionAdapter extends RecyclerView.Adapter {
    private ArrayList<AttenTionBean> list;
    private Context context;

    public AttenTionAdapter(ArrayList<AttenTionBean> list, Context context,OnItemClickLitener mOnItemClickLitener) {
        this.list = list;
        this.context = context;
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_attention, null);
        ViewHolder viewHolder=new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
           ViewHolder vi= (ViewHolder) holder;
           Uri uri = Uri.parse(list.get(0).getUser().get(position).getUserHead());
           vi.it_attention_image.setImageURI(uri);
           vi.it_attention_text1.setText(list.get(0).getUser().get(position).getUserName());
           vi.it_attention_text2.setText(list.get(0).getUser().get(position).getUserPhone());
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.get(0).getUser().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView it_attention_image;
        private final TextView it_attention_text1;
        private final TextView it_attention_text2;

        public ViewHolder(View itemView) {
            super(itemView);
            it_attention_image = (SimpleDraweeView) itemView.findViewById(R.id.it_attention_image);
            it_attention_text1 = (TextView) itemView.findViewById(R.id.it_attention_text1);
            it_attention_text2 = (TextView) itemView.findViewById(R.id.it_attention_text2);
        }
    }

}
