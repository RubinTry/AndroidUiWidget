package cn.rubintry.dialog.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * @author logcat
 * dialog基类
 */
public class BaseDialog extends AlertDialog implements IDialog {


    protected BaseDialog(@NonNull Context context) {
        super(context);
    }

    protected BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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


    public void setTextColor(int resId , int color){
        View view = findViewById(resId);
        if(view != null && view instanceof TextView){
            ((TextView) view).setTextColor(color);
        }
    }


    public void setTextSize(int resId , int size){
        View view = findViewById(resId);
        if(view != null && view instanceof TextView){
            ((TextView) view).setTextSize(size);
        }
    }

    @Override
    public void setSize(int width, int height) {

    }

    @Override
    public Drawable setDrawable() {
        return null;
    }


}
