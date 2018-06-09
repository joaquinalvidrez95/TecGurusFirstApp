package com.joaquinalvidrez.tecgurusfirstapp.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.joaquinalvidrez.tecgurusfirstapp.R
import com.joaquinalvidrez.tecgurusfirstapp.activity.CarDetailActivity

class NotificationFireBase : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)

        Log.e("Notification de fire", "${p0?.from}, ${p0?.notification?.body}")

        p0?.notification?.let { makeNotification(it) }

    }

    private fun makeNotification(notification: RemoteMessage.Notification) {
        val intent = Intent(applicationContext, CarDetailActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationCompat = NotificationCompat.Builder(applicationContext, "wwe")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Nativa: ${notification.title}")
                .setAutoCancel(true)
                .setContentText("Cuerpo: ${notification.body}")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)

        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .notify(1, notificationCompat.build())
    }
}