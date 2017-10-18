package open.cklan.com.interviewlibrary.category.day14_customview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

/**
 * AUTHOR：lanchuanke on 17/10/18 08:40
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private static final int dividerHeight=5;

    private Paint paint;
    StickyGroupInterface groupInterface;
    public StickyItemDecoration(StickyGroupInterface stickyGroupInterface) {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(dividerHeight);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#EEEEEE"));
        groupInterface=stickyGroupInterface;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position=parent.getChildAdapterPosition(view);
        if(isFirstItemInGroup(position)){
            outRect.top=groupInterface.getGroupHeight(position);
        }else{
            outRect.top=dividerHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount=parent.getChildCount();
        int left=parent.getLeft();
        for (int i = 0; i < childCount; i++) {
            //根据position获取View
            View child=parent.getChildAt(i);
            int position=parent.getChildAdapterPosition(child);
            if(i!=0){
                if(!isFirstItemInGroup(position)) {
                    c.drawRect(left,child.getTop()-dividerHeight,parent.getRight(),child.getTop(),paint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount=parent.getChildCount();
        int left=parent.getLeft();
        int right=parent.getRight();
        for (int i = 0; i < childCount; i++) {
            //根据position获取View
            View child=parent.getChildAt(i);
            int position=parent.getChildAdapterPosition(child);
            if(i!=0){
                if(isFirstItemInGroup(position)) {
                    int height=groupInterface.getGroupHeight(position);
                    View groupView = groupInterface.getGroupView(position);
                    drawGroup(c, left, right, child.getTop()-height,height, groupView);
                }
            }else{
                int top=parent.getPaddingTop();
                int height=groupInterface.getGroupHeight(position);
                if(isLastItemInGroup(position,childCount)){
                    int measureTop=child.getBottom()-height;
                    top=Math.min(top,measureTop);
                }
                View groupView = groupInterface.getGroupView(position);
                drawGroup(c,left,right,top,height,groupView);
            }
        }
    }

    private void drawGroup(Canvas c, int left, int right, int top, int groupHeight,View groupView) {
        if(groupView==null)return;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, groupHeight);
        groupView.setLayoutParams(layoutParams);
        groupView.setDrawingCacheEnabled(true);
        groupView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //指定高度、宽度的groupView
        groupView.layout(0, 0, right,groupHeight);
        groupView.buildDrawingCache();
        Bitmap bitmap = groupView.getDrawingCache();
        c.drawBitmap(bitmap, left, top, null);
    }

    private boolean isFirstItemInGroup(int position){
        if(position==0)return true;
        String currentGroupName=groupInterface.getGroupName(position);
        String preGroupName=groupInterface.getGroupName(position-1);
        return !TextUtils.equals(currentGroupName,preGroupName);
    }

    private boolean isLastItemInGroup(int position,int childCount){
        if(position==childCount-1)return true;
        String currentGroupName=groupInterface.getGroupName(position);
        String nextGroupName=groupInterface.getGroupName(position+1);
        return !TextUtils.equals(currentGroupName,nextGroupName);
    }


    public interface StickyGroupInterface{
        View getGroupView(int position);
        String getGroupName(int position);
        int getGroupHeight(int position);

    }
}
