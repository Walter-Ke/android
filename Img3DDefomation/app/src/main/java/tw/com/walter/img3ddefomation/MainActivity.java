package tw.com.walter.img3ddefomation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private Bitmap myBmp;

    public boolean draw = false;
    public float XX = 0;
    public float YY = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyView myView = new MyView(this);
        setContentView(myView);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public class MyView extends View {




        public MyView(Context context) {
            super(context);

            // load bitmap
            myBmp = Bitmap.createBitmap(100, 70, Bitmap.Config.ARGB_8888);

        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (draw) {
                camera = new Camera();
                camera.translate(0, 0, 0);
                camera.rotateX(20);
                camera.rotateY(20);


                Matrix matrix3D = new Matrix();
                camera.getMatrix(matrix3D);
                canvas.translate(0, 0);
                canvas.concat(matrix3D);


                canvas.drawBitmap(myBmp, XX - 90, YY - 80, null);  //將bitmap繪在canvas上

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // paint when action_down
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            draw = true;
            //stop painting when actin_up
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            draw = false;
        }

        // get touched X, Y
        XX = event.getX();
        YY = event.getY();



        // refresh view


        return true;

    }
}







