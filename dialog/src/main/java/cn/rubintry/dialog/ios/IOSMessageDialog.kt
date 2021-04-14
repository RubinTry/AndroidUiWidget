package cn.rubintry.dialog.ios

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import cn.rubintry.dialog.R
import cn.rubintry.dialog.base.BaseCenterDialog
import cn.rubintry.dialog.base.BaseClickListener
import cn.rubintry.dialog.base.IDialogBuilder
import java.lang.ref.WeakReference

/**
 * @author logcat
 * IOS风格的消息弹窗(提示窗)
 */
class IOSMessageDialog(builder: Builder) : BaseCenterDialog(builder.contextWeakReference.get()!!, builder.getCancelable(), builder.getOnCancelListener()), View.OnClickListener {
    private val negativeBtnListener: OnClickListener?
    private val positiveBtnListener: OnClickListener?
    private val onButtonClickListener: OnButtonClickListener?
    private val message: String?
    private val textColor: Int?
    private val textSize: Int?
    private val title: String?
    private val drawable: Drawable?
    private var llContainer: LinearLayout? = null
    private val positiveBtn: String?
    private val negativeBtn: String?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnClickListener(this, R.id.btn_confirm)
        setOnClickListener(this, R.id.btn_cancel)
        if (title != null) {
            setText(R.id.tv_title, title)
        }
        if (message != null) {
            setText(R.id.tv_content, message)
        }
        if (textColor != null) {
            setTextColor(R.id.tv_content, textColor.toInt())
        }
        if (textSize != null) {
            setTextSize(R.id.tv_content, textSize.toInt())
        }
        if (positiveBtn != null) {
            setText(R.id.btn_confirm, positiveBtn)
        }
        if (negativeBtn != null) {
            setText(R.id.btn_cancel, negativeBtn)
        }
        llContainer = findViewById(R.id.llContainer)
        llContainer?.setOnClickListener(this)
    }

    /**
     * 设置窗体布局
     */
    override fun setContent(): Int {
        return R.layout.ios_message_dialog
    }

    override fun setDrawable(): Drawable? {
        return drawable
    }

    override fun show() {
        window?.setWindowAnimations(R.style.ios_message_dialog_animation)
        super.show()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_confirm -> {
                onButtonClickListener?.onConfirm()
                positiveBtnListener?.onClick(this)
                cancel()
            }
            R.id.btn_cancel -> {
                onButtonClickListener?.onCancel()
                negativeBtnListener?.onClick(this)
                cancel()
            }
            R.id.llContainer -> {
                Log.d("dialog", "onClick: ")
            }
        }
    }

    class Builder(context: Context) : IDialogBuilder {
        val contextWeakReference: WeakReference<Context> = WeakReference(context)
        var width = 0
        var height = 0
        private var onCancelListener: DialogInterface.OnCancelListener? = null
        private var cancelable = false
        var drawable: Drawable? = null
        var message: String? = null
        var textColor: Int? = null
        var textSize: Int? = null
        var onButtonClickListener: OnButtonClickListener? = null
        var title: String? = null
        var positiveBtnListener: OnClickListener? = null
        var positiveBtn: String? = null
        var negativeBtnListener: OnClickListener? = null
        var negativeBtn: String? = null

        fun getCancelable(): Boolean{
            return cancelable
        }

        fun getOnCancelListener(): DialogInterface.OnCancelListener?{
            return onCancelListener
        }


        /**
         * 设置文字内容颜色
         *
         * @param textColor
         * @return
         */
        override fun setTextColor(textColor: Int): Builder {
            this.textColor = textColor
            return this
        }

        /**
         * 设置文字内容
         *
         * @param message
         * @return
         */
        override fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        /**
         * 设置文字内容的大小
         *
         * @param textSize
         * @return
         */
        override fun setMessageTextSize(textSize: Int): Builder {
            this.textSize = textSize
            return this
        }

        /**
         * 设置弹窗的宽高
         *
         * @param width
         * @param height
         * @return
         */
        override fun setSize(width: Int, height: Int): Builder {
            this.width = width
            this.height = height
            return this
        }

        /**
         * 设置整个弹窗的背景
         *
         * @param drawable
         * @return
         */
        override fun setDrawable(drawable: Drawable): Builder {
            this.drawable = drawable
            return this
        }

        /**
         * 设置弹窗标题
         *
         * @param title
         * @return
         */
        override fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        /**
         * 设置是否可以取消
         *
         * @param cancelable
         * @return
         */
        override fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        /**
         * 设置点击“取消”按钮后的回调
         *
         * @param cancelListener
         * @return
         */
        override fun setCancelListener(cancelListener: DialogInterface.OnCancelListener): Builder {
            onCancelListener = cancelListener
            return this
        }

        /**
         * 统一设置按钮的点击事件
         *
         * @param listener
         * @return
         */
        override fun setOnButtonClickListener(listener: BaseClickListener): Builder {
            onButtonClickListener = listener as OnButtonClickListener
            return this
        }

        /**
         * “确定”按钮的点击事件
         *
         * @param positiveBtn
         * @param listener
         * @return
         */
        fun setPositiveButton(positiveBtn: String?, listener: OnClickListener?): Builder {
            positiveBtnListener = listener
            this.positiveBtn = positiveBtn
            return this
        }

        /**
         * “取消”按钮的点击事件
         *
         * @param negativeBtn
         * @param listener
         * @return
         */
        fun setNegativeButton(negativeBtn: String?, listener: OnClickListener?): Builder {
            negativeBtnListener = listener
            this.negativeBtn = negativeBtn
            return this
        }

        override fun create(): IOSMessageDialog {
            return IOSMessageDialog(this)
        }

    }

    interface OnButtonClickListener : BaseClickListener {
        /**
         * 确定
         */
        fun onConfirm()

        /**
         * 取消
         */
        override fun onCancel()
    }

    interface OnClickListener {
        fun onClick(dialog: Dialog?)
    }

    init {
        drawable = builder.drawable
        width = builder.width
        height = builder.height
        onButtonClickListener = builder.onButtonClickListener
        message = builder.message
        mContext = builder.contextWeakReference.get()
        textColor = builder.textColor
        textSize = builder.textSize
        title = builder.title
        positiveBtn = builder.positiveBtn
        negativeBtn = builder.negativeBtn
        positiveBtnListener = builder.positiveBtnListener
        negativeBtnListener = builder.negativeBtnListener
    }
}