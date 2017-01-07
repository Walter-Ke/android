package tw.com.walter.overlapping_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

;


public class OverlappedLayout extends LinearLayout {
	
	@SuppressWarnings("unused")

	private int x, y;
	
	private Paint paint;

	public OverlappedLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init(){
		
		paint = new Paint();
		
		paint.setColor(Color.BLUE);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {

		super.dispatchDraw(canvas);
		canvas.drawCircle(x,y,30,paint);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		x = (int)ev.getX();
		y = (int)ev.getY();
		this.invalidate();
		return true;

		//return super.dispatchTouchEvent(ev);
	}

	//	public OverlappedLayout() {
//		init();
//	}
}
