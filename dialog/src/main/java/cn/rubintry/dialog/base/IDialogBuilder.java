package cn.rubintry.dialog.base;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/**
 * Dialog构造器接口
 * @author logcat
 */
public interface IDialogBuilder {



    /**
     * 设置边框尺寸
     * @param width
     * @param height
     * @return
     */
    IDialogBuilder setSize(int width , int height);


    /**
     * 设置dialog背景drawable
     * @param drawable
     * @return
     */
    IDialogBuilder setDrawable(Drawable drawable);


    /**
     *  设置标题
     * @param title
     * @return
     */
    IDialogBuilder setTitle(String title);


    /**
     *  设置消息
     * @param message
     * @return
     */
    IDialogBuilder setMessage(String message);


    /**
     * 设置文本内容的字体大小
     * @param spValue
     * @return
     */
    IDialogBuilder setMessageTextSize(int spValue);


    /**
     * 设置字体颜色
     * @param textColor
     * @return
     */
    IDialogBuilder setTextColor(Integer textColor);

    /**
     * 设置是否可以通过系统交互取消dialog
     * @param cancelable
     * @return
     */
    IDialogBuilder setCancelable(boolean cancelable);

    /**
     * 设置消失监听器
     * @param cancelListener
     * @return
     */
    IDialogBuilder setCancelListener(DialogInterface.OnCancelListener cancelListener);


    /**
     *  默认的按钮点击事件
     * @param listener
     * @return
     */
    IDialogBuilder  setOnButtonClickListener(BaseClickListener listener);





    /**
     * 创建dialog
     * @return
     */
    IDialog create();

}
