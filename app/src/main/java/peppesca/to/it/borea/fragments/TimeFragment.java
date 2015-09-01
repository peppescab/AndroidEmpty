package peppesca.to.it.borea.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import peppesca.to.it.borea.R;
import peppesca.to.it.olympuslib.navigationdrawer.GeneralAbstractFragment;

/**
 * Created by PeppeSca on 27/05/2015.
 */
public class TimeFragment extends GeneralAbstractFragment {

    @Override
    public String getTitle() {
        return "Time";
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_drawer_detail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time, container, false);
//        LinearLayout llMy = (LinearLayout) v.findViewById(R.id.ll_fragments);
//        llMy.setBackgroundColor(Color.RED);
        return v;
    }
}
