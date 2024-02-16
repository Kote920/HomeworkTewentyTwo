package com.example.homeworktewentytwo.presentation.notification

import android.content.pm.PackageManager
import android.util.Log.d
import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

import com.example.homeworktewentytwo.R
import com.example.homeworktewentytwo.presentation.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        d("onMessageReceived", message.data.toString())
        val data = message.data
        showNotification(data["title"]?:"null",data["desc"]?:"null", data["id"]?:"1")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    private fun showNotification(title: String, desc: String, id: String){

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            action = "OPEN_POST_DETAILS"
            putExtra("post_id", id)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(applicationContext, "channel_id")
            .setContentTitle(title)
            .setContentText(desc)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        if(ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED )
            NotificationManagerCompat.from(applicationContext)
                .notify(1, notification)

    }


}