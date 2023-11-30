package com.example.notification.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.notification.ClinicWaitTimeServiceFragment
import com.example.notification.ConsultationEndedServiceFragment
import com.example.notification.DoctorAvailableServiceFragment
import com.example.notification.MainActivity
import com.example.notification.MyBackgroundServiceFragment
import com.example.notification.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM Token", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle incoming FCM message
        super.onMessageReceived(remoteMessage)
        Log.d("FCM Message", "From: ${remoteMessage.from}")

        remoteMessage.notification?.let {
            Log.d("FCM Message", "Message Notification Body: ${it.body}")

            // Extract notification type from data payload
            val notificationType = remoteMessage.data["NotificationType"]

            when (notificationType) {
                "new_doctor_consultation" -> handleNewDoctorConsultation(it.title, it.body)
                "clinic_wait_time" -> handleClinicWaitTime(it.title, it.body)
                "doctor_available" -> handleDoctorAvailable(it.title, it.body)
                "consultation_ended" -> handleConsultationEnded(it.title, it.body)
                else -> {
                    // Handle unknown notification type or default behavior
                    createNotificationChannel()
                    showNotification(it.title, it.body)
                }
            }
        }
    }

    private fun handleNewDoctorConsultation(title: String?, message: String?) {
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.putExtra("fragmentToLoad", "NewDoctorConsultationFragment")
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(this, "doctor_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Show the notification
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())

        // Add additional logic specific to new doctor consultation if needed
        // For example, you might want to update UI, start a service, or perform other actions.

        // Sample additional logic:
        // Update UI to indicate a new doctor consultation is available
        updateUIForNewDoctorConsultation()

        // Start a service to perform background tasks related to a new doctor consultation
        startBackgroundServiceForNewDoctorConsultation()

        // ... Add more custom logic as per your application requirements
    }

    private fun updateUIForNewDoctorConsultation() {
        // TODO: Implement logic to update UI elements to indicate a new doctor consultation is available
        // For example, you might want to show a notification badge, change UI colors, etc.
        // This is a placeholder; replace it with your actual UI update logic.

        // For simplicity, let's print a log message
        Log.d("NewDoctorConsultation", "UI Updated: New doctor consultation available")

        // Example: Show a toast notification
        showToast("New doctor consultation available!")
    }

    private fun startBackgroundServiceForNewDoctorConsultation() {
        // TODO: Implement logic to start a background service for additional tasks related to a new doctor consultation
        // This could include fetching patient data, updating databases, etc.
        // This is a placeholder; replace it with your actual background service logic.

        // For simplicity, let's print a log message
        Log.d("NewDoctorConsultation", "Background Service Started: New doctor consultation available")

        // Example: Start a background service using IntentService
        val backgroundServiceIntent = Intent(this, MyBackgroundServiceFragment::class.java)
        startService(backgroundServiceIntent)
    }

    private fun handleClinicWaitTime(title: String?, message: String?) {
        // Create a notification for clinic wait time
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(this, "eclinic_wait_time_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())


        // Update UI with information about clinic wait time
        updateUIWithWaitTime(message)

        // Start a service to perform background tasks related to clinic wait time
        startBackgroundService()

    }

    private fun updateUIWithWaitTime(waitTimeMessage: String?) {
        // Implement logic to update UI elements with clinic wait time information
        // This could involve updating a TextView, ProgressBar, or other UI components
        Log.d("ClinicWaitTime", "Wait Time Information: $waitTimeMessage")
    }

    private fun startBackgroundService() {
        // Implement logic to start a background service for additional tasks related to clinic wait time
        // This could include fetching data, updating databases, etc.
        Log.d("BackgroundService", "Background Service Started for Clinic Wait Time")

        // Example: Start a background service using IntentService
        val backgroundServiceIntent = Intent(this, ClinicWaitTimeServiceFragment::class.java)
        backgroundServiceIntent.putExtra("waitTime", "Add your wait time data here")
        startService(backgroundServiceIntent)
    }

    private fun handleDoctorAvailable(title: String?, message: String?) {
        // Create a notification for doctor available
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(this, "doctor_available_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Show the notification
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())

        // Add additional logic specific to doctor available if needed
        // For example, you might want to update UI, start a service, or perform other actions.

        // Sample additional logic:
        // Update UI to reflect that the doctor is available
        updateUIForDoctorAvailable()

        // Start a service to perform background tasks related to doctor availability
        startBackgroundServiceForDoctorAvailable()

        // ... Add more custom logic as per your application requirements
    }

    private fun updateUIForDoctorAvailable() {
        // Implement logic to update UI elements to reflect that the doctor is available
        // This could involve changing the color of a status indicator, updating text, etc.
        Log.d("DoctorAvailable", "UI Updated: Doctor is available")
    }

    private fun startBackgroundServiceForDoctorAvailable() {
        // Implement logic to start a background service for additional tasks related to doctor availability
        // This could include fetching data, updating databases, etc.
        Log.d("DoctorAvailable", "Background Service Started: Doctor is available")

        val backgroundServiceIntent = Intent(this, DoctorAvailableServiceFragment::class.java)
        startService(backgroundServiceIntent)
    }

    private fun handleConsultationEnded(title: String?, message: String?) {
        // Create a notification for consultation ended
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(this, "consultation_ended_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Show the notification
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())

        // Sample additional logic:
        // Update UI to reflect that the consultation has ended
        updateUIForConsultationEnded()

        // Start a service to perform background tasks related to the end of a consultation
        startBackgroundServiceForConsultationEnded()

        // ... Add more custom logic as per your application requirements
    }

    private fun updateUIForConsultationEnded() {
        // Implement logic to update UI elements to reflect that the consultation has ended
        // This could involve showing a summary, asking for feedback, etc.
        Log.d("ConsultationEnded", "UI Updated: Consultation has ended")
    }

    private fun startBackgroundServiceForConsultationEnded() {
        // Implement logic to start a background service for additional tasks related to consultation end
        // This could include updating databases, sending reports, etc.
        Log.d("ConsultationEnded", "Background Service Started: Consultation has ended")

        val backgroundServiceIntent = Intent(this, ConsultationEndedServiceFragment::class.java)
        startService(backgroundServiceIntent)
    }



    private fun createNotificationChannel() {
        val doctorChannelId = "doctor_channel"
        val doctorChannelName = "Doctor Consultation Notifications"
        val doctorChannelDescription = "Notifications related to doctor consultations"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val doctorChannel = NotificationChannel(
                doctorChannelId,
                doctorChannelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = doctorChannelDescription
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(doctorChannel)
        }

        val eClinicWaitTimeChannelId = "eclinic_wait_time_channel"
        val eClinicWaitTimeChannelName = "Clinic Wait Time Notifications"
        val eClinicWaitTimeChannelDescription = "Notifications for waiting time when doctor is busy"

        val doctorAvailableChannelId = "doctor_available_channel"
        val doctorAvailableChannelName = "Doctor Available Notifications"
        val doctorAvailableChannelDescription = "Notifications when doctor is available after waiting time"

        val consultationEndedChannelId = "consultation_ended_channel"
        val consultationEndedChannelName = "Consultation Ended Notifications"
        val consultationEndedChannelDescription = "Notifications when doctor ended the consultation and submitted the prescription"



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerNotificationChannel(
                eClinicWaitTimeChannelId,
                eClinicWaitTimeChannelName,
                eClinicWaitTimeChannelDescription

            )
            registerNotificationChannel(
                doctorAvailableChannelId,
                doctorAvailableChannelName,
                doctorAvailableChannelDescription
            )
            registerNotificationChannel(
                consultationEndedChannelId,
                consultationEndedChannelName,
                consultationEndedChannelDescription
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerNotificationChannel(
        channelId: String,
        channelName: String,
        channelDescription: String
    ) {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = channelDescription
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun showNotification(title: String?, message: String?) {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(this, "doctor_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
