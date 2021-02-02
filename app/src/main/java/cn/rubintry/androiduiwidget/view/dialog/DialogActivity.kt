package cn.rubintry.androiduiwidget.view.dialog

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import cn.rubintry.androiduiwidget.R
import cn.rubintry.androiduiwidget.base.BaseActivity
import cn.rubintry.dialog.base.IDialog
import cn.rubintry.dialog.ios.GlobalAlertDialog
import cn.rubintry.dialog.ios.GlobalAlertDialog.OnNotifyClickListener
import cn.rubintry.dialog.ios.GlobalDialog
import cn.rubintry.dialog.ios.IOSBottomListDialog
import cn.rubintry.dialog.ios.IOSMessageDialog
import kotlinx.android.synthetic.main.activity_dialog.*
import java.util.*

/**
 * @author logcat
 */
class DialogActivity : BaseActivity(), View.OnClickListener {
    private var dialog: IDialog? = null
    private var bottomDialog: IDialog? = null
    private var globalDialog: GlobalDialog? = null
    private var globalAlertDialog: GlobalAlertDialog? = null
    private val testHandler = TestHandler()
    private var showDialog = true
    override fun setLayout(): Int {
        return R.layout.activity_dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDialog()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                //若未授权则请求权限
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(intent, 0)
            } else {
            }
        }
        initListener()
    }

    private fun initListener() {
        btnTipsDialog.setOnClickListener(this)
        btnBottomListDialog.setOnClickListener(this)
        btnGlobalDialog.setOnClickListener(this)
    }

    private fun createDialog() {
        dialog = IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setMessage("这是信息")
                .setTextColor(ContextCompat.getColor(this, R.color.txtColor))
                .setPositiveButton("更新" , object : IOSMessageDialog.OnClickListener{
                    override fun onClick(dialog: Dialog?) {

                    }

                })
                .setNegativeButton("取消" , object : IOSMessageDialog.OnClickListener{
                    override fun onClick(dialog: Dialog?) {

                    }

                })
                .create()
        val list: MutableList<String> = ArrayList()
        list.add("男")
        list.add("女");
        list.add("不显示");
        bottomDialog = IOSBottomListDialog.Builder(this)
                .setCancelable(true)
                .setList(list)
                .setCancelListener(null)
                .setOnItemClickListener(object : IOSBottomListDialog.OnItemClickListener {
                    override fun onCancel() {}
                    override fun onItemClick(content: String?, position: Int) {
                        Toast.makeText(this@DialogActivity, content, Toast.LENGTH_SHORT).show()
                    }
                }).create()
        globalDialog = GlobalDialog.Builder(applicationContext)
                .setLayoutId(R.layout.notify_dialog).create()
        globalAlertDialog = GlobalAlertDialog.Builder(applicationContext)
                .setOnNotifyClickListener(object : OnNotifyClickListener {
                    override fun onRootViewClick() {
                        Toast.makeText(this@DialogActivity, "消息被点击了", Toast.LENGTH_SHORT).show()
                    }
                })
                .create()
        globalAlertDialog?.setOnKeyListener { dialog, keyCode, event ->
            showDialog = false
            finish()
            false
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnTipsDialog -> dialog?.show()
            R.id.btnBottomListDialog -> bottomDialog?.show()
            R.id.btnGlobalDialog -> //                globalDialog.show();
                globalAlertDialog?.show()
            else -> {
            }
        }
    }

    private inner class TestHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> if (globalAlertDialog != null && globalAlertDialog?.isShowing == false) {
                    globalAlertDialog?.show()
                } else {
                    globalAlertDialog?.cancel()
                    globalAlertDialog?.show()
                }
                else -> {
                }
            }
        }
    }
}