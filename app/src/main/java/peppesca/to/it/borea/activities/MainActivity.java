package peppesca.to.it.borea.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import peppesca.to.it.borea.fragments.WeatherFragment;
import peppesca.to.it.borea.fragments.TimeFragment;
import peppesca.to.it.olympuslib.navigationdrawer.GeneralAbstractFragment;
import peppesca.to.it.olympuslib.navigationdrawer.NavigationDrawerManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavigationDrawerManager.useGeneralNavigationStructure(this, fillFragments());

    }

    private GeneralAbstractFragment[] fillFragments() {
        GeneralAbstractFragment[] mGeneralFrags = new GeneralAbstractFragment[]{
                new WeatherFragment(),
                new TimeFragment()
        };

        return mGeneralFrags;
    }


}
