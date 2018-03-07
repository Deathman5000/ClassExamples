package com.example.ra412063.fragmetlayoutdemo;
/*
  this activity is the entry point of the app and its display will
  be the content of activity_fragment_demo.xml
  the xml has the fragment element which calls the FriendFragment class,
  it onActivityCreated() method. In this method, the UI size is determined when the app was launched 
  based on the xml file selected (/layout/activity_fragment_demo.xml or /layout-land/activity_fragment_demo.xml)
  and either two fragments are displayed in the same UI or only one fragment
  that displays the list.  
  author: rajan alex
  version: 03/05/18
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
    }
}
