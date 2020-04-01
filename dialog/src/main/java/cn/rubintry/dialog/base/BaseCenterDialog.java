package cn.rubintry.dialog.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * @author logcat
 * 构建一个显示在屏幕中间的dialog
 */
public class BaseCenterDialog extends AlertDialog implements IDialog {

    protected int width;
    protected int height;
    private Context context;
    protected BaseCenterDialog(@NonNull Context context) {
        super(context);

    }

    protected BaseCenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context.getApplicationContext();
    }

    protected BaseCenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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

    private void initWindow(@NonNull Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 然后弹出输入法
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        //调整窗体大小
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        Drawable drawable = setDrawable();
        if(drawable == null){
            drawable = new ColorDrawable(Color.TRANSPARENT);
        }
        window.setBackgroundDrawable(drawable);
        window.setAttributes(params);
    }

    @Override
    public void setSize(int width, int height) {
        if (getWindow() != null) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.width = width;
            params.height = height;
            getWindow().setAttributes(params);
        }
    }


    protected int setContent() {
        return 0;
    }

    @Override
    public Drawable setDrawable() {
        return null;
    }


    public void setOnClickListener(View.OnClickListener listener , int resId){
        findViewById(resId).setOnClickListener(listener);
    }

    public void setText(int resId , String msg){
        View view = findViewById(resId);
        if(view != null && view instanceof TextView){
            ((TextView) view).setText(msg);
        }
    }



}
