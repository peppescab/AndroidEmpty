package peppesca.to.it.borea.recyclerviewlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import peppesca.to.it.borea.R;
import peppesca.to.it.borea.utils.WeatherCity;


/**
 * Created by PeppeSca on 29/03/2015.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<WeatherCity> weatherCityList;
    private Context ctx;

    public WeatherAdapter(Context ctx, ArrayList<WeatherCity> weatherCities) {
        this.weatherCityList = weatherCities;
        this.ctx = ctx;
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_weather, parent, false); //Inflating the layout
        ViewHolder vhItem = new ViewHolder(v, position); //Creating ViewHolder and passing the object of type view
        return vhItem; // Returning the created object

    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {


        WeatherCity res = weatherCityList.get(position);

        if (res.getCod() == null)
            return;

        holder.tvCityName.setText(res.getName());
        Picasso.with(ctx).load(res.getIconAddress()).skipMemoryCache().into(holder.imgWeather);
        holder.tvCityTemp.setText(Html.fromHtml(res.getTemperatureInCelsius() + "&bull;C"));
//        holder.tvCityTemp.setText(res.getTemperatureInCelsius() + " &cent; C");
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        int numValid = 0;
        for (WeatherCity weathCity : weatherCityList) {
            if (weathCity.getCod() != null)
                numValid++;
        }
        //return effective numbers of values
        return numValid; // the number of items in the list will be +1 the titles including the header view.
    }

    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgWeather;
        TextView tvCityName;
        TextView tvCityTemp;

        public ViewHolder(View itemView, int position) {
            super(itemView);

            tvCityName = (TextView) itemView.findViewById(R.id.tvWeatherCity); // Creating TextView object with the id of textView from item_row.xml
            imgWeather = (ImageView) itemView.findViewById(R.id.imgWeather);// Creating ImageView object with the id of ImageView from item_row.xml
            tvCityTemp = (TextView) itemView.findViewById(R.id.tvTemperatureCity); // Creating TextView object with the id of textView from item_row.xml

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View itemView) {
//                    myActivity.replaceFragmentIn(Holderid);
//                }
//            });
        }


    }

}

