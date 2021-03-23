package com.jw.firebasetest

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

class TestApplication: Application() {

    private val environment = FirebaseOptions.Builder()
        .setApplicationId("1:1080837297219:android:5ba48abfe65978cb1bf0f6")
        .setProjectId("jw-firebase-proj")
        .setApiKey("AIzaSyA38lYx2-GWiFm311Haoqf5TDZUBkeevWA").build()

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this, environment)
    }
}