package israa.belghith.findfriends;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MySMSReceiver extends BroadcastReceiver {

    @SuppressLint("MissingPermission")
    public void onReceive(Context context, Intent intent) { // TODO: This method is called when the BroadcastReceiver is receiving an // Intent broadcast.
        String messageBody, phoneNumber;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    messageBody = messages[0].getMessageBody();
                    phoneNumber = messages[0].getDisplayOriginatingAddress();

                    if (messageBody.contains(Constants.MSG_SendMePosition)) {
                        Intent i = new Intent(context, MyLocationService.class);
                        i.putExtra("PHONE", phoneNumber);
                        context.startService(i);
                    }
                    if (messageBody.contains(Constants.MSG_MAPOSITION)) {
                        String[] t = messageBody.split("#");
                        String longitude = t[1];
                        String latitude = t[2];

                        //créer une notification
                        NotificationCompat.Builder mynotify =
                                new NotificationCompat.Builder(context, "FindFriendsID");
                        mynotify.setContentTitle("Position reçu");
                        mynotify.setContentText("Appyuier pour voir la position sur google map");
                        mynotify.setAutoCancel(true);
                        mynotify.setSmallIcon(android.R.drawable.ic_dialog_map);

                        //action sur notification
                        Intent mapIntent = new Intent();
                        mapIntent.setAction(Intent.ACTION_VIEW);
                        mapIntent.setData(Uri.parse("geo:" + latitude + "," + longitude));
                        mapIntent.setPackage("com.google.android.apps.maps");

                        PendingIntent pi = PendingIntent.getActivity(
                                context,
                                0,
                                mapIntent,
                                PendingIntent.FLAG_MUTABLE);//mutable ou non mutable
                        mynotify.setContentIntent(pi);

                        //lancer notification
                        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(context);
                        //
                        NotificationChannel canal = new NotificationChannel(
                                "FindFriendsID",
                                "le canal de notre app findfriends",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManager.createNotificationChannel(canal);
                         notificationManager.notify(1,mynotify.build());

                    }
                }
                phoneNumber = messages[0].getDisplayOriginatingAddress();
                // Toast.makeText(context, "Message : "+messageBody+"Reçu de la part de;"+ phoneNumber, Toast.LENGTH_LONG ) .show();
            }
        }
    }
}