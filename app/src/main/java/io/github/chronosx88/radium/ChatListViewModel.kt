package io.github.chronosx88.radium

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.chronosx88.radium.models.Chat
import io.github.chronosx88.radium.models.Message
import kotlinx.coroutines.launch

class ChatListViewModel : ViewModel() {
    private val _chats = MutableLiveData<Array<Chat>>()
    val chats : LiveData<Array<Chat>> = _chats

    init {
        viewModelScope.launch {
            _chats.value = arrayOf(
                    Chat(
                            "Michael Jordan",
                            Message(
                                    "That's great!",
                                    true,
                                1616522699913,
                                    true
                            ),
                            "https://upload.wikimedia.org/wikipedia/commons/a/ae/Michael_Jordan_in_2014.jpg"
                    ),
                    Chat(
                            "Linus Torvalds",
                            Message(
                                    "software is like sex : it's better when it's free",
                                    false,
                                823522140000,
                                    true
                            ),
                            "https://upload.wikimedia.org/wikipedia/commons/0/01/LinuxCon_Europe_Linus_Torvalds_03_%28cropped%29.jpg"
                    )
            )
        }
    }
}