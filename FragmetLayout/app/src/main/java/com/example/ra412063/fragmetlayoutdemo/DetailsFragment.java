package com.example.ra412063.fragmetlayoutdemo;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by ra412063 on 2/21/18.
 * This class has a
 * newInstance method which is called from FriendsFragment if both fragments will fit 
 * on the same UI and in this a DetailFragment instance is returned to 
 * FriendsFragment which create a new fragment by calling the instance's onCreateView () method
 */

public class DetailsFragment extends Fragment {
    // the method that gets called if in dual pane mode
    public static DetailsFragment newInstance(int index){
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }
    // extact the value of index
    public int getShowIndex(){
        return getArguments().getInt("index",0);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return a ScrollView instance and which will display the selected item detail
        ScrollView scrollView = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        text.setTextSize(15);
        text.setBackgroundColor(Color.MAGENTA);
        text.setTextColor(Color.WHITE);
        int padding = 20;
        scrollView.setPadding(padding,padding,padding,padding);
        scrollView.addView(text);
        // use the index to display president info
        text.setText(PresidentInfo.PRESIDENTS_INFO[getShowIndex()]);
        return scrollView;

    }
}
