package com.example.oneinchman.liquids;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class CalculatorActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        bottomNavigation.setSelectedItemId(bottomNavigation.getMenu().getItem(2).getItemId());

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.add_delete:
                        Intent app_deleteIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(app_deleteIntent);
                        break;
                    case R.id.view:
                        Intent viewIntent = new Intent(getApplicationContext(), DisplayLiquidsActivity.class);
                        startActivity(viewIntent);
                        break;
                }
                return true;
            }
        });
    }
}
