package com.example.tp5;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static final String ACTION_SIMPLEAPPWIDGET = "ACTION_BROADCASTWIDGETSAMPLE";
    private static int mCounter = 0;
    private LocationManager locationManager;
    private static int PERMISSION_CODE = 1;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId) {
        String cityname ="Tunis";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.weatherapi.com/v1/forecast.json?key=8384ce731c8c416892e111040231802&q="+cityname+"&days=1&aqi=yes&alerts=yes").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("tag2",e.toString());
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);

                    String temperature = json.getJSONObject("current").getString("temp_c")+"°c";
                    Log.e("tag2",temperature);
                    // Construct the RemoteViews object
                    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
                    views.setTextViewText(R.id.tvWidget, temperature);
                    views.setTextViewText(R.id.cityname, cityname);
                    // This time we dont have widgetId. Reaching our widget with that way.
                    ComponentName appWidget = new ComponentName(context, NewAppWidget.class);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    appWidgetManager.updateAppWidget(appWidget, views);
                } catch (JSONException e) {}
            }
        });

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_SIMPLEAPPWIDGET.equals(intent.getAction())) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setTextViewText(R.id.tvWidget, "");
            // This time we dont have widgetId. Reaching our widget with that way.
            ComponentName appWidget = new ComponentName(context, NewAppWidget.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, views);
        }
    }
}