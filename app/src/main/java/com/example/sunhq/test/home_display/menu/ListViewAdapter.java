/*
package com.example.sunhq.test.home_display.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunhq.test.R;
import com.example.sunhq.test.home_display.Home_display;

import java.util.List;

*/
/**
 * Created by Kevin on 2017/2/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 *//*



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
                //ToastUtils.showToast(mContext, mData.get(position));
                //与上面封装的对象对应

               // Toast.makeText(mContext,"你点击了"+mData.get(position), Toast.LENGTH_SHORT).show();
                ChosedStyle.chosed = mData.get(position);
                //ChosedObject[] strings = new ChosedObject[]{ChosedStyle, ChosedSpace, ChosedType, ChosedSize};
                Home_display home_display = new Home_display();
                home_display.setValue();
                mPopupWindow.dismiss();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView mTextView;
    }
}
*/
