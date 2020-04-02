package cn.rubintry.dialog.ios;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

import cn.rubintry.dialog.R;
import cn.rubintry.dialog.base.BaseClickListener;
import cn.rubintry.dialog.base.BaseCenterDialog;
import cn.rubintry.dialog.base.IDialogBuilder;

/**
 * @author logcat
 * IOS风格的消息弹窗(提示窗)
 */
public class IOSMessageDialog extends BaseCenterDialog implements View.OnClickListener {

    private final OnButtonClickListener onButtonClickListener;
    private final String message;
    private Drawable drawable;
    private LinearLayout llcontainer;

    public IOSMessageDialog(Builder builder) {

        super(builder.contextWeakReference.get(), builder.cancelable , builder.onCancelListener);
        this.drawable = builder.drawable;
        this.width = builder.width;
        this.height = builder.height;
        this.onButtonClickListener = builder.onButtonClickListener;
        this.message = builder.message;
        this.context = builder.contextWeakReference.get();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnClickListener(this, R.id.btn_confirm);
        setOnClickListener(this, R.id.btn_cancel);
        if(message != null){
            setText(R.id.tv_content , message);
        }
        llcontainer = findViewById(R.id.llcontainer);
        llcontainer.setOnClickListener(this);
    }

    /**
     * 设置窗体布局
     */
    @Override
    protected int setContent() {
        return R.layout.ios_message_dialog;
    }

    @Override
    public Drawable setDrawable() {
        return drawable;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onClick(View v) {
        if (onButtonClickListener == null) {
            return;
        }
        if (v.getId() == R.id.btn_confirm) {
            onButtonClickListener.onConfirm();
            this.cancel();
        } else if(v.getId() == R.id.btn_cancel) {
            onButtonClickListener.onCancel();
            this.cancel();
        }else if(v.getId() == R.id.llcontainer){
            Log.d("dialog", "onClick: ");
        }

    }

    public static class Builder implements IDialogBuilder {

        private WeakReference<Context> contextWeakReference;
        private int width;
        private int height;
        private OnCancelListener onCancelListener;
        private boolean cancelable;
        private Drawable drawable;
        private String message;
        private OnButtonClickListener onButtonClickListener;

        public Builder(Context context) {
            contextWeakReference = new WeakReference<>(context);
        }

        @Override
        public IDialogBuilder setMessage(String message) {
            this.message = message;
            return this;
        }



        @Override
        public IDialogBuilder setSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        @Override
        public IDialogBuilder setDrawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        @Override
        public IDialogBuilder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        @Override
        public IDialogBuilder setCancelListener(OnCancelListener cancelListener) {
            this.onCancelListener = cancelListener;
            return this;
        }

        @Override
        public IDialogBuilder setOnButtonClickListener(BaseClickListener listener) {
            this.onButtonClickListener = (OnButtonClickListener) listener;
            return this;
        }

        @Override
        public IOSMessageDialog create() {
            return new IOSMessageDialog(this);
        }
    }


    public interface OnButtonClickListener extends BaseClickListener {
        /**
         *  确定
         */
        @Override
        void onConfirm();

        /**
         * 取消
         */
        @Override
        void onCancel();
    }
}
