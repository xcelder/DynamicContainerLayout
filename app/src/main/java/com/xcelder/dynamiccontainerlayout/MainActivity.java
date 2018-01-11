package com.xcelder.dynamiccontainerlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.xcelder.dynamiccontainerlayout.views.DynamicContainerLayout;

public class MainActivity extends AppCompatActivity {

    DynamicContainerLayout dynamicContainerLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    dynamicContainerLayout.inflateViewById(R.layout.layout1);
                    return true;
                case R.id.navigation_dashboard:
                    dynamicContainerLayout.inflateViewById(R.layout.layout2);
                    return true;
                case R.id.navigation_notifications:
                    dynamicContainerLayout.inflateViewById(R.layout.layout3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dynamicContainerLayout = findViewById(R.id.dyn_container);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
