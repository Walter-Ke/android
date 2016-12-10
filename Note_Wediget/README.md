(1). AndroidManifest.xml

<receiver android:name=".appwidget">
	<intent-filter>
		<action android:name="android.appwidget.action.APPWIDGET.UPDATE"/>
	</intent-filter>
	<meta-data android:name="android.appwidget.provider"
			android:resource="@xml/appwidget_provider"/>
</receiver>

(2). xml\appwidget-provider.xml

<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
	android:minHeight="72dp"
	android:minWidth="146dp"
	android:previewImg="@mipmap/ic_launcher"
	android:updatePeriodMills="0"
	android:initialLayout="@layout/activity_main"/>

(3). main.java
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[]appWidgetIds){
		
	}	

	@Override
	public void onDelete(Context context, int[] appWidgetIds){
		super.onDelete(context,appWidgetIds);