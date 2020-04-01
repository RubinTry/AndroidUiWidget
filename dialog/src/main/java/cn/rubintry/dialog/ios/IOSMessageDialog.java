package cn.rubintry.dialog.ios;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

import cn.rubintry.dialog.base.BaseCenterDialog;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.base.IDialogBuilder;
import cn.rubintry.dialog.R;

/**
 * @author logcat
 *  IOS风格的消息弹窗(提示窗)
 */
public class IOSMessageDialog extends BaseCenterDialog {

    private Drawable drawable;


    protected IOSMessageDialog(@NonNull Context context) {
        super(context);
    }

    protected IOSMessageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected IOSMessageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public IOSMessageDialog(Builder builder) {
        super(builder.contextWeakReference.get() , builder.cancelable , builder.onCancelListener);
        this.width = builder.width;
        this.height = builder.height;
        this.drawable = builder.drawable;
        setContent();
    }


    /**
     * 设置窗体尺寸
     * @param width
     * @param height
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }


    /**
     * 设置窗体布局
     * @return
     */
    @Override
    protected int setContent() {
        setSize(width , height);
        return R.layout.item_ios_message_dialog;
    }

    @Override
    public Drawable setDrawable() {
        return drawable;
    }

    @Override
    public void show() {
        super.show();
    }



    public static class Builder implements IDialogBuilder {

        private WeakReference<Context> contextWeakReference;
        private int width;
        private int height;
        private OnCancelListener onCancelListener;
        private boolean cancelable;
        private Drawable drawable;

        public Builder(Context context) {
            contextWeakReference = new WeakReference<>(context);
        }


        @Override
        public Builder setSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        @Override
        public Builder setDrawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        @Override
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        @Override
        public Builder setCancelListener(OnCancelListener cancelListener) {
            this.onCancelListener = cancelListener;
            return this;
        }

        @Override
        public IOSMessageDialog create() {
            return new IOSMessageDialog(this);
        }
    }
}
