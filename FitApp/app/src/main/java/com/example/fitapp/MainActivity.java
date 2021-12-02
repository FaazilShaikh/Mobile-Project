package com.example.fitapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        /* Uncomment and change intent here to go to other pages you guys have added
        b.setOnClickListener(v -> startActivity(new Intent(
                getApplicationContext(), MapsActivity.class))
        );
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.map, menu);

        // first parameter is the file for icon and second one is menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mapButton:
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}