package open.cklan.com.interviewlibrary.category.day14_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * AUTHOR：lanchuanke on 17/10/13 18:23
 */
public class SignatureView extends View {
    private static final int STROKE_WIDTH=20;
    Paint paint;
    Path path;
    RectF rectF;
    int lastPositionX;
    int lastPositionY;
    public SignatureView(Context context) {
        this(context,null);
    }

    public SignatureView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SignatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        path=new Path();
        rectF=new RectF();
    }

    private void initPaint() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
        int positionX= (int) event.getX();
        int positionY= (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(positionX,positionY);
                lastPositionX=positionX;
                lastPositionY=positionY;
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                buildInvalidateRectFNoStrokeWidth(positionX,positionY);
                int historySize = event.getHistorySize();
                for (int i=0;i<historySize;i++){
                    float historicalX= event.getHistoricalX(i);
                    float historicalY= event.getHistoricalY(i);
                    path.lineTo(historicalX,historicalY);
                    changeInvalidateRectFNoStrokeWidth(historicalX,historicalY);
                }
                path.lineTo(positionX,positionY);
                break;
            default:
                return false;
        }
        Rect rect=changeInvalidateRectFWidthStrokeWidth();
        invalidate(rect);
        return true;
    }

    private Rect changeInvalidateRectFWidthStrokeWidth() {
        Rect rect=new Rect();
        rect.left= (int) (rectF.left-STROKE_WIDTH/2);
        rect.top= (int) (rectF.top-STROKE_WIDTH/2);
        rect.right= (int) (rectF.right+STROKE_WIDTH/2);
        rect.bottom= (int) (rectF.bottom+STROKE_WIDTH/2);
        return rect;
    }

    private void changeInvalidateRectFNoStrokeWidth(float historicalX, float historicalY) {
        rectF.left=Math.min(rectF.left,historicalX);
        rectF.top=Math.min(rectF.top,historicalY);
        rectF.right=Math.max(rectF.right,historicalX);
        rectF.bottom=Math.max(rectF.bottom,historicalY);
    }

    private void buildInvalidateRectFNoStrokeWidth(int positionX, int positionY) {
        rectF.left=Math.min(lastPositionX,positionX);
        rectF.top=Math.min(lastPositionY,positionY);
        rectF.right=Math.max(lastPositionX,positionX);
        rectF.bottom=Math.max(lastPositionY,positionY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }
}
