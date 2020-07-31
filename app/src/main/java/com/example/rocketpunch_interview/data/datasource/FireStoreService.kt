package com.example.rocketpunch_interview.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.User
import com.google.firebase.firestore.FirebaseFirestore


class FireStoreService(private val firebaseFirestore: FirebaseFirestore): DataSource {
    private val _searchedList = MutableLiveData<List<User>>(listOf())

    override val searchedList: LiveData<List<User>> get() = _searchedList

    override fun searchUser(searchValue: String) {
        firebaseFirestore.collection("users").whereGreaterThanOrEqualTo("name", searchValue).get().addOnSuccessListener { documents ->
            val documentUserList = arrayListOf<User>()

            for (document in documents) {
                documentUserList.add(
                    User(document.data.get("id") as String,
                        document.data.get("name") as String,
                        document.data.get("description") as String,
                        document.data.get("profileImage") as String))
            }

            _searchedList.value = documentUserList
        }
    }

}