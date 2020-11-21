package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // The current word
    var word = MutableLiveData<String>()

    // The current score
    var score = MutableLiveData<Int>()

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()
        word.value=""
        score.value=0
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
        if (!wordList.isEmpty()) {
            word.value = wordList.removeAt(0)
        }
//        updateWord()
//        updateScore()
    }

//    fun getScore(): String {
//        return score.toString()
//    }

//    fun getResult(): String {
//        return word
//    }

    fun getListWord(): MutableList<String> {
        return wordList
    }

    fun skip() {
        score.value= score.value?.minus(1)
        nextWord()
    }

    fun correct() {
        score.value= score.value?.plus(1)
        nextWord()
    }


}