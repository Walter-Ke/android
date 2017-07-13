package tw.com.walter.a3_column_listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends BaseAdapter {

    private Context context;
    private List<Weather> weatherList;

    //build the constructor
    public WeatherAdapter(MainActivity mainActivity, ArrayList<Weather> weatherList) {

        this.context = mainActivity;
        this.weatherList = weatherList;
    }



    public int getCount() {
        return weatherList.size();
    }

    public Object getItem(int position) {
        return weatherList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Weather weather = weatherList.get(position);

        return new WeatherAdapterView(this.context, weather);
    }

}
