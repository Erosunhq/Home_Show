package com.example.sunhq.test.home_display;

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
import android.widget.TextView;

import com.example.sunhq.test.R;
import com.example.sunhq.test.home_display.sliding_effect.ZoomOutPageTransformer;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Sunhq on 2018/3/29.
 */
public class ShowPic extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView currentPage;
    ZoomImageView imageView;

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
        final int currentItem = bundle.getInt("SelectedItem",0);
        //Toast.makeText(ShowPic.this,"imagePathListArray = "+imagePathListArray,Toast.LENGTH_SHORT).show();

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        /*
        * 显示页码的TextView
        * */
        currentPage = (TextView) findViewById(R.id.currentPage);
        /*
        * 设置页码
        * 当前页码  /  总共页码
        * */
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                int currentPosition = position;
                currentPage.setText(currentPosition+1 +" / "+ mImageViews.length);
                currentPage.setTextColor(0xffc1966c);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /***********************************************************************************************/
        //设置图片切换时的动画效果(下面是两种效果,任选一种)
        //mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        /****************************************************************/
        mViewPager.setAdapter(new PagerAdapter() {
            // 一屏显示多张图片
            /*public float getPageWidth(int position) {
                return (float) 0.6;
            }
*/
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                /****************************************************************/

                imageView = new ZoomImageView(getApplicationContext());

                /*
                * 能不能用Picasso来实现显示大图
                * */
                Picasso.with(ShowPic.this)
                        .load(new File(imagePathListArray.get(position)))
                        .fit()
                        .error(R.mipmap.error)

                        .centerInside()
                        .into(imageView);
                /*
                * 下面这容易报OOM的错
                *
                * */
                /*imageView.setImageURI(Uri.parse(imagePathListArray.get(position)));*/
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
