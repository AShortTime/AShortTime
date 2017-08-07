package com.ast.www.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ast.www.R;
import com.ast.www.model.bean.SearchForBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/2.
 */

public class SearchForAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SearchForBean> list;

    public SearchForAdapter(Context context, ArrayList<SearchForBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.get(0).getUser().size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(0).getUser().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_scf_searchfor, null);
            viewHolder=new ViewHolder();
            viewHolder.it_attention_image= (SimpleDraweeView) convertView.findViewById(R.id.it_attention_image);
            viewHolder.it_searchfor_text1= (TextView) convertView.findViewById(R.id.it_searchfor_text1);
            viewHolder.it_searchfor_text2= (TextView) convertView.findViewById(R.id.it_searchfor_text2);
            viewHolder.it_searchfor_but_guanzhu= (Button) convertView.findViewById(R.id.it_searchfor_but_guanzhu);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
//        if(list.get(0).getUser().get(position).getUserSignature().length()>0) {
//            viewHolder.it_attention_image.setImageURI(list.get(0).getUser().get(position).getUserSignature());
//        }
        viewHolder.it_searchfor_text1.setText(list.get(0).getUser().get(position).getUserName());
        viewHolder.it_searchfor_text2.setText(list.get(0).getUser().get(position).getUserSignature());
        viewHolder.it_searchfor_but_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
    static class ViewHolder {
        SimpleDraweeView it_attention_image;
        TextView it_searchfor_text1;
        TextView it_searchfor_text2;
        Button it_searchfor_but_guanzhu;
    }
}
