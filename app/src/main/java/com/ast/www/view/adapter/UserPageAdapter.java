package com.ast.www.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ast.www.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/10.
 */

public class UserPageAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> list;

    public UserPageAdapter(Context context,ArrayList<String> list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHorde viewHorde;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.it_userpage_2, null);
            viewHorde=new ViewHorde();
            viewHorde.textView= (TextView) convertView.findViewById(R.id.userpage_2_text);
            convertView.setTag(viewHorde);
        }else{
            viewHorde= (ViewHorde) convertView.getTag();
        }
        viewHorde.textView.setText(list.get(position));
        return convertView;
    }
    class ViewHorde {
        TextView textView;
    }
}
