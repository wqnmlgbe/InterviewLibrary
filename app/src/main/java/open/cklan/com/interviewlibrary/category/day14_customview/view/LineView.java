package open.cklan.com.interviewlibrary.category.day14_customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * AUTHOR：lanchuanke on 17/9/27 14:34
 */
public class LineView extends View {
    Paint paint;
    int width;
    int height;
    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#000000"));
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(width);
        canvas.drawLine(width/2,0,width/2,height,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }
}
