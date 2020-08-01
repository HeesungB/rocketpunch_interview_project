package com.example.rocketpunch_interview.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class FireStoreService(
    private val firebaseFirestore: FirebaseFirestore,
    private val preferencesService: PreferencesService
): DataSource {
    private val _myUser = MutableLiveData<User>()
    private val _messageChannelList = MutableLiveData<List<MessageChannel>>(listOf())
    private val _searchedList = MutableLiveData<List<User>>(listOf())
    private val _selectedMessageChannel = MutableLiveData<MessageChannel>()

    override val myUser: LiveData<User> get() = _myUser
    override val messageChannelList: LiveData<List<MessageChannel>> get() = _messageChannelList
    override val searchedList: LiveData<List<User>> get() = _searchedList
    override val selectedMessageChannel: LiveData<MessageChannel> get() = _selectedMessageChannel

    override fun setMyUser() {
        if (preferencesService.hasValue("user_id")) {
            firebaseFirestore.collection("users").whereEqualTo("id", preferencesService.getString("user_id","")).get().addOnSuccessListener {
                _myUser.value = it.documents[0].toObject(User::class.java)
            }
        } else {
            createUser()
        }
    }

    override fun createUser() {
        val uniqueID = UUID.randomUUID().toString()
        val user = User(uniqueID, "이름", "직업", "")
        firebaseFirestore.collection("users").add(user)
        preferencesService.setString("user_id",uniqueID)

        _myUser.value = user
    }

    override fun initUserSearchList() {
        _searchedList.value = listOf()
    }

    override fun searchUser(searchValue: String) {
        if(searchValue.length >= 2) {
            firebaseFirestore.collection("users").orderBy("name").startAt(searchValue).endAt(searchValue+'\uf8ff').get().addOnSuccessListener { documents ->
                val documentUserList = arrayListOf<User>()

                for (document in documents) {
                    documentUserList.add(document.toObject(User::class.java))
                }

                _searchedList.value = documentUserList
            }
        } else {
            initUserSearchList()
        }
    }

    override fun getMessageChannelList() {
        firebaseFirestore.collection("message_channel").whereArrayContainsAny("userList", arrayListOf(myUser.value)).get().addOnSuccessListener { documents ->
            val documentMessageChannelList = arrayListOf<MessageChannel>()

            for (document in documents) {
                val userList = document.data["userList"] as ArrayList<*>
                var opponentUser: User? = null

                for (user in userList) {
                    val tempUser = user as Map<*, *>
                    if(preferencesService.getString("user_id","") != tempUser["id"] as String) {
                        opponentUser = User(
                            tempUser["id"] as String,
                            tempUser["name"] as String,
                            tempUser["description"] as String,
                            tempUser["profileImage"] as String
                        )
                    }
                }

                documentMessageChannelList.add(
                    MessageChannel(
                        document.data["userList"] as List<User>,
                        document.data["currentChat"] as Chat?,
                        document.data["unreadChatCount"] as Long,
                        opponentUser
                    )
                )
            }

            _messageChannelList.value = documentMessageChannelList
        }
    }

    override fun setMessageChannelList(messageChannel: MessageChannel) {
        _selectedMessageChannel.value = messageChannel
    }

    override fun openMessageChannel(userList: List<User>) {
        firebaseFirestore.collection("message_channel").whereIn("userList", arrayListOf(userList)).get().addOnSuccessListener {
            if (it.isEmpty) {
                createMessageChannel(userList)
            } else {
                val messageChannel = it.documents[0].toObject(MessageChannel::class.java)!!
                messageChannel.opponentUser = getOpponentUser(userList)
                setMessageChannelList(messageChannel)
            }
        }
    }

    private fun getOpponentUser(userList: List<User>): User {
        lateinit var opponentUser: User

        for (user in userList) {
            if (preferencesService.getString("user_id", "") != user.id) {
                opponentUser = user
            }
        }

        return opponentUser
    }

    private fun createMessageChannel(userList: List<User>) {
        val newMessageChannel = MessageChannel(
            userList,
            null,
            0,
            getOpponentUser(userList)
        )

        firebaseFirestore.collection("message_channel").add(
            newMessageChannel
        )

        setMessageChannelList(newMessageChannel)
    }

}