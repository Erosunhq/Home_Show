package com.example.sunhq.test.home_display;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunhq on 2018/3/23.
 */
public class GetImagePath {

    private ChosedObject[] para;

    public GetImagePath(ChosedObject[] para){  //构造方法
        this.para = para;
    }
   /* public  void setPara(String para){
        this.para = para;
    }
    public String getPara(){
        return para;
    }
*/

    public List<String> getImagePathFromSD(){
        //图片列表
        List<String> imagePathList = new ArrayList<String>();
        // 得到sd卡内images文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "Picture/images";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        //将所有文件存入ArrayList中,并过滤出所有图片格式的文件
        for (int i = 0; i < files.length; i++){
            File file = files[i];
            if (checkIsImageFile(file.getName())){
                if (checkIsChosedFile(file.getName())){
                    imagePathList.add(file.getPath());//getPath() 获取文件路径,getName()获取文件名称
                }
            }
        }
        return imagePathList;
    }

    // 文件名称匹配
   /* private boolean checkIsChosedFile(String name){
        boolean isChosedFile = false;
        if (name.contains(para)){  //匹配相应的字符串, 包含
            return true;
        }

        return isChosedFile;
    }*/
    private boolean checkIsChosedFile(String name){
          for(int i = 0;i<para.length;i++){
              if (para[i].equals("") ){
                  continue;
              }
              if (!name.contains(para[i].chosed)){
                  return false;
              }
          }
        return true;
    }


    // 检查文件夹下文件的扩展名,得到图片格式的文件
    private boolean checkIsImageFile(String name) {
        boolean isImageFile = false;

        //获取扩展名   后缀名匹配
        String FileEnd = name.substring(name.lastIndexOf(".") + 1,name.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") ||FileEnd.equals("gif") ||
                FileEnd.equals("jpeg") ||FileEnd.equals("bmp")){
            isImageFile = true;
        }else {
            isImageFile = false;
        }

        return isImageFile;
    }

}
