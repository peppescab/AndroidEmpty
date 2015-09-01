package peppesca.to.it.borea.network;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import peppesca.to.it.borea.fragments.WeatherFragment;
import peppesca.to.it.borea.utils.AppController;
import peppesca.to.it.borea.utils.JsonManager;
import peppesca.to.it.borea.utils.WeatherCity;

/**
 * Created by PeppeSca on 29/05/2015.
 */
public class WeatherGetRequest {

    private static final String TAG = WeatherGetRequest.class.getSimpleName();
    private static final String OPEN_WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static void getJsonObjectFromUrl(final String city, final WeatherFragment weatherFragment) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                OPEN_WEATHER_API + city, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {

                    WeatherCity weathTmp = JsonManager.convertJsonToWeather(response.toString());
                    if (weathTmp == null || !weathTmp.getCod().equals("200")) {
                        Toast.makeText(weatherFragment.getActivity(), "Error in searching " + city, Toast.LENGTH_SHORT).show();
                    } else {
                        weatherFragment.addCityToList(city, weathTmp);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(weatherFragment.getActivity(), "Error in searching " + city, Toast.LENGTH_SHORT).show();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}
