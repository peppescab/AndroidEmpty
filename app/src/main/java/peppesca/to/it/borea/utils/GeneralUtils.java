package peppesca.to.it.borea.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import peppesca.to.it.borea.fragments.WeatherFragment;
import peppesca.to.it.borea.network.WeatherGetRequest;

/**
 * Created by PeppeSca on 31/05/2015.
 */
public class GeneralUtils {

    private static final String SHARED_PREFERENCE_NAME = "peppesca.to.it";
    private static final String KEY_WEATHER_CITY = "WeatherCityShared";
    private static final String WEATHER_CITY_DIVIDER = "#";
    private static SharedPreferences prefs;

    public static final void addWeatherCity(Activity act, final WeatherFragment weatherFragment) {
        LinearLayout layout = new LinearLayout(act);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(act);
        titleBox.setHint("Nome citta'");
        layout.addView(titleBox);

        AlertDialog.Builder alert = new AlertDialog.Builder(act);
        alert.setMessage("Inserisci una nuova citta'");

        alert.setView(layout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (!titleBox.getText().toString().isEmpty()) {
                    WeatherGetRequest.getJsonObjectFromUrl(titleBox.getText().toString(), weatherFragment);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();
    }

    public static ArrayList<WeatherCity> loadWeatherCities(Activity act) {


        prefs = act.getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String storedCities = prefs.getString(KEY_WEATHER_CITY, "");

        ArrayList<WeatherCity> ret = new ArrayList<>();

        String singleCity[] = storedCities.split(WEATHER_CITY_DIVIDER, -1);

        for (String str : singleCity) {
            if (!str.isEmpty())
                ret.add(JsonManager.convertJsonToWeather(str));
        }

        return ret;


    }

    public static boolean storeWeatherCities(Activity act, ArrayList<WeatherCity> citiesToStore) {
        prefs = act.getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String listWeatherOneString = "";
        for (WeatherCity weatherCity : citiesToStore) {
            listWeatherOneString += WEATHER_CITY_DIVIDER + JsonManager.convertWeatherToJson(weatherCity);
        }

        return prefs.edit().putString(KEY_WEATHER_CITY, listWeatherOneString).commit();
    }
}
