package com.jw.firebasetest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName

    private val remoteConfig = Firebase.remoteConfig
    private val analytics = Firebase.analytics

    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set to specific test value
//        analytics.setUserProperty("some_property", "test")

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Log.d(TAG, "Config params updated: $updated")
                        findViewById<TextView>(R.id.text_view).text = remoteConfig.getString("test_key")

                        Toast.makeText(this, remoteConfig.getString("test_key"), Toast.LENGTH_SHORT).show()
                        analytics.resetAnalyticsData()
                    } else {
                        findViewById<TextView>(R.id.text_view).text = "Fetch Failed"
                    }
                }

        // when the textview in the middle of the screen is tapped, try to initialize a different project.
        // doing this crashes with:
        // java.lang.IllegalStateException: FirebaseApp name [DEFAULT] already exists!
        //  at com.google.android.gms.common.internal.Preconditions.checkState(com.google.android.gms:play-services-basement@@17.2.1:29)
        //  at com.google.firebase.FirebaseApp.initializeApp(FirebaseApp.java:294)
        //  at com.google.firebase.FirebaseApp.initializeApp(FirebaseApp.java:267)
        findViewById<TextView>(R.id.text_view).setOnClickListener {
            initializeSecondSetOfFirebaseProjectProgrammatically()
        }
    }

    private fun initializeSecondSetOfFirebaseProjectProgrammatically() {
        FirebaseApp.initializeApp(this, option_proj2)
    }

    // default project option. (this got initialized automatically by 'google-services.json')
    private val option_proj1 = FirebaseOptions.Builder()
        .setApplicationId("1:1080837297219:android:5ba48abfe65978cb1bf0f6")
        .setProjectId("jw-firebase-proj")
        .setApiKey("AIzaSyA38lYx2-GWiFm311Haoqf5TDZUBkeevWA").build()

    // second set of project option.
    private val option_proj2 = FirebaseOptions.Builder()
        .setApplicationId("1:955949062470:android:3f34125cf9dd3cd065ad47")
        .setProjectId("jw-firebase-proj2")
        .setApiKey("AIzaSyDyvv_RFmd5iQzwJTodFZ0_DlzLFi8VA8M").build()
}