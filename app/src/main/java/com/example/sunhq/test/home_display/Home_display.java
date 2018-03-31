package com.example.sunhq.test.home_display;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sunhq.test.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sunhq on 2018/3/16.
 */

//新建此对象的主要作用就是将已选中变量可以被赋值为按钮选中的选项
class ChosedObject {
    public String chosed = "";

    public ChosedObject(String chosed) {
        this.chosed = chosed;
    }

    @Override
    public String toString() {
        return chosed;
    }
}

public class Home_display extends ActionBarActivity {

    String getPath_logo_home = Environment.getExternalStorageDirectory() + "/images/logomax_nomargin.png";


    /*  设定当前menu的选中项  */
    private int checkedItemIdStyle = R.id.Chinese_style;   //当前选择的menu的ID
    private ChosedObject ChosedStyle = new ChosedObject("");
    //private String ChosedStyle = "";  //设置默认选项

    private int checkedItemIdSpace = R.id.public_area;
    private ChosedObject ChosedSpace = new ChosedObject("");
    //private String ChosedSpace = "";

    private int checkedItemIdType = R.id.crema_marfil;
    private ChosedObject ChosedType = new ChosedObject("");
    //private String ChosedType = "";

    private int checkedItemIdSize = R.id.a_60A;
    private ChosedObject ChosedSize = new ChosedObject("");
    //private String ChosedSize = "";

    //以下是顶部图片横向滑动的定义
    ArrayList<String> PicList;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    GetImagePath getImagePath;
    private ImageView imageView;

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
        final Button home_display_back = (Button) findViewById(R.id.home_display_back);
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
        final Button space = (Button) findViewById(R.id.space);
        final Button size = (Button) findViewById(R.id.size);
        final Button type = (Button) findViewById(R.id.type);


        /*风格按钮的点击事件*/
        if (style != null) {
            style.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PopupMenu popup = new PopupMenu(Home_display.this, style);  //style为第一个样式按钮
                    popup.getMenuInflater().inflate(R.menu.display_style_menu, popup.getMenu());

                    popup.getMenu().findItem(checkedItemIdStyle).setChecked(true);  //设置选中
                    //给每个选项添加点击事件
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  //弹出菜单的按钮点击事件
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {

                                case R.id.Chinese_style:
                                    ChosedStyle.chosed = getResources().getString(R.string.Chinese_style);
                                    checkedItemIdStyle = R.id.Chinese_style;
                                    style.setText(ChosedStyle.chosed);
                                    setStyleValue();
                                    break;
                                case R.id.European_style:
                                    ChosedStyle.chosed = getResources().getString(R.string.European_style);
                                    checkedItemIdStyle = R.id.European_style;
                                    style.setText(ChosedStyle.chosed);
                                    setStyleValue();
                                    break;
                                case R.id.American_style:
                                    ChosedStyle.chosed = getResources().getString(R.string.American_style);
                                    checkedItemIdStyle = R.id.American_style;
                                    style.setText(ChosedStyle.chosed);
                                    setStyleValue();
                                    break;
                                case R.id.Modern_style:
                                    ChosedStyle.chosed = getResources().getString(R.string.Modern_style);
                                    checkedItemIdStyle = R.id.Modern_style;
                                    style.setText(ChosedStyle.chosed);
                                    setStyleValue();
                                    break;
                                case R.id.Industry_style:
                                    ChosedStyle.chosed = getResources().getString(R.string.Industry_style);
                                    checkedItemIdStyle = R.id.Industry_style;
                                    style.setText(ChosedStyle.chosed);
                                    setStyleValue();
                                    break;
                            }
                            return true;
                        }
                    });
                    //show
                    popup.show();
                }
            });
        }

        /* 空间按钮的点击事件 */
        if (space != null) {
            space.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(Home_display.this, space);  //space为第二个样式按钮
                    popup.getMenuInflater().inflate(R.menu.display_space_menu, popup.getMenu());

                    popup.getMenu().findItem(checkedItemIdSpace).setChecked(true);  //设置选中
                    //给每个选项添加点击事件
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.public_area:
                                    ChosedSpace.chosed = getResources().getString(R.string.public_area);
                                    checkedItemIdSpace = R.id.public_area;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                                case R.id.bedroom:
                                    ChosedSpace.chosed = getResources().getString(R.string.bedroom);
                                    checkedItemIdSpace = R.id.bedroom;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                                case R.id.toilet:
                                    ChosedSpace.chosed = getResources().getString(R.string.toilet);
                                    checkedItemIdSpace = R.id.toilet;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                                case R.id.kitchen:
                                    ChosedSpace.chosed = getResources().getString(R.string.kitchen);
                                    checkedItemIdSpace = R.id.kitchen;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                                case R.id.restaurant:
                                    ChosedSpace.chosed = getResources().getString(R.string.restaurant);
                                    checkedItemIdSpace = R.id.restaurant;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                                case R.id.entrance:
                                    ChosedSpace.chosed = getResources().getString(R.string.entrance);
                                    checkedItemIdSpace = R.id.entrance;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                                case R.id.aisle:
                                    ChosedSpace.chosed = getResources().getString(R.string.aisle);
                                    checkedItemIdSpace = R.id.aisle;
                                    space.setText(ChosedSpace.chosed);
                                    setSpaceValue();
                                    break;
                            }
                            return true;
                        }
                    });
                    popup.show();
                }
            });
        }

        /* 瓷砖按钮的点击事件 */
        if (type != null) {
            type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(Home_display.this, type);  //type为第三个样式按钮
                    popup.getMenuInflater().inflate(R.menu.display_type_menu, popup.getMenu());

                    popup.getMenu().findItem(checkedItemIdType).setChecked(true);  //设置选中
                    //给每个选项添加点击事件
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.crema_marfil:
                                    ChosedType.chosed = getResources().getString(R.string.crema_marfil);
                                    //Toast.makeText(Home_display.this,ChosedType,Toast.LENGTH_SHORT).show();
                                    checkedItemIdType = R.id.crema_marfil;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.golden_lvory:
                                    ChosedType.chosed = getResources().getString(R.string.golden_lvory);
                                    checkedItemIdType = R.id.golden_lvory;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.venice_rice_black:
                                    ChosedType.chosed = getResources().getString(R.string.venice_rice_black);
                                    checkedItemIdType = R.id.venice_rice_black;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.diamond_ti_meters:
                                    ChosedType.chosed = getResources().getString(R.string.diamond_ti_meters);
                                    checkedItemIdType = R.id.diamond_ti_meters;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.King_Kong_Valentino:
                                    ChosedType.chosed = getResources().getString(R.string.King_Kong_Valentino);
                                    checkedItemIdType = R.id.King_Kong_Valentino;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Cloud_Bella_gold:
                                    ChosedType.chosed = getResources().getString(R.string.Cloud_Bella_gold);
                                    checkedItemIdType = R.id.Cloud_Bella_gold;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Century_Beige:
                                    ChosedType.chosed = getResources().getString(R.string.Century_Beige);
                                    checkedItemIdType = R.id.Century_Beige;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Cappuccino:
                                    ChosedType.chosed = getResources().getString(R.string.Cappuccino);
                                    checkedItemIdType = R.id.Cappuccino;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Ultraman:
                                    ChosedType.chosed = getResources().getString(R.string.Ultraman);
                                    checkedItemIdType = R.id.Ultraman;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Modern_wood_grain:
                                    ChosedType.chosed = getResources().getString(R.string.Modern_wood_grain);
                                    checkedItemIdType = R.id.Modern_wood_grain;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Sophie_Te_Kim:
                                    ChosedType.chosed = getResources().getString(R.string.Sophie_Te_Kim);
                                    checkedItemIdType = R.id.Sophie_Te_Kim;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Golden_butterfly:
                                    ChosedType.chosed = getResources().getString(R.string.Golden_butterfly);
                                    checkedItemIdType = R.id.Golden_butterfly;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                                case R.id.Milan_Chanti:
                                    ChosedType.chosed = getResources().getString(R.string.Milan_Chanti);
                                    checkedItemIdType = R.id.Milan_Chanti;
                                    type.setText(ChosedType.chosed);
                                    setTypeValue();
                                    break;
                            }
                            return true;
                        }
                    });
                    popup.show();
                }
            });
        }

        /* 规格按钮的点击事件 */
        if (size != null) {
            size.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(Home_display.this, size);  //size为第四个样式按钮
                    popup.getMenuInflater().inflate(R.menu.display_size_menu, popup.getMenu());

                    popup.getMenu().findItem(checkedItemIdSize).setChecked(true);  //设置选中
                    //给每个选项添加点击事件
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.a_60A:
                                    ChosedSize.chosed = getResources().getString(R.string.a_60A);
                                    //Toast.makeText(Home_display.this,ChosedSize,Toast.LENGTH_SHORT).show();
                                    checkedItemIdSize = R.id.a_60A;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                                case R.id.a_60G:
                                    ChosedSize.chosed = getResources().getString(R.string.a_60G);
                                    checkedItemIdSize = R.id.a_60G;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                                case R.id.a_80A:
                                    ChosedSize.chosed = getResources().getString(R.string.a_80A);
                                    checkedItemIdSize = R.id.a_80A;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                                case R.id.a_80B:
                                    ChosedSize.chosed = getResources().getString(R.string.a_80B);
                                    checkedItemIdSize = R.id.a_80B;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                                case R.id.a_80AP:
                                    ChosedSize.chosed = getResources().getString(R.string.a_80AP);
                                    checkedItemIdSize = R.id.a_80AP;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                                case R.id.a_80BP:
                                    ChosedSize.chosed = getResources().getString(R.string.a_80BP);
                                    checkedItemIdSize = R.id.a_80BP;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                                case R.id.a_80CP:
                                    ChosedSize.chosed = getResources().getString(R.string.a_80CP);
                                    checkedItemIdSize = R.id.a_80CP;
                                    size.setText(ChosedSize.chosed);
                                    setSizeValue();
                                    break;
                            }
                            return true;
                        }
                    });
                    popup.show();
                }
            });
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                /*
                * 将PicList封装传递给ShowPic中的Viewpager
                *
                * */
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("PicListArray", PicList);
                bundle.putStringArray("PicList", new String[]{PicList.get(position)});
                bundle.putInt("SelectedItem", position);
                Intent intent = new Intent(Home_display.this, ShowPic.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    //与上面封装的对象对应
    ChosedObject[] strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};

    //  menu子菜单的点击事件
    private void setStyleValue() {
        getImagePath = new GetImagePath(strings);  //ChosedStyle为输入查询的参数
        PicList = (ArrayList<String>) getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }

    private void setSpaceValue() {
        System.out.println(Arrays.toString(strings));
        getImagePath = new GetImagePath(strings);  //ChosedSpace为输入查询的参数
        System.out.println(Arrays.toString(strings));
        PicList = (ArrayList<String>) getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }

    private void setTypeValue() {
        System.out.println(Arrays.toString(strings));
        getImagePath = new GetImagePath(strings);  //ChosedType为输入查询的参数
        System.out.println(Arrays.toString(strings));
        PicList = (ArrayList<String>) getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }

    private void setSizeValue() {
        System.out.println(Arrays.toString(strings));
        getImagePath = new GetImagePath(strings);  //ChosedSize为输入查询的参数
        System.out.println(Arrays.toString(strings));
        PicList = (ArrayList<String>) getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
        setGridView();
    }


    private void setGridView() {
        int size = PicList.size();

        int length = 100;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density + (size - 1) * 10);   //计算宽度时一定要加上图片之间的间隔距离,这里设置的是30
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 重点
        gridView.setColumnWidth(itemWidth); // 重点
        gridView.setHorizontalSpacing(10); // 间距   重点修改********************************
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 重点

        gridViewAdapter = new GridViewAdapter(getApplicationContext(), PicList);
        gridView.setAdapter(gridViewAdapter);

    }

}
