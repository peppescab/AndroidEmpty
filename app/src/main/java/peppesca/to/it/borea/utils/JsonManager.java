package peppesca.to.it.borea.utils;

import com.google.gson.Gson;

/**
 * Created by PeppeSca on 09/06/2015.
 */
public class JsonManager {

    public static String convertWeatherToJson(WeatherCity weatherCity) {
        Gson gson = new Gson();
        String jsonRet = gson.toJson(weatherCity);

        return jsonRet;
    }

    public static WeatherCity convertJsonToWeather(String stringVal) {
        Gson gson = new Gson();
        WeatherCity weathTmp = gson.fromJson(stringVal, WeatherCity.class);
        return weathTmp;

    }


}
