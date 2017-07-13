package tw.com.walter.a3_column_listview;

/**
 * Created by root on 2017/1/4.
 */

public class Weather {

    public static final int NA = -1;
    public static final int SUNNY = 0;
    public static final int OVERCAST = 1;
    public static final int RAIN = 2;

    public String city = null;
    public int temperature = 0;
    public int sky = NA;

    public Weather(String city, int temperature, int sky) {

        this.city = city;
        this.temperature = temperature;
        this.sky = sky;
    }

    public String getCity() {
        return city;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getSkyResource() {

        switch (sky) {

            case SUNNY:
                // return sunny image drawable
                return R.drawable.sunny;

            case OVERCAST:
                // return overcast image drawable
                return R.drawable.overcast;

            case RAIN:
                // return rain image drawable
                return R.drawable.rain;
        }

        return 0;
    }
}
