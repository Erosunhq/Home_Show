package com.example.sunhq.test.home_display;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.sunhq.test.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Sunhq on 2018/3/28.
 */
public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<String> PicList;

    public GridViewAdapter(Context _context, List<String> PicList) {
        this.PicList = PicList;
        this.context = _context;
    }

    @Override
    public int getCount() {
        return PicList.size();
    }

    @Override
    public Object getItem(int position) {
        return PicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.home_display_gridview_item,null);
        }
        //ImageView imageView = (ImageView) convertView;

        Picasso.with(context)
                .load(new File(PicList.get(position)))
                .resize(360,360)   //改变在GridView上的缩略图的大小,到一体机上待修改  重点修改!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                .centerCrop()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.ic_launcher)
                .noFade()
                .into((ImageView) convertView);

        return convertView;
    }
}
