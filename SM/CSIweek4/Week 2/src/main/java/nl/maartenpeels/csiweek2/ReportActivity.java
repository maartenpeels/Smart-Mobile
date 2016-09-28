package nl.maartenpeels.csiweek2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity {
    private boolean useGPS = false;
    long[] vibratePattern = new long[] { 20, 50, 100, 200, 40, 100 } ;
    private Button toggleGPS;
    private boolean vibrated = false;

    //Fake badguy location
    private Location lastKnownPos;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //Fake badguy location
        lastKnownPos = new Location("badguyLoc");
        lastKnownPos.setLongitude(5.481743);
        lastKnownPos.setLatitude(51.451727);

        toggleGPS = (Button)findViewById(R.id.toggleGPS);
        toggleGPS.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleGPS();
            }
        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if(location.distanceTo(lastKnownPos) < 100)
                {
                    if(!vibrated)
                    {
                        vibrate();
                    }
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }

    public void onClick(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void vibrate()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, 2);
        else {
            vibrated = true;
            Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(vibratePattern, 1);
        }
        Toast.makeText(getApplicationContext(), "[DEBUG}Bad guy is within 100 meters!", Toast.LENGTH_SHORT).show();
    }

    public void toggleGPS()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }else{
            useGPS = !useGPS;
            if(useGPS) {
                toggleGPS.setText("turn GPS off");
                toggleGPS.setBackgroundColor(getResources().getColor(R.color.textColorGreen));
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
            else {
                toggleGPS.setText("turn GPS on");
                toggleGPS.setBackgroundColor(getResources().getColor(R.color.textColorRed));
                locationManager.removeUpdates(locationListener);
                vibrated = false;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    toggleGPS();
                }
            }
            case 2: {
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    vibrate();
                }
            }
        }
    }
}
