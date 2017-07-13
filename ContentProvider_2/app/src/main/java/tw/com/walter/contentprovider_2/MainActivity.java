package tw.com.walter.contentprovider_2;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String VALUE = "value";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShow = (Button) findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get data and show data in LogCat
                Infos datas = new Infos();

                datas = Getdata(1);
                Log.d("Form2", "vvv");
                Log.d("Form2", "id : " + datas.id + " name : " + datas.name
                        + " value : " + datas.value);
                Log.d("Form2", "vvv");
            }
        });
    }

    public class Infos {
        public String id;
        public String name;
        public String value;
    }

    public Infos Getdata(int id) {

        Infos infos = new Infos();
        Uri getdata = Uri.parse("content://tw.com.walter.contentprovider/Info/"
                + String.valueOf(id));
        Cursor c = managedQuery(getdata,null,null,null,"_ID asc");

        // get data procedure
        if (c.moveToFirst()) {
            do {
                infos.id = c.getString(c.getColumnIndex(_ID));
                infos.name = c.getString(c.getColumnIndex(NAME));
                infos.value = c.getString(c.getColumnIndex(VALUE));
            } while (c.moveToNext());
        }
        return infos;
    }
}
