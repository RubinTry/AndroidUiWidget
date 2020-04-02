package cn.rubintry.dialog.base;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/**
 * @author logcat
 */
public interface IDialog extends DialogInterface {
    /**
     * 设置dialog尺寸
     *
     * @param width
     * @param height
     */
    void setSize(int width , int height);


    /**
     *  设置drawable资源
     * @return
     */
    Drawable setDrawable();

    /**
     * 显示dialog
     */
    void show();


    /**
     *  取消dialog
     */
    @Override
    void cancel();


    /**
     *  隐藏dialog
     */
    @Override
    void dismiss();




}
