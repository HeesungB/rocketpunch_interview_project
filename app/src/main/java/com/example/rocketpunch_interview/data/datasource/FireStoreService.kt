package com.example.rocketpunch_interview.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.*
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class FireStoreService(
    private val firebaseFirestore: FirebaseFirestore,
    private val preferencesService: PreferencesService
): DataSource {
    private val _myUser = MutableLiveData<User>()
    private val _channelList = MutableLiveData<List<Channel>>(listOf())
    private val _searchedList = MutableLiveData<List<User>>(listOf())
    private val _selectedChannel = MutableLiveData<Channel>()
    private val _chatList = MutableLiveData<List<Chat>>(listOf())

    override val myUser: LiveData<User> get() = _myUser
    override val channelList: LiveData<List<Channel>> get() = _channelList
    override val searchedList: LiveData<List<User>> get() = _searchedList
    override val selectedChannel: LiveData<Channel> get() = _selectedChannel
    override val chatList: LiveData<List<Chat>> get() = _chatList

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
            val documentChannelList = arrayListOf<Channel>()

            for (document in documents) {
                val channelDto = document.toObject(ChannelDto::class.java)
                documentChannelList.add(convertChannel(channelDto, document.id))
            }

            _channelList.value = documentChannelList
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
            convertChat(channelDto.currentChat),
            channelDto.unreadChatCount,
            getOpponentUser(channelDto.userList)
        )
    }

    override fun sendChat(content: String) {
        val chatDto = ChatDto(
            _selectedChannel.value!!.idx,
            _myUser.value!!,
            _selectedChannel.value!!.opponentUser,
            content,
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(Calendar.getInstance().time),
            false
        )

        firebaseFirestore.collection("chat").add(chatDto).addOnSuccessListener {
            updateChannelCurrentChat(chatDto)
        }
    }

    private fun updateChannelCurrentChat(chatDto: ChatDto) {
        firebaseFirestore.collection("channel").document(chatDto.channelIdx).update("currentChat", chatDto)

    }

    override fun connectChatList() {
        firebaseFirestore.collection("chat").whereEqualTo("channelIdx", _selectedChannel.value!!.idx).orderBy("dateTime").addSnapshotListener { snapshots, e ->
            if (e != null) {
                Log.d("zz", "Listen failed.", e)
                return@addSnapshotListener
            }

            val tempChatList = ArrayList<Chat>(arrayListOf())
            for (doc in snapshots!!) {

                convertChat(doc.toObject(ChatDto::class.java))?.let { tempChatList.add(it) }
            }

            _chatList.value = tempChatList
        }
    }

    private fun convertChat(chatDto: ChatDto?): Chat? {
        chatDto?.let {
            val rowType = if(_myUser.value == chatDto.sender) {
                RowType.MYCHAT
            } else {
                RowType.OTHERCHAT
            }

            return Chat(
                chatDto.channelIdx,
                chatDto.sender!!,
                chatDto.receiver!!,
                chatDto.content,
                convertTime(chatDto.dateTime),
                chatDto.isRead,
                rowType
            )
        }

        return null
    }

    override fun initSelectedChannel() {
        _selectedChannel.value = null
    }

    override fun initChatList() {
        _chatList.value = listOf()
    }

    private fun convertTime(dateTime: String): String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val convertedDateTime = simpleDateFormat.parse(dateTime)!!

        val currentDateTime = Calendar.getInstance()
        currentDateTime.time = convertedDateTime

        lateinit var prefix: String
        var hours = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val minutes = currentDateTime.get(Calendar.MINUTE)

        if(hours < 12) {
            prefix = "오전"
        } else {
            prefix = "오후"
            hours -= 12
        }

        if (hours == 0) {
           hours = 12
        }

        return "$prefix $hours:${String.format("%02d", minutes)}"
    }

}