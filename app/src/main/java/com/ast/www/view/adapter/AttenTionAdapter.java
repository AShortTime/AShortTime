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

    public AttenTionAdapter(ArrayList<AttenTionBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_attention, null);
        ViewHolder viewHolder=new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
           ViewHolder vi= (ViewHolder) holder;
           Uri uri = Uri.parse(list.get(0).getUser().get(position).getUserHead());
           vi.it_attention_image.setImageURI(uri);
           vi.it_attention_text1.setText(list.get(0).getUser().get(position).getUserName());
           vi.it_attention_text2.setText(list.get(0).getUser().get(position).getUserPhone());
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
