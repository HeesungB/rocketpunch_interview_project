package com.example.rocketpunch_interview.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class FireStoreService(
    private val firebaseFirestore: FirebaseFirestore,
    private val preferencesService: PreferencesService
): DataSource {
    private val _myUser = MutableLiveData<User>()
    private val _channelList = MutableLiveData<List<Channel>>(listOf())
    private val _searchedList = MutableLiveData<List<User>>(listOf())
    private val _selectedChannel = MutableLiveData<Channel>()

    override val myUser: LiveData<User> get() = _myUser
    override val channelList: LiveData<List<Channel>> get() = _channelList
    override val searchedList: LiveData<List<User>> get() = _searchedList
    override val selectedChannel: LiveData<Channel> get() = _selectedChannel

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

    override fun getChannelList() {
        firebaseFirestore.collection("channel").whereArrayContainsAny("userList", arrayListOf(myUser.value)).get().addOnSuccessListener { documents ->
            val tempChannelList = arrayListOf<Channel>()

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

                if (userList[0] == userList[1]) {
                    val tempUser = userList[0] as Map<*, *>
                    opponentUser = User(
                        tempUser["id"] as String,
                        tempUser["name"] as String,
                        tempUser["description"] as String,
                        tempUser["profileImage"] as String
                    )
                }

                tempChannelList.add(
                    Channel(
                        document.id,
                        document.data["userList"] as List<User>,
                        document.data["currentChat"] as Chat?,
                        document.data["unreadChatCount"] as Long,
                        opponentUser!!
                    )
                )
            }

            _channelList.value = tempChannelList
        }
    }

    override fun setChannelList(channel: Channel) {
        _selectedChannel.value = channel
    }

    override fun openChannel(userList: List<User>) {
        firebaseFirestore.collection("channel").whereIn("userList", arrayListOf(userList)).get().addOnSuccessListener {
            if (it.isEmpty) {
                createChannel(userList)
            } else {
                val channelDto = it.documents[0].toObject(ChannelDto::class.java)!!
                setChannelList(convertChannel(channelDto,it.documents[0].id))
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

        if(userList[0] == userList[1]) {
            opponentUser = userList[0]
        }

        return opponentUser
    }

    private fun createChannel(userList: List<User>) {
        val newChannel = ChannelDto(
            userList,
            null,
            0
        )

        firebaseFirestore.collection("channel").add(newChannel).addOnSuccessListener {
            setChannelList(convertChannel(newChannel,it.id))
        }
    }

    private fun convertChannel(channelDto: ChannelDto, idx: String): Channel {
        return Channel(
            idx,
            channelDto.userList,
            channelDto.currentChat,
            channelDto.unreadChatCount,
            getOpponentUser(channelDto.userList)
        )
    }

}