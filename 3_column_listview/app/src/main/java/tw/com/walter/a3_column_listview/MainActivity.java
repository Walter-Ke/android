package tw.com.walter.a3_column_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list);

        ArrayList<Weather> weatherList = new ArrayList<Weather>();

        // Create Weather object add  to weatherList
        weatherList.add(0,new Weather("台北",23,Weather.OVERCAST));
        weatherList.add(0,new Weather("桃園",26,Weather.OVERCAST));
        weatherList.add(0,new Weather("新竹",29,Weather.SUNNY));
        weatherList.add(0,new Weather("台中",27,Weather.RAIN));

        // assign adapter to listAdapter
        WeatherAdapter weatherAdapter = new WeatherAdapter(this, weatherList);
        listView.setAdapter(weatherAdapter);

    }
}
