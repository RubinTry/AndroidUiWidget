package cn.rubintry.widget.elastic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * @author logcat
 * 带有下拉放大特性的组件
 */
public class ElasticView extends FrameLayout {
    private static final String TAG = "ElasticView";
    private View headerView;
    private int mHeaderWidth;
    private int mHeaderHeight;
    private boolean mHeaderSizeReady;
    private OnReadyPullListener onReadyPullListener;
    private float mInitialX;
    private float mInitialY;
    private boolean mIsBeingDragged;
    /**
     * 最大头部下拉高度
     */
    private int mMaxPullHeight = ScreenUtils.getScreenHeight(getContext()) / 4;

    public ElasticView(Context context) {
        super(context);
    }

    public ElasticView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        //去除边界阴影
        setHorizontalFadingEdgeEnabled(false);
        setVerticalFadingEdgeEnabled(false);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }


    public void setOnReadyPullListener(OnReadyPullListener onReadyPullListener) {
        this.onReadyPullListener = onReadyPullListener;
    }

    private void init() {
        mHeaderSizeReady = false;
    }

    public ElasticView setHeader(View header) {
        this.headerView = header;
        header.post(new Runnable() {
            @Override
            public void run() {
                mHeaderHeight = headerView.getHeight();
                mHeaderWidth = headerView.getWidth();
                mHeaderSizeReady = true;
            }
        });
        return this;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isHeaderReady() && isReady()) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
//                    log("onInterceptTouchEvent DOWN");
                    mInitialX = ev.getX();
                    mInitialY = ev.getY();
                    mIsBeingDragged = false;
                    break;
                case MotionEvent.ACTION_MOVE:

                    float diffY = ev.getY() - mInitialY;
                    float diffX = ev.getX() - mInitialX;
                    if (diffY > 0 && diffY / Math.abs(diffX) > 2) {
                        mIsBeingDragged = true;
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if ( isHeaderReady() && isReady()) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    if (mIsBeingDragged) {
                        float diffY = ev.getY() - mInitialY;
                        changeHeader((int) diffY);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    if (mIsBeingDragged) {
                        resetHeader();
                        return true;
                    }
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void resetHeader() {
        PullAnimatorUtil.resetAnimator(headerView, mHeaderHeight, mHeaderWidth);
    }

    private void changeHeader(int offsetY) {
        PullAnimatorUtil.pullAnimator(headerView, mHeaderHeight, mHeaderWidth, offsetY, mMaxPullHeight);
    }

    private boolean isReady() {
        return onReadyPullListener != null && onReadyPullListener.isReady();
    }

    private boolean isHeaderReady() {
        return headerView != null && mHeaderSizeReady;
    }
}
