package com.example.sunhq.test.home_display;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunhq.test.R;
import com.example.sunhq.test.home_display.menu.DisplayUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

public class Home_display extends ActionBarActivity implements View.OnClickListener {



   /* *//*  设定当前menu的选中项  */
    private ChosedObject ChosedStyle = new ChosedObject("");

    private ChosedObject ChosedSpace = new ChosedObject("");

    private ChosedObject ChosedType = new ChosedObject("");

    private ChosedObject ChosedSize = new ChosedObject("");

    //以下是顶部图片横向滑动的定义
    ArrayList<String> PicList;
    GridView gridView;
    GridViewAdapter gridViewAdapter;
    GetImagePath getImagePath;
    private ImageView imageView;

    /*
    * 四个按钮的菜单项显示
    * */
    private ListViewAdapter mListViewAdapter;
    private ListView mPopListView;
    private List<String> mData=new ArrayList<>();
    Button style,space,size,type;  //家庭展示页面四个按钮
    Button style_back,space_back,size_back,type_back; //隐藏在那四个按钮后的四个叉

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_display);
        imageView = (ImageView) findViewById(R.id.logo_home_display);


        int resourceId = R.mipmap.logomax_nomargin;
        Picasso.with(this)
                .load(resourceId)
               // .placeholder(R.mipmap.ic_launcher)
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


        //主页面四个按钮 后 隐藏的按钮
        style_back = (Button) findViewById(R.id.style_back);
        space_back = (Button) findViewById(R.id.space_back);
        type_back = (Button) findViewById(R.id.type_back);
        size_back = (Button) findViewById(R.id.size_back);

        style_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChosedStyle = new ChosedObject("");  // 点击 该按钮将已选项置为空
                style_back.setVisibility(View.INVISIBLE);
                style.setText(Home_display.this.getResources().getString(R.string.style));
                strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};
                setValue();
            }
        });


        space_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChosedSpace = new ChosedObject("");
                strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};
                space_back.setVisibility(View.INVISIBLE);
                space.setText(Home_display.this.getResources().getString(R.string.space));
                setValue();
            }
        });

        type_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChosedType = new ChosedObject("");
                strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};
                type_back.setVisibility(View.INVISIBLE);
                type.setText(Home_display.this.getResources().getString(R.string.ceramic));
                setValue();
            }
        });

        size_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChosedSize = new ChosedObject("");
                strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};
                size_back.setVisibility(View.INVISIBLE);
                size.setText(Home_display.this.getResources().getString(R.string.format));
                setValue();
            }
        });

        //主页面四个按钮的点击事件
        style = (Button) findViewById(R.id.style);
        space = (Button) findViewById(R.id.space);
        size = (Button) findViewById(R.id.size);
        type = (Button) findViewById(R.id.type);

        if (style != null) {
            style.setOnClickListener(Home_display.this);
        }
        if (space != null) {
            space.setOnClickListener(Home_display.this);
        }
        if (size != null) {
            size.setOnClickListener(Home_display.this);
        }
        if (type != null) {
            type.setOnClickListener(Home_display.this);
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


    public void setValue(){
        if ((strings[0].chosed != null && !strings[0].chosed.equals("") )||
                ( strings[1].chosed != null && !strings[1].chosed.equals("")) ||
                ( strings[2].chosed != null && !strings[2].chosed.equals("")) ||
                ( strings[3].chosed != null && !strings[3].chosed.equals(""))) {
            getImagePath = new GetImagePath(strings);  //strings为输入查询的参数
            PicList = (ArrayList<String>) getImagePath.getImagePathFromSD();  //获取SD卡中相关图片的路径
            setGridView();
        }else{
            gridView.setAdapter(null);
        }

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


    //四个按钮的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.style:
                initDataStyle();
                showPopupWindowStyle();
                break;
            case R.id.space:
                initDataSpace();
                showPopupWindowSpace();
                break;
            case R.id.size:
                initDataSize();
                showPopupWindowSize();
                break;
            case R.id.type:
                initDataType();
                showPopupWindowType();
                break;
        }
    }

    /*************************************************1111111111111111111***********************************************************/
    private void initDataStyle() {
        mData.clear();  //每次点击按钮都要重置,不然会叠加显示
        mData.add(this.getResources().getString(R.string.Chinese_style));
        mData.add(this.getResources().getString(R.string.European_style));
        mData.add(this.getResources().getString(R.string.American_style));
        mData.add(this.getResources().getString(R.string.Modern_style));
        mData.add(this.getResources().getString(R.string.Industry_style));
    }
    private void showPopupWindowStyle() {
        View view = View.inflate(Home_display.this, R.layout.menu_popup_window, null);
        mPopListView = (ListView) view.findViewById(R.id.pop_list_view);

        PopupWindow popupWindow = new PopupWindow(view, DisplayUtils.dip2px(this, 50), //修改菜单宽度
                DisplayUtils.dip2px(this, 150), true);  //修改菜单的高度
      /*  PopupWindow popupWindow = new PopupWindow(view, 100, //修改菜单左右位置的
                200, true);  //修改菜单的高度*/
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update();
        popupWindow.showAsDropDown(style, DisplayUtils.dip2px(this, 20), 25);  //设置菜单的左右偏移量
        mListViewAdapter = new ListViewAdapter(this,mData,popupWindow);
        mPopListView.setAdapter(mListViewAdapter);
    }
    /**************************************************1111111111111111111111111**********************************************************/
    /**************************************************2222222222222222222222222**********************************************************/
    private void initDataSpace() {
        mData.clear();  //每次点击按钮都要重置,不然会叠加显示
        mData.add(this.getResources().getString(R.string.public_area));
        mData.add(this.getResources().getString(R.string.bedroom));
        mData.add(this.getResources().getString(R.string.toilet));
        mData.add(this.getResources().getString(R.string.kitchen));
        mData.add(this.getResources().getString(R.string.restaurant));
        mData.add(this.getResources().getString(R.string.entrance));
        mData.add(this.getResources().getString(R.string.aisle));
    }
    private void showPopupWindowSpace() {
        View view = View.inflate(Home_display.this, R.layout.menu_popup_window, null);
        mPopListView = (ListView) view.findViewById(R.id.pop_list_view);

        PopupWindow popupWindow = new PopupWindow(view, DisplayUtils.dip2px(this, 80), //修改菜单宽度
                DisplayUtils.dip2px(this, 150), true);  //修改菜单的高度
      /*  PopupWindow popupWindow = new PopupWindow(view, 100, //修改菜单左右位置的
                200, true);  //修改菜单的高度*/
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update();
        popupWindow.showAsDropDown(space, DisplayUtils.dip2px(this, 7), 25);  //设置菜单的左右偏移量
        mListViewAdapter = new ListViewAdapter(this,mData,popupWindow);
        mPopListView.setAdapter(mListViewAdapter);
    }
    /**********************************************2222222222222222222222222222**************************************************************/
    /**********************************************3333333333333333333333333333**************************************************************/
    private void initDataSize() {
        mData.clear();  //每次点击按钮都要重置,不然会叠加显示
        mData.add(this.getResources().getString(R.string.a_60A));
        //mData.add(this.getResources().getString(R.string.a_60G));
        mData.add(this.getResources().getString(R.string.a_80A));
        mData.add(this.getResources().getString(R.string.a_80AP));
        mData.add(this.getResources().getString(R.string.a_80B));
        mData.add(this.getResources().getString(R.string.a_80BP));
        mData.add(this.getResources().getString(R.string.a_80CP));

    }
    private void showPopupWindowSize() {
        View view = View.inflate(Home_display.this, R.layout.menu_popup_window, null);
        mPopListView = (ListView) view.findViewById(R.id.pop_list_view);

        PopupWindow popupWindow = new PopupWindow(view, DisplayUtils.dip2px(this, 50), //修改菜单宽度
                DisplayUtils.dip2px(this, 150), true);  //修改菜单的高度
      /*  PopupWindow popupWindow = new PopupWindow(view, 100, //修改菜单左右位置的
                200, true);  //修改菜单的高度*/
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update();
        popupWindow.showAsDropDown(size, DisplayUtils.dip2px(this, 20), 25);  //设置菜单的左右偏移量
        mListViewAdapter = new ListViewAdapter(this,mData,popupWindow);
        mPopListView.setAdapter(mListViewAdapter);
    }
    /******************************************33333333333333333333333333333333******************************************************************/
    /******************************************44444444444444444444444444444444******************************************************************/
    private void initDataType() {
        mData.clear();  //每次点击按钮都要重置,不然会叠加显示
        mData.add(this.getResources().getString(R.string.crema_marfil));
        mData.add(this.getResources().getString(R.string.golden_lvory));
        mData.add(this.getResources().getString(R.string.venice_rice_black));
        mData.add(this.getResources().getString(R.string.diamond_ti_meters));
        mData.add(this.getResources().getString(R.string.King_Kong_Valentino));
        mData.add(this.getResources().getString(R.string.Cloud_Bella_gold));
        mData.add(this.getResources().getString(R.string.Century_Beige));
        mData.add(this.getResources().getString(R.string.Cappuccino));
        mData.add(this.getResources().getString(R.string.Ultraman));
        mData.add(this.getResources().getString(R.string.Modern_wood_grain));
        mData.add(this.getResources().getString(R.string.Sophie_Te_Kim));
        mData.add(this.getResources().getString(R.string.Golden_butterfly));
        mData.add(this.getResources().getString(R.string.Milan_Chanti));
    }
    private void showPopupWindowType() {
        View view = View.inflate(Home_display.this, R.layout.menu_popup_window, null);
        mPopListView = (ListView) view.findViewById(R.id.pop_list_view);

        PopupWindow popupWindow = new PopupWindow(view, DisplayUtils.dip2px(this, 90), //修改菜单宽度
                DisplayUtils.dip2px(this, 150), true);  //修改菜单的高度
      /*  PopupWindow popupWindow = new PopupWindow(view, 100, //修改菜单左右位置的
                200, true);  //修改菜单的高度*/
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update();
        popupWindow.showAsDropDown(type, DisplayUtils.dip2px(this, 4), 25);  //设置菜单的左右偏移量
        mListViewAdapter = new ListViewAdapter(this,mData,popupWindow);
        mPopListView.setAdapter(mListViewAdapter);
    }
    /****************************************444444444444444444444444444444********************************************************************/


    /*
    * 菜单界面的ListView适配器    不要问我为什么不新建文件,又是对象又是字符串又是方法的两个文件传来传去太麻烦了
    *
    * */
    public class ListViewAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> mData;
        private PopupWindow mPopupWindow;



        public ListViewAdapter(Context context, List<String> data, PopupWindow popupWindow) {
            mContext = context;
            mData = data;
            mPopupWindow = popupWindow;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.menu_adapter_popup_list_view, null);
                viewHolder = new ViewHolder();
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(mData.get(position));
            viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //Toast.makeText(mContext,"你点击了"+mData.get(position), Toast.LENGTH_SHORT).show();
                    switch(mData.size()){     // 这个判断条件有点蠢,而且不具有普适性,但是目前来说能用
                        case 5:
                            ChosedStyle.chosed = mData.get(position);
                            style.setText(ChosedStyle.chosed);
                            style_back.setVisibility(View.VISIBLE);
                            break;
                        case 7:
                            ChosedSpace.chosed = mData.get(position);
                            space.setText(ChosedSpace.chosed);
                            space_back.setVisibility(View.VISIBLE);
                            break;
                        case 13:
                            ChosedType.chosed = mData.get(position);
                            type.setText(ChosedType.chosed);
                            type_back.setVisibility(View.VISIBLE);
                            break;
                        case 6:
                            ChosedSize.chosed = mData.get(position);
                            size.setText(ChosedSize.chosed);
                            size_back.setVisibility(View.VISIBLE);
                            break;
                    }
                    strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};
                    setValue();
                    mPopupWindow.dismiss();
                }
            });
            return convertView;
        }


         class ViewHolder {
            TextView mTextView;
        }
    }


}
