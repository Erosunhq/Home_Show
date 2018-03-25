/*
package com.example.sunhq.test;

*/
/**
 * Created by Sunhq on 2018/3/20.
 *//*

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyGridViewAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    Home_display home_display = new Home_display();

    public MyGridViewAdapter(Context _context, List<String> _list) {
        this.list = _list;
        this.context = _context;
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
        if (convertView == null){
            convertView = View.inflate(context,R.layout.home_display_gridview_item,null);
        }
        //ImageView imageView = (ImageView) convertView;
        Picasso.with(context)
                .load(new File(PicList.get(position)))
                .resize(200,200)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .noFade()
                .into((ImageView) convertView);
        return convertView;
    }
}
*/
