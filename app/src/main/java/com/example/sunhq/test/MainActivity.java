package com.example.sunhq.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MainActivity extends AppCompatActivity {

    String getPath_logo = Environment.getExternalStorageDirectory() + "/images/logomax_nomargin.png";
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //这里要配合修改Manifest里的内容theme属性，因为这个Activity继承的包不一样
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View view = View.inflate(this, R.layout.activity_main,null);
        setContentView(view);

        imageView = (ImageView) findViewById(R.id.logo_home);
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                handler.sendMessage(message);
            }
        }).start();*/

        Picasso.with(this)
                .load(new File(getPath_logo))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .tag("image")
                .into(imageView);


        Button close_btn = (Button)findViewById(R.id.close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0); //完全退出本程序，finish()只是返回上一页
            }
        });

        Button home_decoration_display = (Button) findViewById(R.id.home_decoration_display);
        home_decoration_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Home_display.class);
                startActivity(intent);
            }
        });

    }

    /*//异步处理消息,将耗时的获取本地图片资源的操作放在子线程中操作
    private Handler handler = new Handler(){
      public void handleMessage(Message message){
          //从文件夹中获取图片资源并显示到页面
          Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/images/logomax_nomargin.png");
          //System.out.print(Environment.getExternalStorageDirectory());
          imageView.setImageBitmap(bitmap);
      }
    };*/
}
