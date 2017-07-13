package tw.com.walter.tranmonitortouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame1;
    private FrameLayout frame2;

    private ItemLine itemLine1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame1 = (FrameLayout)findViewById(R.id.frame1);
        frame2 = (FrameLayout)findViewById(R.id.frame2);

        //add ItemLine view to frame1
        itemLine1 = new ItemLine(this, 0, 1);
        frame1.addView(itemLine1);

        //implement frame2 setOnTouchListener
        //double the coordinate.
        //bypass frame2 touch event to frame1's view
        //hint:dispatchtouchevent
        frame2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int action = motionEvent.getAction();
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();

                switch (action){

                    case MotionEvent.ACTION_DOWN:


                        break;

                    case MotionEvent.ACTION_MOVE:


                        break;

                    case MotionEvent.ACTION_UP:


                        break;
                }

                motionEvent.setLocation(x*2, y*2);

                itemLine1.dispatchTouchEvent(motionEvent);

                return true;
            }
        });
    }
}
