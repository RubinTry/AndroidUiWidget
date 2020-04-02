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
    private static final int DEFAULT_WIDTH = 315;
    private static final int DEFAULT_HEIGHT = 200;
    protected Context context;
    protected int width;
    protected int height;
    protected BaseBottomDialog(@NonNull Context context) {
        super(context);
    }

    protected BaseBottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context.getApplicationContext();
    }

    protected BaseBottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContent());
        if (getWindow() != null) {
            initWindow(getWindow());
        }
        setSize(width , height);
    }

    protected int setContent() {
        return 0;
    }


    private void initWindow(@NonNull Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 然后弹出输入法
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        //调整窗体大小
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = this.width;
        params.height = this.height;
        Drawable drawable = setDrawable();
        if(drawable == null){
            drawable = new ColorDrawable(Color.TRANSPARENT);
        }
        window.setBackgroundDrawable(drawable);
        window.setAttributes(params);
    }


    @Override
    public void setSize(int width, int height) {
        if(width == 0){
            width = DEFAULT_WIDTH;
        }
        if(height == 0){
            height = DEFAULT_HEIGHT;
        }
        if (getWindow() != null) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.width = width;
            params.height = height;
            getWindow().setAttributes(params);
        }
    }

    @Override
    public Drawable setDrawable() {
        return null;
    }
}
