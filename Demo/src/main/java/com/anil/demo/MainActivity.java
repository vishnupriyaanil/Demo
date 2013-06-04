
package com.anil.demo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ArrayAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.anil.demo.adapter.ViewPagerAdapter;
import com.anil.demo.rest.RestClient;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class MainActivity
    extends SherlockFragmentActivity
    implements OnPageChangeListener, OnNavigationListener
{

    @RestService
    RestClient restClient;
    private String[] locations;
    @ViewById
    ViewPager pager;

    @AfterViews
    void afterViews() {
        locations = getResources().getStringArray(R.array.locations);
        configureViewPager();
        configureActionBar();
    }

    @UiThread
    void doSomethingElseOnUiThread() {
        // do something on UIThread
    }

    @Background
    void doSomethingInBackground() {
        restClient.main();
        doSomethingElseOnUiThread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater();
        return true;
    }

    private void configureViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), locations);
        pager.setAdapter(viewPagerAdapter);
        pager.setOnPageChangeListener(this);
    }

    public void onPageSelected(int position) {
        getSupportActionBar().setSelectedNavigationItem(position);
    }

    private void configureActionBar() {
        Context context = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.locations, (android.R.layout.simple_list_item_1));
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        pager.setCurrentItem(itemPosition);
        return true;
    }
    @Override
public void onPageScrollStateChanged(int position) {}@Override
 public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
}
