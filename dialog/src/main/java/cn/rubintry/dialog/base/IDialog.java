package cn.rubintry.dialog.base;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/**
 * @author logcat
 */
public interface IDialog extends DialogInterface {
    /**
     * 设置dialog尺寸
     */
    void setSize(int width , int height);


//    /**
//     *  设置布局
//     * @return
//     */
//    int setContent();


    /**
     * 设置drawable资源
     */
    Drawable setDrawable();

    /**
     * 显示dialog
     */
    void show();

}
