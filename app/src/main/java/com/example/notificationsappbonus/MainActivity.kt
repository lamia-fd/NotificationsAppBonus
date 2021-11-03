package com.example.notificationsappbonus

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    lateinit var btn: Button
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    private val notificationId=110

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn = findViewById(R.id.button)

        createNotificationChanel()

        btn.setOnClickListener {

            Handler().postDelayed({
                sendNotification()
            }, 5000)

        }




    }


    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            val channel=
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)//.apply {

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)


        }
    }

    fun sendNotification(){

        val intent = Intent(this, EggActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var builder = Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_action_name).setContentIntent(pendingIntent)
            .setContentTitle("egg cooking time")
            .setContentText("Reedy")


        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())


        }


    }
}