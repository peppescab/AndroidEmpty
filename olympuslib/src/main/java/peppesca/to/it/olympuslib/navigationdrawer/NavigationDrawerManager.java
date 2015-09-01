package peppesca.to.it.olympuslib.navigationdrawer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import peppesca.to.it.olympuslib.R;


/**
 * Created by PeppeSca on 17/05/2015.
 */
public class NavigationDrawerManager {

    private GeneralAbstractFragment[] fragmentsDrawerList;
    private AppCompatActivity mActivity;
    private DrawerLayout mDrawerLayout;
    private int idFragmentContainer;

    /**
     * @param mActivity
     * @param idFragmentContainer
     * @param recView
     * @param mDrawerLayout
     * @param fragmentsDrawerList
     */
    public NavigationDrawerManager(AppCompatActivity mActivity, int idFragmentContainer, RecyclerView recView, DrawerLayout mDrawerLayout, GeneralAbstractFragment[] fragmentsDrawerList) {
        this.mActivity = mActivity;
        this.idFragmentContainer = idFragmentContainer;
        this.mDrawerLayout = mDrawerLayout;
        this.fragmentsDrawerList = fragmentsDrawerList;
        recView.setHasFixedSize(true);
        MyNavigationDrawerAdapter mAdapter = new MyNavigationDrawerAdapter(this, fragmentsDrawerList);
        // Creating the Adapter of MyNavigationDrawerAdapter class(which we are going to see in a bit)
        recView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);                 // Creating a layout Manager
        recView.setLayoutManager(mLayoutManager);
        setUp();
    }



    public static final void useGeneralNavigationStructure(AppCompatActivity act, GeneralAbstractFragment[] listGeneralFragments) {
        act.setContentView(R.layout.nav_drawer_general);
        RecyclerView mRecycler = (RecyclerView) act.findViewById(R.id.mRecyclerView);
        DrawerLayout mDrawerLay = (DrawerLayout) act.findViewById(R.id.drawerLayout);
        Toolbar toolbar = (Toolbar) act.findViewById(R.id.tool_bar);

//        //TODO Put in library?
////        setActionBar(toolbar);
        act.setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(act, mDrawerLay, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        }; // mDrawer Toggle Object Made
        mDrawerLay.setDrawerListener(mDrawerToggle); // mDrawer Listener set to the mDrawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
        //TODO -- till here
        NavigationDrawerManager navDrawManager = new NavigationDrawerManager(
                act,
                R.id.fragment_container,
                mRecycler,
                mDrawerLay,
                listGeneralFragments);
//        getActionBar().setHomeButtonEnabled(true);

    }

    private void setUp() {
        FragmentManager fm = mActivity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(idFragmentContainer, fragmentsDrawerList[0]);
        ft.commit();
    }

    public void replaceFragmentIn(final int position) {

        mDrawerLayout.closeDrawers();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //each Fragment knows its position
                GeneralAbstractFragment tmpGeneralFragment = null;
                for (GeneralAbstractFragment itGenerFrag : fragmentsDrawerList) {
                    if (itGenerFrag.getPosition() == position) {
                        tmpGeneralFragment = itGenerFrag;
                        break;
                    }
                }

                if (tmpGeneralFragment != null) {
                    FragmentManager fm = mActivity.getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(idFragmentContainer, tmpGeneralFragment);
                    ft.commit();
                }

            }
        }, 200);
    }
}
