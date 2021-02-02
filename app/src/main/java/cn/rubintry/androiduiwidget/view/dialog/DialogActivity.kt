package cn.rubintry.androiduiwidget.view.dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import cn.rubintry.androiduiwidget.MainActivity;
import cn.rubintry.androiduiwidget.R;
import cn.rubintry.androiduiwidget.base.BaseActivity;
import cn.rubintry.dialog.base.IDialog;
import cn.rubintry.dialog.ios.GlobalAlertDialog;
import cn.rubintry.dialog.ios.GlobalDialog;
import cn.rubintry.dialog.ios.IOSBottomListDialog;
import cn.rubintry.dialog.ios.IOSMessageDialog;

/**
 * @author logcat
 */
public class DialogActivity extends BaseActivity {


    private IDialog dialog;
    private IDialog bottomDialog;
    private GlobalDialog globalDialog;
    private GlobalAlertDialog globalAlertDialog;

    private TestHandler testHandler = new TestHandler();

    private boolean showDialog = true;

    @Override
    protected int setLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDialog();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                //若未授权则请求权限
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 0);
            } else {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (showDialog) {
//                            Message message = new Message();
//                            message.what = 1;
//                            testHandler.sendMessage(message);
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }).start();
            }
        }


    }

    private void createDialog() {

        dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setMessage("这是信息")
                .setTextColor(ContextCompat.getColor(this, R.color.txtColor))
                .setPositiveButton("更新", new IOSMessageDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog) {

                    }

                })
                .setNegativeButton("取消", new IOSMessageDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog) {

                    }
                })
                .create();


        List<String> list = new ArrayList<>();
        list.add("男");
//        list.add("女");
//        list.add("不显示");
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


        globalDialog = new GlobalDialog.Builder(getApplicationContext())
                .setLayoutId(R.layout.notify_dialog).create();

        globalAlertDialog = new GlobalAlertDialog.Builder(getApplicationContext())
                .setOnNotifyClickListener(new GlobalAlertDialog.OnNotifyClickListener() {
                    @Override
                    public void onRootViewClick() {
                        Toast.makeText(DialogActivity.this, "消息被点击了", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        globalAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                showDialog = false;
                finish();
                return false;
            }
        });
    }


    @OnClick({R.id.btnTipsDialog, R.id.btnBottomListDialog, R.id.btnGlobalDialog})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTipsDialog:
                dialog.show();
                break;
            case R.id.btnBottomListDialog:
                bottomDialog.show();
                break;
            case R.id.btnGlobalDialog:
//                globalDialog.show();
                globalAlertDialog.show();
                break;
            default:
                break;
        }
    }


    private class TestHandler extends Handler {

        public TestHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (globalAlertDialog != null && !globalAlertDialog.isShowing()) {
                        globalAlertDialog.show();
                    } else {
                        globalAlertDialog.cancel();
                        globalAlertDialog.show();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
