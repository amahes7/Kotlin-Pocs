package com.example.loginappforzebra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivityPage extends AppCompatActivity {


    ListView appList;

    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);
        getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(flags);
                }
            }
        });
        appList = (ListView) findViewById(R.id.appsList);
        appList.setAdapter(setAdapterForList());
    }

    private ArrayAdapter setAdapterForList() {
        ArrayList appListArray = new ArrayList();
        appListArray.add("HHReplen Application");
        appListArray.add("App 2");
        appListArray.add("App 3");
        appListArray.add("App 4");
        appListArray.add("App 5");
        appListArray.add("App 6");
        appListArray.add("App 7");
        appListArray.add("App 8");
        appListArray.add("App 9");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, appListArray);
        return arrayAdapter;
    }
}