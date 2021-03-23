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

    val remoteConfig = Firebase.remoteConfig
    val analytics = Firebase.analytics

    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set to specific test value
        analytics.setUserProperty("some_property", "test")

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        Log.d(TAG, "Config params updated: $updated")
                        findViewById<TextView>(R.id.text_view).text = remoteConfig.getString("test_key")

                        Toast.makeText(this, remoteConfig.getString("test_key"), Toast.LENGTH_SHORT).show()
                    } else {
                        findViewById<TextView>(R.id.text_view).text = "Fetch Failed"
                    }
                }
    }
}