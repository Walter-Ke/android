package tw.com.walter.a2_column_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView = null;
    TextView textView1, textView2 = null;
    ArrayList<HashMap<String,Object>> arrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.MyListView);
        arrayList = new ArrayList<HashMap<String, Object>>();

        for(int i=1 ; i<11 ; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle","this is titile" +i);
            map.put("ItemText","this is text");
            arrayList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                arrayList,
                R.layout.mylistview,
                new String[] {"ItemTitle","ItemText"},
                new int[] {R.id.ItemTitle, R.id.ItemText}
                );
        listView.setAdapter(simpleAdapter);
    }
}
