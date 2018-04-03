package com.example.lenovo.myapplication1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.myapplication1.R;
import com.example.lenovo.myapplication1.Model;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Model> nameArraylist;
    Context context;

    public Adapter(Context context, ArrayList<Model> nameArraylist) {
        this.context = context;
        this.nameArraylist = nameArraylist;
    }

    @Override
    public int getCount() {
        return nameArraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return nameArraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater inflater;
        if (view == null) {
            holder = new ViewHolder();
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_view_items, null);
            holder.name = (TextView) view.findViewById(R.id.textView);
            holder.url = (TextView) view.findViewById(R.id.textView1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(nameArraylist.get(i).getName());
        holder.url.setText(nameArraylist.get(i).getUrl());

        return view;
    }

    public class ViewHolder {
        TextView name, url;
    }
}