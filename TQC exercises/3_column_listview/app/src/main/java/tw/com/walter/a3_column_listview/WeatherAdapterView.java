package tw.com.walter.a3_column_listview;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by root on 2017/1/4.
 */

public class WeatherAdapterView extends LinearLayout {

    public WeatherAdapterView(Context context, Weather weather) {

        super(context);

        this.setOrientation(HORIZONTAL);

        // city
        // let city name on the left
        LinearLayout.LayoutParams cityLayoutParams = new LinearLayout.LayoutParams(
                100, LinearLayout.LayoutParams.WRAP_CONTENT);
        cityLayoutParams.setMargins(1, 10, 1, 10);
        TextView citys = new TextView(context);
        citys.setText(weather.getCity());
        citys.setTextSize(14f);
        citys.setTextColor(Color.RED);
        addView(citys, cityLayoutParams);


        // temperature
        // let temperature in the middle
        // setTextSize(14f)
        LinearLayout.LayoutParams tempLayoutParams =
                    new LinearLayout.LayoutParams(40, LayoutParams.WRAP_CONTENT);
        tempLayoutParams.setMargins(1,10,1,10);
        TextView temp = new TextView(context);
        temp.setText(Integer.toString(weather.getTemperature()));
        temp.setTextSize(14f);
        temp.setTextColor(Color.RED);
        addView(temp, tempLayoutParams);


        // weather
        // let weather image place in right side
        LinearLayout.LayoutParams weathLayoutParams =
                new LinearLayout.LayoutParams(40,LayoutParams.WRAP_CONTENT);
        weathLayoutParams.setMargins(140,10,10,10);
        ImageView img_weather = new ImageView(context);
        img_weather.setImageResource(weather.getSkyResource());
        addView(img_weather,weathLayoutParams);
    }
}

