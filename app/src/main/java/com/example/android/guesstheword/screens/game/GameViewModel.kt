package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // The current word
    val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    //I want to encapsulate the value of scrore so the that it can't be access from outside of the class

    val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    val _onFinishNavigateEvent = MutableLiveData<Boolean>()
    val onFinishNavigateEvent: LiveData<Boolean>
        get() = _onFinishNavigateEvent


    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        _word.value = ""
        _score.value = 0
        _onFinishNavigateEvent.value = false
        Log.i("GameViewModel", "call instructor")

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "on cleard ")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _onFinishNavigateEvent.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun skip() {
        _score.value = score.value?.minus(1)
        nextWord()
    }

    fun correct() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

    fun finishedGame(){
        _onFinishNavigateEvent.value = false
    }

}