package cn.rubintry.dialog.ios;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

import cn.rubintry.dialog.R;
import cn.rubintry.dialog.base.BaseCenterDialog;
import cn.rubintry.dialog.base.IDialogBuilder;

/**
 * @author logcat
 * IOS风格的消息弹窗(提示窗)
 */
public class IOSMessageDialog extends BaseCenterDialog  {

    private Drawable drawable;

    public IOSMessageDialog(Builder builder) {
        super(builder.contextWeakReference.get(), R.style.dialog_default_style);
        setCancelable(builder.cancelable);
        setOnCancelListener(builder.onCancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置窗体尺寸
     *
     * @param width
     * @param height
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    /**
     * 设置窗体布局
     */
    @Override
    protected int setContent() {
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
