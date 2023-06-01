package com.bm.fire_emergency_mqtt_mobile.firebase


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent;
import android.content.Context
import android.content.Intent;
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import com.bm.fire_emergency_mqtt_mobile.R
import com.bm.fire_emergency_mqtt_mobile.activities.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONArray
import org.json.JSONObject


class FirebaseMessageReceiver : FirebaseMessagingService() {

    private val channelId = "notification_channel";
    private val channelName = "com.bm.fire_emergency_mqtt_mobile.firebase"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {
            generateNotification(message.notification!!.title!!, message.notification!!.body!!)
        }
    }

    fun generateNotification(title: String, message: String) {
        val messageArray = message.split("cardId:")
        val cardID = messageArray[1];

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("urgentData", message)
        intent.putExtra("dataType", DataType.URGENT.name)
        intent.putExtra("cardId", cardID)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE
        )

        var builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.fire)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
        //   builder = builder.setContent(getView(title, message))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }


    @SuppressLint("RemoteViewLayout")
    fun getView(title: String, message: String): RemoteViews {
        val remoteView = RemoteViews(channelName, R.layout.notification)
        remoteView.setTextViewText(R.id.title, title)
        remoteView.setTextViewText(R.id.description, message)
        remoteView.setImageViewResource(R.id.notification_app_logo, R.drawable.fire)

        return remoteView
    }


}