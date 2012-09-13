package com.msi.ibm.eyes;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TesteMaroto extends Activity implements SensorEventListener {

	TextView xViewA = null;
	TextView yViewA = null;
	TextView zViewA = null;
	TextView xViewO = null;
	TextView yViewO = null;
	TextView zViewO = null;
	private SensorManager sm;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        setContentView(R.layout.main);
        
        xViewA = (TextView) findViewById(R.id.xbox);
        yViewA = (TextView) findViewById(R.id.ybox);
        zViewA = (TextView) findViewById(R.id.zbox);
        xViewO = (TextView) findViewById(R.id.xboxo);
        yViewO = (TextView) findViewById(R.id.yboxo);
        zViewO = (TextView) findViewById(R.id.zboxo);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	MenuItem bussula = menu.add(0,1,0, "Bussula");
    	bussula.setIntent(new Intent(this, BussulaActivity.class));
    	
    	MenuItem ibm = menu.add(0,2,0, "IBMEyes");
    	ibm.setIntent(new Intent(this, IBMEyes.class));
    	
    	
    	return super.onCreateOptionsMenu(menu);
    }
    
	@Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	sm.unregisterListener(this);
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Log.i("MUDOU A ACCURACY", "Sensor:"+sensor+ " "+ accuracy);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		synchronized (this) {
            Log.i("MAROTO", "onSensorChanged: " + event.sensor + ", x: " + values[0] + ", y: " + values[1] + ", z: " + values[2]);
            if (event.sensor.getType() == SensorManager.SENSOR_ORIENTATION) {
	            xViewO.setText("Orientation X: " + values[0]);
	            yViewO.setText("Orientation Y: " + values[1]);
	            zViewO.setText("Orientation Z: " + values[2]);
            }
            if (event.sensor.getType() == SensorManager.SENSOR_ACCELEROMETER) {
	            xViewA.setText("Accel X: " + values[0]);
	            yViewA.setText("Accel Y: " + values[1]);
	            zViewA.setText("Accel Z: " + values[2]);
            }            
            if (event.sensor.getType() == SensorManager.AXIS_X) {
            	Log.i("TODOS VALUES X:", "-->"+Arrays.asList(values));
            }            
            if (event.sensor.getType() == SensorManager.AXIS_Y) {
            	Log.i("TODOS VALUES Y:", "-->"+Arrays.asList(values));
            }            
            if (event.sensor.getType() == SensorManager.AXIS_Z) {
            	Log.i("TODOS VALUES Z:", "-->"+Arrays.asList(values));
            }            
        }
		
	}
}
