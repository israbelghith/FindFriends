package israa.belghith.findfriends;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MyLocationService extends Service {
    //le service activity sans interface
    public MyLocationService() {
    }
    public void onCreate(){

    }

    @SuppressLint("MissingPermission")//ignore all the permision about MissingPermission
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //recupere phone number envoyer
        String number =intent.getStringExtra("PHONE");
        //position gps
        FusedLocationProviderClient mClient=
                LocationServices.getFusedLocationProviderClient(this);

        if(Constants.GPS_SMS_PERMISSION_Stats) {
            mClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();

                        //send location
                        SmsManager manager=SmsManager.getDefault();
                        manager.sendTextMessage(number,
                                null,
                                Constants.MSG_MAPOSITION+"#"+longitude+"#"+latitude,
                                null,
                                null);
                    } else
                        Toast.makeText(MyLocationService.this, "pas de position gps", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}