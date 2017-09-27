package open.cklan.com.interviewlibrary.category.day9_pathmeasure;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import open.cklan.com.interviewlibrary.utils.LogUtil;

/**
 * AUTHOR：lanchuanke on 17/9/21 11:12
 */
public class PathMeasureView extends View {
    private Path circlePath;
    private Path destPath;
    private Paint paint;
    private PathMeasure pathMeasure;
    private ValueAnimator valueAnimator;
    private float animatedValue;
    private float circleLength;

    private float width=200.0f;
    private float height=200.0f;
    private int strokeWidth=8;
    private float[] tan=new float[2];
    private float[] pos=new float[2];

    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode){
            case MeasureSpec.AT_MOST:
                float width=this.width+getPaddingLeft()+getPaddingRight();
                this.width=Math.min(width,widthSize);
                break;
            case MeasureSpec.EXACTLY:
                this.width=widthSize;
                break;
        }

        switch (heightMode){
            case MeasureSpec.AT_MOST:
                float height=this.height+getPaddingBottom()+getPaddingTop();
                this.height=Math.min(height,heightSize);
                break;
            case MeasureSpec.EXACTLY:
                this.height=heightSize;
                break;
        }
        circlePath.reset();
        circlePath.addCircle(this.width/2,this.height/2,(this.width-this.strokeWidth*2)/2, Path.Direction.CW);
        pathMeasure.setPath(circlePath,true);
        circleLength=pathMeasure.getLength();

        setMeasuredDimension(MeasureSpec.makeMeasureSpec((int) this.width,widthMode),
                MeasureSpec.makeMeasureSpec((int) this.height,heightMode));

    }

    private void init(Context context) {
        //初始化Paint
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#B68857"));
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        //初始化circlePath pathMeasure destPath
        circlePath=new Path();
        pathMeasure=new PathMeasure();
        destPath=new Path();

        //初始化ValueAnimator
        valueAnimator=ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue=(float)animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        destPath.reset();
        destPath.lineTo(0,0);//硬件加速的bug
//        float start=0; //连续画圆
        float stop=animatedValue*circleLength;
        float start=stop-(0.5f-Math.abs(animatedValue-0.5f))*circleLength;
        pathMeasure.getSegment(start,stop,destPath,true);
        canvas.drawPath(destPath,paint);
    }

//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        pathMeasure.getPosTan(circleLength*animatedValue,pos,tan);
//        canvas.drawPath(circlePath,paint);
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
//        canvas.drawCircle(pos[0],pos[1],5,paint);
//    }
}
