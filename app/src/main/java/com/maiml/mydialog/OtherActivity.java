package com.maiml.mydialog;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {

    private RelativeLayout rlTitle;
    private ListView categoryListView;
    private List<String> datas;
    private PopupWindow window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initView();

        setListener();
    }

    private void setListener() {

        rlTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCategory();
            }
        });
    }

    private void initView() {

        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);

        datas = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            datas.add("item " + i);
        }

        categoryListView = new ListView(this);
        //隐藏滚动条
        categoryListView.setVerticalScrollBarEnabled(false);
        categoryListView.setBackgroundResource(R.color.white);
        categoryListView.setDivider(null);

        categoryListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public Object getItem(int position) {
                return datas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(OtherActivity.this).inflate(
                            R.layout.item_region, parent, false);
                }
                TextView textView = BaseViewHolder.get(convertView, R.id.tv_name);

                textView.setText(datas.get(position));

                return convertView;
            }
        });
    }


    private void selectCategory() {
        if (window == null) {
            //创建PopupWindow
            window = new PopupWindow(categoryListView, rlTitle.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        window.setFocusable(true);
        //设置背景图片
        window.setBackgroundDrawable(new BitmapDrawable());
        //设置外部点击消失
        window.setOutsideTouchable(true);
        window.showAsDropDown(rlTitle);
    }

}
