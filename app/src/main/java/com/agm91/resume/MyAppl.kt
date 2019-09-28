package com.agm91.resume

import android.app.Application

import com.google.firebase.FirebaseApp

class MyAppl : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
    }

    companion object {
        lateinit var instance: MyAppl
            private set
    }
}
