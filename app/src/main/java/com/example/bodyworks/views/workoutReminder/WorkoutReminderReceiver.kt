package com.example.bodyworks.views.workoutReminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.bodyworks.R

class WorkoutReminderReceiver : BroadcastReceiver() {
    @SuppressLint("ScheduleExactAlarm")
    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, "workout_reminder_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Workout Reminder")
            .setContentText("Time to do some exercise!")
            .build()

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(2, notification)

        val intervalMillis = 24 * 60 * 60 * 1000 //24 hours
        val nextAlarmTime = System.currentTimeMillis() + intervalMillis

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val newPendingIntent = PendingIntent.getBroadcast(
            context, 2, intent, PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextAlarmTime,
            newPendingIntent
        )
    }
}