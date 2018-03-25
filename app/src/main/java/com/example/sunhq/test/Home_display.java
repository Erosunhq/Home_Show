package com.example.sunhq.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunhq on 2018/3/16.
 */
public class Home_display extends ActionBarActivity{

    String getPath_logo_home = Environment.getExternalStorageDirectory() + "/images/logomax_nomargin.png";
    private int checkedItemId = R.id.Chinese_style;   //当前选择的menu的ID
    ImageView imageView;

    //以下是顶部图片横向滑动的定义
    List<String> PicList;
    GridView gridView;
    GetImagePath getImagePath;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_display);
        imageView = (ImageView) findViewById(R.id.logo_home_display);

        Picasso.with(this)
                .load(new File(getPath_logo_home))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .tag("image")
                .into(imageView);


        //左下角返回按钮
        Button home_display_back = (Button) findViewById(R.id.home_display_back);
        home_display_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //顶部图片展示
        gridView = (GridView) findViewById(R.id.top_gridView);

        //主页面四个按钮的点击事件
        final Button style = (Button) findViewById(R.id.style);
        assert style != null;
        style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Home_display.this, style);  //style为第一个样式按钮
                popup.getMenuInflater().inflate(R.menu.display_style_menu, popup.getMenu());

                popup.getMenu().findItem(checkedItemId).setChecked(true);  //设置选中
                //给每个选项添加点击事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  //弹出菜单的按钮点击事件
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Chinese_style:
                                checkedItemId = R.id.Chinese_style;
                                set_Chinese_style_Value();
                                break;
                            case R.id.European_style:
                                checkedItemId = R.id.European_style;
                                set_European_style_Value();
                                break;
                            case R.id.American_style:
                                checkedItemId = R.id.American_style;
                                set_American_style_Value();
                                break;
                            case R.id.Modern_style:
                                checkedItemId = R.id.Modern_style;
                                set_Modern_style_Value();
                                break;
                            case R.id.Industry_style:
                                checkedItemId = R.id.Industry_style;
                                set_Industry_style_Value();
                                break;
                        }
                        return true;
                    }
                });
                //show
                popup.show();
            }
        });

        //图片选项的点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(Home_display.this,"这是第"+position+"张图片",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(Home_display.this,"这是第"+position+"张图片",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(Home_display.this,"这是第"+position+"张图片",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(Home_display.this,"这是第"+position+"张图片",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }



    //  menu子菜单的点击事件
    private void set_Chinese_style_Value() {
        getImagePath = new GetImagePath("中式");  //Chinese为输入查询的参数
        PicList = getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }

    private void set_European_style_Value(){
        getImagePath = new GetImagePath("欧式");  //European为输入查询的参数
        PicList = getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }

    private void set_American_style_Value() {
        getImagePath = new GetImagePath("美式");  //American为输入查询的参数
        PicList = getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }

    private void set_Modern_style_Value(){
        getImagePath = new GetImagePath("现代");  //Modern为输入查询的参数
        PicList = getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();

    }

    private void set_Industry_style_Value(){
        getImagePath = new GetImagePath("工业风");  //Industry为输入查询的参数
        PicList = getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();

    }


    private void setGridView() {
        int size = PicList.size();

        int length = 100;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density + (size - 1)*30);   //计算宽度时一定要加上图片之间的间隔距离,这里设置的是30
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 重点
        gridView.setColumnWidth(itemWidth); // 重点
        gridView.setHorizontalSpacing(30); // 间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 重点

        MyGridViewAdapter adapter = new MyGridViewAdapter(getApplicationContext(),
                PicList);
        gridView.setAdapter(adapter);
    }
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
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.home_display_gridview_item, null);
            }
            //ImageView imageView = (ImageView) convertView;
            Picasso.with(context)
                    .load(new File(PicList.get(position)))
                    .resize(200, 200)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .noFade()
                    .into((ImageView) convertView);
            return convertView;
        }
    }
}
