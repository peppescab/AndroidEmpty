package peppesca.to.it.olympuslib.navigationdrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import peppesca.to.it.olympuslib.R;


/**
 * Created by PeppeSca on 29/03/2015.
 */
public class MyNavigationDrawerAdapter extends RecyclerView.Adapter<MyNavigationDrawerAdapter.ViewHolder> {

    private static NavigationDrawerManager myNavDrawMan;
    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java
    private GeneralAbstractFragment[] fragmentsDrawer;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public MyNavigationDrawerAdapter(NavigationDrawerManager myNavDrawMan, GeneralAbstractFragment [] fragmentsDrawer) {
        this.fragmentsDrawer = fragmentsDrawer;
        this.myNavDrawMan = myNavDrawMan;
        mNavTitles = new String[fragmentsDrawer.length];
        mIcons = new int[fragmentsDrawer.length];
        for (int i=0; i< fragmentsDrawer.length;i++) {
            int pos = i;
            fragmentsDrawer[i].setPosition(i);
            mNavTitles[pos] = fragmentsDrawer[i].getTitle();
            mIcons[pos] = fragmentsDrawer[i].getIcon();
        }
    }

    @Override
    public MyNavigationDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_raw_nav_drawer, parent, false); //Inflating the layout
        ViewHolder vhItem = new ViewHolder(v, position); //Creating ViewHolder and passing the object of type view
        return vhItem; // Returning the created object


    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(MyNavigationDrawerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mNavTitles[position]);
        holder.imageView.setImageResource(mIcons[position]);
    }


    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length; // the number of items in the list will be +1 the titles including the header view.
    }

    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int Holderid;
        TextView textView;
        ImageView imageView;


        public ViewHolder(View itemView, int position) {   // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
            imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
            Holderid = position;                                               // setting holder id as 1 as the object being populated are of type item row

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    myNavDrawMan.replaceFragmentIn(Holderid);
                }
            });
        }


    }

}

