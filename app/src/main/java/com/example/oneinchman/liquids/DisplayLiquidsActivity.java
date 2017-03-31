package com.example.oneinchman.liquids;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayLiquidsActivity extends AppCompatActivity {

    private TextView componentsView;
    private TextView percentagesView;
    private TextView nameView;
    private Spinner dropdownList;
    private LiquidsDBHandler db;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_liquids);

        componentsView = (TextView) findViewById(R.id.liquidComponentsView);
        percentagesView = (TextView) findViewById(R.id.liquidProportionsView);
        nameView = (TextView) findViewById(R.id.liquidNameView);
        dropdownList = (Spinner) findViewById(R.id.dropdownList);
        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        db = new LiquidsDBHandler(this, null, null, 1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getLiquidNames());
        dropdownList.setAdapter(adapter);
        dropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ArrayList<String> liquid = null;
               liquid = db.getLiquidByName(dropdownList.getSelectedItem().toString());
               nameView.setText(liquid.get(0));
               componentsView.setText(liquid.get(1));
               percentagesView.setText(liquid.get(2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        bottomNavigation.setSelectedItemId(bottomNavigation.getMenu().getItem(1).getItemId());
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.add_delete:
                        Intent app_deleteIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(app_deleteIntent);
                        break;
                    case R.id.calculate:
                        Intent calculateIntent = new Intent(getApplicationContext(), CalculatorActivity.class);
                        startActivity(calculateIntent);
                        break;
                }
                return true;
            }
        });
    }

    public ArrayList<String> getLiquidNames() {
        return db.getLiquidNames();
    }

}