package cn.rubintry.dialog.ios;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.rubintry.dialog.R;
import cn.rubintry.dialog.base.BaseBottomDialog;
import cn.rubintry.dialog.base.BaseClickListener;
import cn.rubintry.dialog.base.IBottomDialogBuilder;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.base.IDialogBuilder;


/**
 * @author logcat
 *  底部弹出式列表弹窗(IOS风格)
 */
public class IOSBottomListDialog extends BaseBottomDialog {
    private Drawable drawable;
    private List<String> messageList;
    private Integer itemTextSize;
    private Integer itemTextColor;
    protected IOSBottomListDialog(@NonNull Context context) {
        super(context);
    }

    protected IOSBottomListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected IOSBottomListDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public IOSBottomListDialog(Builder builder) {
        super(builder.contextWeakReference.get(), builder.cancelable , builder.cancelListener);
        this.messageList = builder.list;
        this.itemTextColor = builder.itemTextColor;
        this.itemTextSize = builder.itemTextSize;
        this.width = builder.width;
        this.height = builder.height;
        this.drawable = builder.drawable;
    }




    @Override
    public Drawable setDrawable() {
        return drawable;
    }

    @Override
    protected int setContent() {
        return R.layout.ios_bottom_list_dialog;
    }

    public static class Builder implements IBottomDialogBuilder {
        private WeakReference<Context> contextWeakReference;
        private List<String> list;
        private Integer itemTextSize;
        private Integer itemTextColor;
        private boolean cancelable;
        private OnCancelListener cancelListener;
        private Drawable drawable;
        private int width;
        private int height;
        private OnItemClickListener onItemClickListener;

        public Builder(Context context) {
            contextWeakReference = new WeakReference<>(context);
        }

        public Builder setList(List<String> list) {
            this.list = list;
            return this;
        }

        public Builder setItemTextSize(Integer itemTextSize) {
            this.itemTextSize = itemTextSize;
            return this;
        }

        public Builder setItemTextColor(Integer itemTextColor) {
            this.itemTextColor = itemTextColor;
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
        public IDialogBuilder setTitle(String title) {
            return null;
        }

        @Override
        public Builder setMessage(String message) {
            return this;
        }

        @Override
        public IDialogBuilder setMessageTextSize(int spValue) {
            return null;
        }

        @Override
        public Builder setTextColor(Integer textColor) {
            return this;
        }

        @Override
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        @Override
        public Builder setCancelListener(OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        @Override
        public Builder setOnButtonClickListener(BaseClickListener listener) {
            return this;
        }

        @Override
        public Builder setOnItemClickListener(BaseClickListener listener) {
            this.onItemClickListener = (OnItemClickListener) listener;
            return this;
        }


        @Override
        public IDialog create() {
            if(list == null || list.size() == 0){
                throw new IllegalArgumentException("Please add some items!!!");
            }
            return new IOSBottomListDialog(this);
        }
    }


    public interface OnItemClickListener extends BaseClickListener{

        @Override
        void onCancel();


        void onItemClick();
    }
}
