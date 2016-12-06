package com.maiml.mydialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maimingliang on 2016/11/30.
 */

public class DialogUtils {

    public static Dialog showIosAlert(Context context, boolean isButtonVerticle, String title, String msg,
                                       String firstTxt, String secondTxt, String thirdTxt,
                                       boolean outsideCancleable, boolean cancleable,
                                       final MyDialogListener listener){

        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);
        assigIosAlertView(context,dialog,isButtonVerticle,title,msg,firstTxt,secondTxt,thirdTxt,listener);
        dialog.show();
        return dialog;
    }

    private static Dialog buildDialog(Context context, boolean cancleable, boolean outsideTouchable) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancleable);
        dialog.setCanceledOnTouchOutside(outsideTouchable);
        return dialog;
    }

    private static int  assigIosAlertView(Context activity, final Dialog dialog, boolean isButtonVerticle,
                                          String title, String msg, String firstTxt, String secondTxt, String thirdTxt,
                                          final MyDialogListener listener) {
        View root = View.inflate(activity,isButtonVerticle ? R.layout.dialog_ios_alert_vertical : R.layout.dialog_ios_alert,null);
        TextView tvTitle = (TextView) root.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        TextView tvMsg = (TextView) root.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

        Button button1 = (Button) root.findViewById(R.id.btn_1);
        Button button2 = (Button) root.findViewById(R.id.btn_2);
        Button button3 = (Button) root.findViewById(R.id.btn_3);

        if (TextUtils.isEmpty(firstTxt)){
            root.findViewById(R.id.ll_container).setVisibility(View.GONE);
            root.findViewById(R.id.line).setVisibility(View.GONE);
        }else {

            button1.setVisibility(View.VISIBLE);
            button1.setText(firstTxt);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFirst(dialog);
                    if (dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });

            //btn2
            if (TextUtils.isEmpty(secondTxt)){
                root.findViewById(R.id.line_btn2).setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
            }else {
                root.findViewById(R.id.line_btn2).setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                button2.setText(secondTxt);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSecond(dialog);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                //btn 3
                if (TextUtils.isEmpty(thirdTxt)){
                    root.findViewById(R.id.line_btn3).setVisibility(View.GONE);
                    button3.setVisibility(View.GONE);
                }else {
                    root.findViewById(R.id.line_btn3).setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);

                    button3.setText(thirdTxt);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onThird(dialog);
                            if (dialog != null && dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    });


                }

            }

        }
        dialog.setContentView(root);
        return 0;

    }



    public static Dialog showBottomItemDialog(Context context,
                                              List<String> words, String bottomTxt,
                                              boolean outsideCancleable, boolean cancleable,
                                              final MyItemDialogListener listener){
        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);

        int measuredHeight =   assignBottomListDialogView(context,dialog,words,bottomTxt,listener);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);




        dialog.show();
        return dialog;
    }
    private static int assignBottomListDialogView(final Context context,  final Dialog dialog,
                                                  final List<String> words, String bottomTxt, final MyItemDialogListener listener) {
        View root = View.inflate(context,R.layout.dialog_ios_alert_bottom,null);
        Button btnBottom = (Button) root.findViewById(R.id.btn_bottom);
        if (TextUtils.isEmpty(bottomTxt)){
            btnBottom.setVisibility(View.GONE);
        }else {
            btnBottom.setVisibility(View.VISIBLE);
            btnBottom.setText(bottomTxt);
            btnBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBottomBtnClick();
                    if (dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });
        }

        ListView listView = (ListView) root.findViewById(R.id.lv);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return words.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                Button view = (Button) View.inflate(context,R.layout.item_btn_bottomalert,null);
                view.setText(words.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(words.get(position),position);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                return view;
            }
        };

        listView.setAdapter(adapter);
        dialog.setContentView(root);

        int height =    0;

        return height;
    }







}
