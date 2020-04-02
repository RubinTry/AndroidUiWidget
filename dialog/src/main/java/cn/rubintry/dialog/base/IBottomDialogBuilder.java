package cn.rubintry.dialog.base;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

/**
 * @author logcat
 * 底部列表弹窗的构造器接口
 */
public interface IBottomDialogBuilder extends IDialogBuilder {
    /**
     *  item点击事件
     * @param listener
     * @return
     */
    IBottomDialogBuilder  setOnItemClickListener(BaseClickListener listener);

    /**
     * 设置窗体大小
     * @param width
     * @param height
     * @return
     */
    @Override
    IBottomDialogBuilder setSize(int width, int height);


    /**
     * 设置窗体背景drawable
     * @param drawable
     * @return
     */
    @Override
    IBottomDialogBuilder setDrawable(Drawable drawable);


    @Override
    IBottomDialogBuilder setCancelable(boolean cancelable);

    @Override
    IBottomDialogBuilder setCancelListener(DialogInterface.OnCancelListener cancelListener);

    @Override
    IDialog create();
}
