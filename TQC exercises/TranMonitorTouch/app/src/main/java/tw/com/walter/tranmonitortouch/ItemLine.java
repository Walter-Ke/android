package tw.com.walter.tranmonitortouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class ItemLine extends View {

	boolean drawGlow = false;
	// this is the pixel coordinates of the screen
	float glowX = 0;
	float glowY = 0;
	// this is the radius of the circle we are drawing
	float radius = 20;

	int shift;
	int scaleup;

	public ItemLine(Context context, int padding, int scale) {
		super(context);
		shift = padding;
		scaleup = scale;
	}

	Paint paint = new Paint();
	{
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setAlpha(50);
	};

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		// Draw the circle when screen touched
		/*
		 * if (touched) {
		 * 
		 * canvas.drawCircle(); }
		 */
		if(drawGlow){
			canvas.drawCircle(glowX,glowY,radius,paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// when screen touched, draw the circle
		// process touched coordinate to draw circle
		// trigger the view to redraw
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			drawGlow=true;
		}else if(event.getAction()==MotionEvent.ACTION_UP){
			drawGlow=false;
		}
		glowX = event.getX()*scaleup;
		glowY = event.getY()*scaleup;
		this.invalidate();
		return true;
	}
}
