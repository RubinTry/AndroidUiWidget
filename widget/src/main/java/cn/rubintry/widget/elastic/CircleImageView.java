package cn.rubintry.widget.elastic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import cn.rubintry.widget.R;

/**
 * @author logcat
 */
public class CircleImageView extends View {
    private Path circlePath = new Path();
    private Paint paint = new Paint();
    private Bitmap picture;
    private BitmapDrawable pictureDrawable;
    private int scaleMode;
    private int backgroundId;
    private float paddingLeft;
    private float paddingRight;

    /**
     * 圆图半径
     */
    private float radius;


    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context , attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        backgroundId = typedArray.getResourceId(R.styleable.CircleImageView_background , 0);
        String typeName = getResources().getResourceTypeName(backgroundId);
        pictureDrawable = (BitmapDrawable) ContextCompat.getDrawable(context , backgroundId);
        scaleMode = typedArray.getInt(R.styleable.CircleImageView_scaleMode , 0);
        paddingLeft = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_paddingLeft , 0);
        paddingRight = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_paddingRight , 0);
        typedArray.recycle();
    }

    private Bitmap scale(Bitmap bitmap , int newWidth , int newHeight , float paddingLeft , float paddingRight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        matrix.preScale(scaleWidth , scaleHeight);
        return Bitmap.createBitmap(bitmap , 0, 0 , width, height , matrix , false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = w / 2f;

        circlePath.addCircle(w / 2f , h / 2f , radius , Path.Direction.CW);



        switch (scaleMode){
            case 0:
                //none
                picture = pictureDrawable.getBitmap();
                break;
            case 1:
                //fitCenter
                Bitmap bitmap = pictureDrawable.getBitmap();
                picture = scale(bitmap , getWidth() , getHeight() , paddingLeft , paddingRight);
                if(!bitmap.isRecycled()){
                    bitmap.isRecycled();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(picture == null){
            return;
        }
        paint.reset();
        canvas.clipPath(circlePath);

        canvas.drawBitmap(picture , 0 , 0 , paint);


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(picture != null && !picture.isRecycled()){
            picture.recycle();
        }
    }
}
