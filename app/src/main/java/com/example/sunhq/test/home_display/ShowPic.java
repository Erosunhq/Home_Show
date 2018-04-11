package com.example.sunhq.test.home_display;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sunhq.test.R;

import java.util.ArrayList;

/**
 * Created by Sunhq on 2018/3/29.
 */
public class ShowPic extends AppCompatActivity {

    private ViewPager mViewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.zoomshow);
        Button back = (Button) findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        //接受传过来的信息
        Bundle bundle = getIntent().getExtras();

        final ArrayList<String> imagePathListArray = bundle.getStringArrayList("PicListArray");
        final ImageView[] mImageViews = new ImageView[imagePathListArray.size()];
        int currentItem = bundle.getInt("SelectedItem",0);
        //Toast.makeText(ShowPic.this,"imagePathListArray = "+imagePathListArray,Toast.LENGTH_SHORT).show();

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ZoomImageView imageView = new ZoomImageView(getApplicationContext());

                imageView.setImageURI(Uri.parse(imagePathListArray.get(position)));
                container.addView(imageView);
                mImageViews[position] = imageView;
                return imageView;
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mImageViews[position]);
            }
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return mImageViews.length;
            }
        });

           mViewPager.setCurrentItem(currentItem); //初始化  刚开始选中的哪张图片,一定要放在这个位置
    }
}
