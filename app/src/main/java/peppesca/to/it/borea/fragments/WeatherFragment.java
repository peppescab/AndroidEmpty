package peppesca.to.it.borea.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hudomju.swipe.OnItemClickListener;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.SwipeableItemClickListener;
import com.hudomju.swipe.adapter.RecyclerViewAdapter;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import peppesca.to.it.borea.R;
import peppesca.to.it.borea.network.WeatherGetRequest;
import peppesca.to.it.borea.recyclerviewlist.WeatherAdapter;
import peppesca.to.it.borea.utils.GeneralUtils;
import peppesca.to.it.borea.utils.WeatherCity;
import peppesca.to.it.olympuslib.navigationdrawer.GeneralAbstractFragment;

/**
 * Created by PeppeSca on 27/05/2015.
 */
public class WeatherFragment extends GeneralAbstractFragment {

    private static final String TAG = WeatherFragment.class.getSimpleName();
    private WeatherAdapter myWeatherAdapater;
    private ArrayList<WeatherCity> listCitiesCache = new ArrayList();
    private WeatherFragment myInstance;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public String getTitle() {
        return "Weather";
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_drawer_weather;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        RecyclerView rvWeather = (RecyclerView) view.findViewById(R.id.rvWeather);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        //fill map if values not present
        myInstance = this;
        ifEmptyDefaultList();
        myWeatherAdapater = new WeatherAdapter(getActivity().getApplicationContext(), listCitiesCache);


        rvWeather.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());                 // Creating a layout Manager
        rvWeather.setLayoutManager(mLayoutManager);
        rvWeather.setAdapter(myWeatherAdapater);
//        rvWeather.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        com.melnykov.fab.FloatingActionButton floatButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.addWeatherCity(getActivity(), myInstance);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //reload values
                for (int i = 0; i < listCitiesCache.size(); i++) {
                    WeatherGetRequest.getJsonObjectFromUrl(listCitiesCache.get(i).getName(), myInstance);
                }
            }
        });

        final SwipeToDismissTouchListener<RecyclerViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new RecyclerViewAdapter(rvWeather),
                        new SwipeToDismissTouchListener.DismissCallbacks<RecyclerViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(RecyclerViewAdapter view, int position) {
                                listCitiesCache.remove(position);
                                myWeatherAdapater.notifyDataSetChanged();
                            }
                        });

        rvWeather.setOnTouchListener(touchListener);
        rvWeather.setOnScrollListener((RecyclerView.OnScrollListener) touchListener.makeScrollListener());
        rvWeather.addOnItemTouchListener(new SwipeableItemClickListener(getActivity(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (view.getId() == R.id.txt_delete) {
                            touchListener.processPendingDismisses();
                        } else if (view.getId() == R.id.txt_undo) {
                            touchListener.undoPendingDismiss();
                        } else { // R.id.txt_data
//                            Toast.makeText(get, "Position " + position, LENGTH_SHORT).show();
                        }
                    }
                }));


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GeneralUtils.storeWeatherCities(getActivity(), listCitiesCache);
    }

    private void ifEmptyDefaultList() {

        listCitiesCache = GeneralUtils.loadWeatherCities(getActivity());

        if (listCitiesCache.size() < 1) {
            listCitiesCache.add(new WeatherCity("Locri"));
            listCitiesCache.add(new WeatherCity("Torino"));
            listCitiesCache.add(new WeatherCity("Firenze"));
            listCitiesCache.add(new WeatherCity("Berlino"));
        }

        for (int i = 0; i < listCitiesCache.size(); i++) {
            WeatherGetRequest.getJsonObjectFromUrl(listCitiesCache.get(i).getName(), myInstance);
        }
    }

    public void addCityToList(String cityKey, WeatherCity cityValue) {
        for (WeatherCity weatIt : listCitiesCache)
            if (weatIt.getName().equals(cityKey)) {
                listCitiesCache.remove(weatIt);
                break;
            }

        listCitiesCache.add(cityValue);
        myWeatherAdapater.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
