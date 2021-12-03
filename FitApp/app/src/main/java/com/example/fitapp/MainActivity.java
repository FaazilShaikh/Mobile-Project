package com.example.fitapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;


public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private TextView stepCount;
    private SensorManager sensorManager;
    CircularProgressBar circularProgressBar;
    private Sensor mStepCounter;
    private boolean isSensorActive;
    private float numberSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circularProgressBar = findViewById(R.id.circularProgressBar);

        Button b = findViewById(R.id.button);

        b.setOnClickListener(v -> startActivity(new Intent(
                getApplicationContext(), songActivity.class))
        );
        Button b3=findViewById(R.id.button3);
        b3.setOnClickListener(v-> startActivity(new Intent(
                getApplicationContext(),userInfostats.class
        )));

        stepCount = findViewById(R.id.stepDisplay);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorActive = true;
            stepCount.setText("Steps: 0");
        }else{
            stepCount.setText("Steps: 0");
            isSensorActive = false;
        }
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


    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!= null){
            sensorManager.unregisterListener(this, mStepCounter);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor==mStepCounter){
            numberSteps = event.values[0];
            stepCount.setText("Steps: "+String.valueOf(numberSteps));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}