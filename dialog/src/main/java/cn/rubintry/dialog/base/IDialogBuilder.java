package cn.rubintry.dialog.base;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/**
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


    IDialogBuilder setDrawable(Drawable drawable);

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
     * 创建dialog
     * @return
     */
    IDialog create();

}
