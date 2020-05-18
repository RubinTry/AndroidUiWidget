package cn.rubintry.dialog.ios;

import android.app.Dialog;
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

    private OnClickListener negativeBtnListener;
    private OnClickListener positiveBtnListener;
    private OnButtonClickListener onButtonClickListener;
    private String message;
    private Integer textColor;
    private Integer textSize;
    private String title;
    private Drawable drawable;
    private LinearLayout llContainer;
    private String positiveBtn;
    private String negativeBtn;

    public IOSMessageDialog(Builder builder) {
        super(builder.contextWeakReference.get(), builder.cancelable , builder.onCancelListener);
        this.drawable = builder.drawable;
        this.width = builder.width;
        this.height = builder.height;
        this.onButtonClickListener = builder.onButtonClickListener;
        this.message = builder.message;
        this.context = builder.contextWeakReference.get();
        this.textColor = builder.textColor;
        this.textSize = builder.textSize;
        this.title = builder.title;
        this.positiveBtn = builder.positiveBtn;
        this.negativeBtn = builder.negativeBtn;
        this.positiveBtnListener = builder.positiveBtnListener;
        this.negativeBtnListener = builder.negativeBtnListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnClickListener(this, R.id.btn_confirm);
        setOnClickListener(this, R.id.btn_cancel);

        if(title != null){
            setText(R.id.tv_title , title);
        }

        if(message != null){
            setText(R.id.tv_content , message);
        }

        if(textColor != null){
            setTextColor(R.id.tv_content , textColor.intValue());
        }

        if(textSize != null){
            setTextSize(R.id.tv_content , textSize.intValue());
        }

        if(positiveBtn != null){
            setText(R.id.btn_confirm , positiveBtn);
        }

        if(negativeBtn != null){
            setText(R.id.btn_cancel , negativeBtn);
        }

        llContainer = findViewById(R.id.llContainer);
        llContainer.setOnClickListener(this);
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

        if (v.getId() == R.id.btn_confirm) {
            if(onButtonClickListener != null){
                onButtonClickListener.onConfirm();
            }

            if(positiveBtnListener != null){
                positiveBtnListener.onClick(this);
            }
            this.cancel();
        } else if(v.getId() == R.id.btn_cancel) {
            if(onButtonClickListener != null){
                onButtonClickListener.onCancel();
            }
            if(negativeBtnListener != null){
                negativeBtnListener.onClick(this);
            }
            this.cancel();
        }else if(v.getId() == R.id.llContainer){
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
        private Integer textColor;
        private Integer textSize;
        private OnButtonClickListener onButtonClickListener;
        private String title;
        private OnClickListener positiveBtnListener;
        private String positiveBtn;
        private OnClickListener negativeBtnListener;
        private String negativeBtn;

        public Builder(Context context) {
            contextWeakReference = new WeakReference<>(context);
        }

        @Override
        public Builder setTextColor(Integer textColor) {
            this.textColor = textColor;
            return this;
        }

        @Override
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder setMessageTextSize(int textSize) {
            this.textSize = textSize;
            return this;
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
        public Builder setTitle(String title) {
            this.title = title;
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
        public Builder setOnButtonClickListener(BaseClickListener listener) {
            this.onButtonClickListener = (OnButtonClickListener) listener;
            return this;
        }

        public Builder setPositiveButton(String positiveBtn , OnClickListener listener){
            this.positiveBtnListener = (OnClickListener)listener;
            this.positiveBtn = positiveBtn;
            return this;
        }


        public Builder setNegativeButton(String negativeBtn , OnClickListener listener){
            this.negativeBtnListener = (OnClickListener)listener;
            this.negativeBtn = negativeBtn;
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
        void onConfirm();

        /**
         * 取消
         */
        @Override
        void onCancel();
    }


    public interface OnClickListener{
        void onClick(Dialog dialog);
    }
}
