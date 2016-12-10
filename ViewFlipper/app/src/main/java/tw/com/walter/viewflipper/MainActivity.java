package tw.com.walter.viewflipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class MainActivity extends AppCompatActivity{
    private ViewFlipper viewFlipper = null;
    private Button BtnPrevious = null;
    private Button BtnNext= null;

    private Animation AniSlideLeftIn = null;  //從螢幕左邊進
    private Animation AniSlideLeftOut = null; //從螢幕左邊出
    private Animation AniSlideRightIn = null; //從螢幕右邊進
    private Animation AniSlideRightOut = null;//從螢幕右邊出

    private GestureDetector gestureDetector= null;

    private float preX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewFlipper =(ViewFlipper) findViewById(R.id.viewFlipper);

        BtnPrevious =  (Button) findViewById(R.id.previousBtn);
        BtnNext=  (Button) findViewById(R.id.nextBtn);




        //specify the second view
        viewFlipper.showNext();

        //initial Animation object
        AniSlideLeftIn =  AnimationUtils.loadAnimation(this,R.anim.slide_left_in);
        AniSlideLeftOut =  AnimationUtils.loadAnimation(this, R.anim.slide_left_out);
        AniSlideRightIn =  AnimationUtils.loadAnimation(this, R.anim.slide_right_in);
        AniSlideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_right_out);


        BtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("viewFlipper previous");

                viewFlipper.setInAnimation(AniSlideRightIn);
                viewFlipper.setOutAnimation(AniSlideRightOut);

                if(!isLeftEdge()) {

                    viewFlipper.showPrevious();
                    currentPageToast(viewFlipper.getDisplayedChild());
                }

            }
        });

        BtnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                System.out.println("viewFlipper next");

                viewFlipper.setInAnimation(AniSlideLeftIn);
                viewFlipper.setOutAnimation(AniSlideLeftOut);

                if(!isRightEdge()) {

                    viewFlipper.showNext();
                    currentPageToast(viewFlipper.getDisplayedChild());
                }
            }
       });

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                System.out.println("viewFlipper touch listener");
                return false;
            }
        });

    }


    private boolean isRightEdge(){

        return viewFlipper.getDisplayedChild()== (viewFlipper.getChildCount()-1);
    }


    private boolean isLeftEdge(){

        return viewFlipper.getDisplayedChild() == 0;
    }

    private void currentPageToast(int page){

        String msg = null;

        switch(page){
            case 0:
                msg = "o..";
                break;
            case 1:
                msg = ".o.";
                break;
            case 2:
                msg = "..o";
                break;
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        System.out.println("Activity onTouchEvent");

        switch(event.getAction()){

        case MotionEvent.ACTION_DOWN:

            preX = event.getX();

            System.out.println("preX:" + preX);

            break;

        case MotionEvent.ACTION_UP:

            float curX = event.getX();

            System.out.println("curX:" + curX);

            if(preX > curX){  //move to right

                BtnNext.callOnClick();

            }else if(preX < curX){//move to left

                BtnPrevious.callOnClick();
            }
            break;
        }

        return super.onTouchEvent(event);
    }

}
