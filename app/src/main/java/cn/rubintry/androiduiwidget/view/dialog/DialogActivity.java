package cn.rubintry.androiduiwidget.view.dialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import cn.rubintry.androiduiwidget.MainActivity;
import cn.rubintry.androiduiwidget.R;
import cn.rubintry.androiduiwidget.base.BaseActivity;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.ios.IOSBottomListDialog;
import cn.rubintry.dialog.ios.IOSMessageDialog;

/**
 * @author logcat
 */
public class DialogActivity extends BaseActivity {


    private IDialog dialog;
    private IDialog bottomDialog;

    @Override
    protected int setLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDialog();
    }

    private void createDialog() {

        dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setMessage("这是信息")
                .setTextColor(ContextCompat.getColor(this, R.color.txtColor))
                .setOnButtonClickListener(new IOSMessageDialog.OnButtonClickListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(DialogActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(DialogActivity.this, "点击取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();


        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        list.add("不显示");
        bottomDialog = new IOSBottomListDialog.Builder(this)
                .setCancelable(true)
                .setList(list)
                .setCancelListener(null)
                .setOnItemClickListener(new IOSBottomListDialog.OnItemClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onItemClick(String content, int position) {
                        Toast.makeText(DialogActivity.this, content, Toast.LENGTH_SHORT).show();
                    }

                }).create();

    }


    @OnClick({R.id.btnTipsDialog , R.id.btnBottomListDialog})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btnTipsDialog:
                dialog.show();
                break;
            case R.id.btnBottomListDialog:
                bottomDialog.show();
                break;
                default:
                    break;
        }
    }
}
