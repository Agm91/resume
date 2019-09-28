package com.agm91.resume.ui.viewHolder

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.agm91.resume.entity.Resume
import com.agm91.resume.firebase.FirebaseQueryLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

class HomeViewModel : ViewModel() {

    private val liveData = FirebaseQueryLiveData(DATABASE_REFERENCE)

    val messageUploadIsSuccessful = MutableLiveData<Boolean>()

    val resumeLiveData: LiveData<Resume>
        get() = Transformations.map(liveData, Deserializer())

    private inner class Deserializer : Function<DataSnapshot, Resume> {
        override fun apply(dataSnapshot: DataSnapshot): Resume? {
            return dataSnapshot.getValue(Resume::class.java)
        }
    }

    companion object {
        private val DATABASE_REFERENCE = FirebaseDatabase.getInstance().getReference("/resume")
    }
}