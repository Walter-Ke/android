package tw.com.walter.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInitial = (Button) findViewById(R.id.btnInitial);
        Button btnChange = (Button) findViewById(R.id.btnChange);
        Button btnShow = (Button) findViewById(R.id.btnShow);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);

        btnInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initial some data
                initialData();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // update Data to
                // "name: updated raw data in Form1 ,value:this is updated raw data in Form1"
                UpdateData(1, "updated raw data in Form1",
                        "this is updated raw data in Form1");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get data and show data in LogCat
                Infos datas = new Infos();

                datas = Getdata(1);
                Log.d("Form1", "vvv");
                Log.d("Form1", "id : " + datas.id + " name : " + datas.name
                        + " value : " + datas.value);
                Log.d("Form1", "vvv");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete data
                DeleteData(1);
            }
        });
    }

    public class Infos {
        public String id;
        public String name;
        public String value;
    }

    public void initialData() {
        // inserdata with
        // id: 1
        // name: Raw Data in Form1
        // value: this is Raw Data 1 in Form1
        this
                .InsertData(1, "Raw Data in Form1",
                        "this is Raw Data 1 in Form1");
        Toast.makeText(this, "Add initial data.", Toast.LENGTH_LONG);
    }

    public void InsertData(int id, String name, String value) {
        ContentValues values = new ContentValues();
         values.put(InfoProvider._ID,id);

        // getContentResolver().insert()
        getContentResolver().insert(InfoProvider.CONTENT_URI,values);
    }

    public void UpdateData(int id, String name, String value) {
        ContentValues editedValues = new ContentValues();
        editedValues.put(InfoProvider.NAME, name);
        editedValues.put(InfoProvider.VALUE, name);
        getContentResolver().update(Uri.parse("content://tw.com.walter.contentprovider/info" + id),editedValues,null,null);
    }

    public void DeleteData(int id) {
        getContentResolver().delete(Uri.parse("content://tw.com.walter.contentprovider/info/" + id ),null,null);
    }

    public Infos Getdata(int id) {

        Infos infos = new Infos();
        Uri getdata = Uri.parse("content://COM.TQC.GAD01/Info/"
                + String.valueOf(id));
        Cursor c = managedQuery(getdata,null,null,null,"_ID_asc");

        // get data procedure
        if (c.moveToFirst()) {
            do {
                infos.id = c.getString(c.getColumnIndex(InfoProvider._ID)) ;
                infos.name = c.getString(c.getColumnIndex(InfoProvider.NAME));
                infos.value = c.getString(c.getColumnIndex(InfoProvider.VALUE));
            } while (c.moveToNext());
        }
        return infos;
    }
}
