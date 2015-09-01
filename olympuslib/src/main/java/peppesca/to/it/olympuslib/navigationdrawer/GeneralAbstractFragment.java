package peppesca.to.it.olympuslib.navigationdrawer;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PeppeSca on 09/04/2015.
 */
public abstract class GeneralAbstractFragment extends Fragment {

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        position = pos;
    }

    public abstract String getTitle();

    public abstract int getIcon();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getTitle());
//        getActivity().setTitle(getTitle());
        super.onPrepareOptionsMenu(menu);
    }
}
