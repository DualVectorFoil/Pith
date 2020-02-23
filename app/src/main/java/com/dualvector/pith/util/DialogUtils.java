package com.dualvector.pith.util;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dualvector.pith.R;

public class DialogUtils {

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);
        TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
        if (msg != null && !msg.equals("")) {
            tipTextView.setText(msg);// 设置加载信息
        }

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);//
        loadingDialog.setContentView(v);// 设置布局

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = (int) (dm.widthPixels * 0.3);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        if (loadingDialog.getWindow() != null) {
            loadingDialog.getWindow().setLayout(width, height);
        }

        return loadingDialog;
    }
}
