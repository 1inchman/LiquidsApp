package com.example.oneinchman.liquids;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText componentsTxt;
    private EditText percentagesTxt;
    private EditText nameTxt;

    private Button addButton;
    private Button clearButton;
    private LiquidsDBHandler dbHandler;

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        componentsTxt = (EditText) findViewById(R.id.componentsText);
        percentagesTxt = (EditText) findViewById(R.id.percentagesText);
        addButton = (Button) findViewById(R.id.submitButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        nameTxt = (EditText) findViewById(R.id.nameText);
        dbHandler = new LiquidsDBHandler(this, null, null, 1);

        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.view:
                        Intent viewIntent = new Intent(getApplicationContext(), DisplayLiquidsActivity.class);
                        startActivity(viewIntent);
                        break;
                    case R.id.calculate:
                        Intent calculatorIntent = new Intent(getApplicationContext(), CalculatorActivity.class);
                        startActivity(calculatorIntent);
                        break;
                }
                return true;
            }
        });
//        displayLiquids();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        final Button button = (Button) findViewById(R.id.btn_sbmt);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                final TextView ingrd_view = (TextView) findViewById(R.id.ingridients);
//                final TextView percentage = (TextView) findViewById(R.id.percentage);
//                String idgrd = ingrd_view.getText().toString();
//                String perctg = percentage.getText().toString();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addButtonClicked(View view) {
        Liquids liquid = new Liquids(componentsTxt.getText().toString(),
                percentagesTxt.getText().toString(),
                nameTxt.getText().toString());
        dbHandler.addLiquid(liquid);
        componentsTxt.setText("");
        percentagesTxt.setText("");
        nameTxt.setText("");
        Toast.makeText(this, "Liquid added", Toast.LENGTH_LONG).show();
    }

    public void clearButtonClicked(View view) {
        dbHandler.deleteTableFromDB();
        Toast.makeText(this, "Table deleted", Toast.LENGTH_LONG).show();
    }

}
