package cn.rubintry.dialog.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * @author logcat
 * 构建一个显示在屏幕底部的dialog
 */
public class BaseBottomDialog extends BaseDialog {
    private static final int DEFAULT_WIDTH = WindowManager.LayoutParams.WRAP_CONTENT;
    private static final int DEFAULT_HEIGHT = WindowManager.LayoutParams.WRAP_CONTENT;
    protected Context context;
    protected int width;
    protected int height;
    protected BaseBottomDialog(@NonNull Context context) {
        super(context);
    }

    protected BaseBottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context.getApplicationContext();
        this.width = 0;
        this.height = 0;
    }

    protected BaseBottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContent());
        setSize(width , height);
    }

    protected int setContent() {
        return 0;
    }


    @Override
    public void show() {
        super.show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 然后弹出输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setGravity(Gravity.BOTTOM);
        //调整窗体大小
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = this.width;
        params.height = this.height;
        Drawable drawable = setDrawable();
        if(drawable == null){
            drawable = new ColorDrawable(Color.TRANSPARENT);
        }
        getWindow().setBackgroundDrawable(drawable);
        getWindow().setAttributes(params);
    }

    @Override
    public void setSize(int width, int height) {
        if(width == 0){
            this.width = DEFAULT_WIDTH;
        }
        if(height == 0){
            this.height = DEFAULT_HEIGHT;
        }
//        if (getWindow() != null) {
//            WindowManager.LayoutParams params = getWindow().getAttributes();
//            params.width = width;
//            params.height = height;
//            getWindow().setAttributes(params);
//        }
    }

    @Override
    public Drawable setDrawable() {
        return null;
    }
}
