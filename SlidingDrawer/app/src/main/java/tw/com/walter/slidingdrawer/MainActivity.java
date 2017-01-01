package tw.com.walter.slidingdrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean locked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        final SlidingDrawer mSlidingDrawer = (SlidingDrawer) findViewById(R.id.slidingdrawer);

        mSlidingDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {

            @Override
            public void onScrollEnded() {
                Log.i("SlidingDrawer", "onScrollEnded slider");

                // Check SlidingDrawer is open or not.
                if(mSlidingDrawer.isOpened()){

                    locked = false;
                    Log.i("","lock is open!");
                }
                else{

                    locked = true;
                }
            }

            @Override
            public void onScrollStarted() {
                Log.i("SlidingDrawer", "onScrollStarted slider");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if SlidingDrawer open, don't show toast.
                if(!locked)
                {
                    // make a toast message
                    mMakeTextToast("Btn1 is clicked",true);
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make a toast message
                mMakeTextToast("Btn2 is clicked",true);
            }
        });
    }

    public void mMakeTextToast(String str, boolean isLong) {
        if (isLong == true) {
            // make a toast message
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        } else {
            // make a toast message
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }
}
