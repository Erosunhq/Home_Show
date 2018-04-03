package com.example.sunhq.test.engineering_case;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sunhq.test.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunhq on 2018/3/30.
 */
public class Engineering_case extends AppCompatActivity {

    List<String> PicList = new ArrayList<String>();
    GetImagePath imagePath;

    private GridView gridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //这里要配合修改Manifest里的内容theme属性，因为这个Activity继承的包不一样
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.engineering_case);

        Button engineer_case_back = (Button) findViewById(R.id.engineering_case_back);
        if (engineer_case_back != null) {
            engineer_case_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }

        ImageView imageView = (ImageView) findViewById(R.id.logo_home);
        //加载屏幕左边的logo
        int resourceId = R.mipmap.logomax_nomargin;
        Picasso.with(this)
                .load(resourceId)
                //.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .tag("image")
                .into(imageView);

        /*
        *  中间几个按钮的点击事件
        * */

        gridView = (GridView) findViewById(R.id.gridView);

        Button technical_parameter1 = (Button) findViewById(R.id.technical_parameter1);
        if (technical_parameter1 != null) {
            technical_parameter1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imagePath = new GetImagePath("project"); // 这里依据传入的字符串不同,方法获得不同的文件夹,展示不同的图片
                    PicList = imagePath.getImagePathFromSD();
                    gridView.setAdapter(new ImageListAdapter(Engineering_case.this, PicList));
                }
            });
        }

        Button technical_parameter2 = (Button) findViewById(R.id.technical_parameter2);
        if (technical_parameter2 != null) {
            technical_parameter2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imagePath = new GetImagePath("project"); // 这里依据传入的字符串不同,方法获得不同的文件夹,展示不同的图片
                    PicList = imagePath.getImagePathFromSD();
                    gridView.setAdapter(new ImageListAdapter(Engineering_case.this, PicList));
                }
            });
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,"position =  "+position,Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("PicListArray", (ArrayList<String>) PicList);
                bundle.putStringArray("PicList", new String[]{PicList.get(position)});
                bundle.putInt("SelectedItem", position);
                Intent intent = new Intent(Engineering_case.this,ShowPic.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
        /**
         * 适配器
         */
        public class ImageListAdapter extends ArrayAdapter {
            private Context context;

            private List<String> PicList;

            public ImageListAdapter(Context context, List<String> PicList){
                super(context,R.layout.engineering_case_gridview_item,PicList);

                this.context = context;
                this.PicList = PicList;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView==null){
                    convertView = View.inflate(context,R.layout.engineering_case_gridview_item,null);
                }
                ImageView imageView = (ImageView)convertView;
                if (TextUtils.isEmpty(PicList.get(position))){
                    Picasso
                            .with(context)
                            .cancelRequest(imageView);
                    imageView.setImageDrawable(null);
                }else {
                    //加载图片
                    Picasso
                            .with(context)
                            .load(new File(PicList.get(position)))
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .resize(310,310)
                            .noFade()
                            .into((ImageView) convertView);
                }
                return convertView;
            }

    }
}
