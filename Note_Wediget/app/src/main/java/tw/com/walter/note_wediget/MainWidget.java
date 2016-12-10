package tw.com.walter.note_wediget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTime(context,appWidgetManager),1,1000);

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private class myTime extends TimerTask{

        AppWidgetManager appWidgetManager1 = null;
        RemoteViews remoteViews = null;
        ComponentName componentName = null;

        long currentTimeMills;
        Calendar calendar= Calendar.getInstance();
        String note = "test";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime;

        public myTime(Context context, AppWidgetManager appWidgetManager){

            appWidgetManager1 = appWidgetManager;
            remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_main);
            componentName = new ComponentName(context, MainWidget.class);
        }

        @Override
        public void run() {

            currentTimeMills = System.currentTimeMillis();
            calendar.setTimeInMillis(currentTimeMills);
            currentTime = new Date(currentTimeMills);

            remoteViews.setTextViewText(R.id.widget_dateView,"Date:" + simpleDateFormat.format(currentTime));
            remoteViews.setTextViewText(R.id.widget_timeView,"Time:" + simpleTimeFormat.format(currentTimeMills));

            appWidgetManager1.updateAppWidget(componentName,remoteViews);

        }
    }

    public void onDelete(Context context, int[] appWidgetIds){
        super.onDeleted(context,appWidgetIds);
    }
}
