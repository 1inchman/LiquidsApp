package com.example.oneinchman.liquids;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayLiquidsActivity extends AppCompatActivity {

    TextView componentsView;
    TextView percentagesView;
    TextView nameView;
    Spinner dropdownList;
    LiquidsDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.activity_display_liquids);
        setContentView(android.R.layout.);

        componentsView = (TextView) findViewById(R.id.liquidComponentsView);
        percentagesView = (TextView) findViewById(R.id.liquidProportionsView);
        nameView = (TextView) findViewById(R.id.liquidNameView);
        dropdownList = (Spinner) findViewById(R.id.dropdownList);
        db = new LiquidsDBHandler(this, null, null, 1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getLiquidNames());
        dropdownList.setAdapter(adapter);
        dropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ArrayList<String> liquid =  db.getLiquidByName(dropdownList.getSelectedItem().toString());
               nameView.setText(liquid.get(0));
               componentsView.setText(liquid.get(1));
               percentagesView.setText(liquid.get(2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public ArrayList<String> getLiquidNames() {
        return db.getLiquidNames();
    }

}
