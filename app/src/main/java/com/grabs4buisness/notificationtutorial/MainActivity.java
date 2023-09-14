package com.grabs4buisness.notificationtutorial;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID1 = 1000,NOTIFICATION_ID2=1001,NOTIFICATION_ID3=1002;

    NotificationManager nm;
    Notification notificationMsg1;

    Drawable drawable1,drawable2,drawable3;
    Bitmap large_icon1,large_icon2,large_icon3;
    PendingIntent pendingIntent1,pendingIntent2,pendingIntent3;

    String contentText;

    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=findViewById(R.id.Btn1);
        btn2=findViewById(R.id.Btn2);
        btn3=findViewById(R.id.Btn3);


        //Creating instance of the notification manager
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Creating the large icon bitmap
        drawable1 = ResourcesCompat.getDrawable(getResources(), R.drawable.coronavirus, null);
        BitmapDrawable bitmapDrawable1 = (BitmapDrawable) drawable1;
        large_icon1 = bitmapDrawable1.getBitmap();

        drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.quarantine, null);
        BitmapDrawable bitmapDrawable2 = (BitmapDrawable) drawable2;
        large_icon2 = bitmapDrawable2.getBitmap();

        drawable3 = ResourcesCompat.getDrawable(getResources(), R.drawable.covidnews, null);
        BitmapDrawable bitmapDrawable3 = (BitmapDrawable) drawable3;
        large_icon3 = bitmapDrawable3.getBitmap();

        //Creating intent to pass into when the notification is get clicked
        Intent notificationIntent1 = new Intent(this, CrowdAlertActivity.class);
        Intent notificationIntent2 = new Intent(this, QuarantineRequired.class);
        Intent notificationIntent3 = new Intent(this, CovidNews.class);


        // Create a PendingIntent for the notification with FLAG_IMMUTABLE
        pendingIntent1 = PendingIntent.getActivity(this, 0, notificationIntent1, PendingIntent.FLAG_IMMUTABLE);
        pendingIntent2 = PendingIntent.getActivity(this,0,notificationIntent2,PendingIntent.FLAG_IMMUTABLE);
        pendingIntent3 = PendingIntent.getActivity(this,0,notificationIntent3,PendingIntent.FLAG_IMMUTABLE);

        //On button clicked
        btn1.setOnClickListener(v -> createNotification1());
        btn2.setOnClickListener(v -> createNotification2());
        btn3.setOnClickListener(v -> createNotification3());

    }


    private void createNotification1()
    {
        contentText = "Someone in your area has tested Positive for Covid-19. Tap for more information";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationMsg1 = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.coronavirus)
                    .setColor(ContextCompat.getColor(this, R.color.red))
                    .setContentTitle("Crowd Alert")
                    .setContentText(getStyledContentText(contentText))
                    .setShowWhen(true)
                    .setLargeIcon(large_icon1)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getStyledContentText(contentText)))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent1)
                    .setAutoCancel(true)
                    .addAction(0, "Read More", pendingIntent1)
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_HIGH));

        } else {
            notificationMsg1 = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.coronavirus)
                    .setColor(ContextCompat.getColor(this, R.color.red))
                    .setContentTitle("Crowd Alert")
                    .setContentText(getStyledContentText(contentText))
                    .setLargeIcon(large_icon1)
                    .setShowWhen(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getStyledContentText(contentText)))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent1) // Set the PendingIntent
                    .setAutoCancel(true)
                    .addAction(0, "Read More", pendingIntent1)
                    .build();
        }
        nm.notify(NOTIFICATION_ID1, notificationMsg1);

    }
    private void createNotification2()
    {
        contentText = "Get more information before your 14 days of quarantine";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationMsg1 = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.coronavirus)
                    .setColor(ContextCompat.getColor(this, R.color.yellow))
                    .setContentTitle("Quarantine Required")
                    .setContentText(getStyledContentText(contentText))
                    .setShowWhen(true)
                    .setLargeIcon(large_icon2)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getStyledContentText(contentText)))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent2)
                    .setAutoCancel(true)
                    .addAction(0, "Read More", pendingIntent2)
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notificationMsg1 = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.coronavirus)
                    .setColor(ContextCompat.getColor(this, R.color.yellow))
                    .setContentTitle("Quarantine Required")
                    .setContentText(getStyledContentText(contentText))
                    .setLargeIcon(large_icon2)
                    .setShowWhen(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getStyledContentText(contentText)))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent2) // Set the PendingIntent
                    .setAutoCancel(true)
                    .addAction(0, "Read More", pendingIntent2)
                    .build();
        }
        nm.notify(NOTIFICATION_ID2, notificationMsg1);
    }
    private void createNotification3()
    {
        contentText="Vaccination efforts continues. Tap for more information";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationMsg1 = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.coronavirus)
                    .setColor(ContextCompat.getColor(this, R.color.red))
                    .setContentTitle("COVID-19 News")
                    .setContentText(getStyledContentText(contentText))
                    .setShowWhen(true)
                    .setLargeIcon(large_icon3)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getStyledContentText(contentText)))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent3)
                    .setAutoCancel(true)
                    .addAction(0, "Read More", pendingIntent3)
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notificationMsg1 = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.coronavirus)
                    .setColor(ContextCompat.getColor(this, R.color.red))
                    .setContentTitle("COVID-19 News")
                    .setContentText(getStyledContentText(contentText))
                    .setLargeIcon(large_icon3)
                    .setShowWhen(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(getStyledContentText(contentText)))
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setContentIntent(pendingIntent3) // Set the PendingIntent
                    .setAutoCancel(true)
                    .addAction(0, "Read More", pendingIntent3)
                    .build();
        }
        nm.notify(NOTIFICATION_ID3, notificationMsg1);
    }

    private SpannableString getStyledContentText(String contextText) {

        // Create a custom Typeface for Times New Roman
        Typeface timesNewRoman = Typeface.create("serif", Typeface.NORMAL);

        SpannableString spannableContentText = new SpannableString(contentText);

        // Change text color to red
        spannableContentText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.red)),
                0, contentText.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply Times New Roman font
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            spannableContentText.setSpan(new TypefaceSpan(timesNewRoman),
                    0, contentText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        return spannableContentText;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}