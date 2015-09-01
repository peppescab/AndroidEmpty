package peppesca.to.it.borea.utils;

import java.util.List;

/**
 * Created by PeppeSca on 29/05/2015.
 */
public class WeatherCity {

    private final static String ICON_ADDR = "http://openweathermap.org/img/w/";
    List<Weather> weather;
    Main main;
    Sys sys;
    private String name;
    private String cod;

    public WeatherCity(String name) {
        this.name = name;
        cod = null;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //FOR GSON
//    {
//        "coord":{"lon":-0.13,"lat":51.51},
//        "sys":{"message":0.0221,"country":"GB","sunrise":1433389586,"sunset":1433448677},
//        "weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],
//        "base":"stations",
//         "main":{"temp":290.191,"temp_min":290.191,"temp_max":290.191,"pressure":1026.11,"sea_level":1033.85,"grnd_level":1026.11,"humidity":51},
//        "wind":{"speed":2.96,"deg":110.001},
//        "clouds":{"all":24},"dt":1433456635,"id":2643743,
//            "name":"London",
//            "cod":200}

    static class Sys {
        String country;
    }

    static class Weather {
        String description;
        String icon;
    }

    static class Main {
        float temp;
    }


    public float getTemperatureInCelsius() {
        float temp = main.temp - 273.15f;
        return Math.round(temp * 100) / 100.0f;
    }

    public String getIconAddress() {
        return ICON_ADDR + weather.get(0).icon + ".png";
    }

    public String getDescription() {
        if (weather != null && weather.size() > 0)
            return weather.get(0).description;
        return null;
    }
}

