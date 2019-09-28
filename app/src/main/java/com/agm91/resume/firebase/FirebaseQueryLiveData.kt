package com.agm91.resume.firebase

import android.os.Handler
import android.util.Log

import androidx.lifecycle.LiveData

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class FirebaseQueryLiveData : LiveData<DataSnapshot> {

    private lateinit var query: Query
    private var listenerRemovePending = false
    private val listener = MyValueEventListener()
    private val handler = Handler()
    private val removeListener = Runnable {
        query.removeEventListener(listener)
        listenerRemovePending = false
    }

    constructor(query: Query) {
        this.query = query
    }

    constructor(ref: DatabaseReference) {
        this.query = ref
    }

    override fun onActive() {
        Log.d(LOG_TAG, "onActive")
        if (listenerRemovePending) {
            handler.removeCallbacks(removeListener)
        } else {
            query.addValueEventListener(listener)
        }
        listenerRemovePending = false
    }

    override fun onInactive() {
        Log.d(LOG_TAG, "onInactive")
        // Listener removal is schedule on a two second delay
        handler.postDelayed(removeListener, 2000)
        listenerRemovePending = true
    }

    private inner class MyValueEventListener : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            value = dataSnapshot
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.e(LOG_TAG, "Can't listen to query $query", databaseError.toException())
        }
    }

    companion object {

        private val LOG_TAG = "FirebaseQueryLiveData"
    }
}