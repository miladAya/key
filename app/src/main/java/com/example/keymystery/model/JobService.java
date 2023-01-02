package com.example.keymystery.model;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.keymystery.R;

public class JobService extends android.app.job.JobService {
    public  static final String CHANNEL_ID="channel id";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        sendNotification();
        return false;
    }



    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
    private int sendNotification() {

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID
                    ,"channel name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService
                    (NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        Intent intent1=new Intent(getBaseContext(),JobService.class);
        //  لمن بدي ابعت قيمه وحدةلمن يكون نوعها نفس نوع الانتنت الي اجتني
        intent1.setAction("stop");
        PendingIntent pendingIntent=PendingIntent.getService(this,0,intent1,0);

        NotificationCompat.Builder builder=
                new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notification);
        builder.setContentTitle("Notification");
        builder.setContentText("Notification");
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.addAction(R.drawable.notification,"Action",pendingIntent);
//        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
//        managerCompat.notify(0,builder.build());
        //شفل الاشعار و اربط الاشعار ب سيرفز بنشغل من هادي عشان يعرف انو بنشتغل علي فور غروند سيرفز
        startForeground(1,builder.build());



        return START_STICKY;
    }
}
