package cn.rubintry.dialog.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

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

    protected BaseCenterDialog(@NonNull Context context) {
        super(context);
        width = 0;
        height = 0;
    }

    protected BaseCenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseCenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContent());
    }

    @Override
    public void show() {
        super.show();
        // 接着清除flags
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        // 然后弹出输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setGravity(Gravity.CENTER);
        //调整窗体大小
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        params.height = height;
        getWindow().setAttributes(params);
        Drawable drawable = setDrawable();
        if(drawable != null){
            getWindow().setBackgroundDrawable(drawable);
        }
    }

    @Override
    public void setSize(int width, int height) {

    }


    protected int setContent() {
        return 0;
    }

    @Override
    public Drawable setDrawable() {
        return null;
    }

}
