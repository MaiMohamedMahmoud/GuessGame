/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        // use ViewModelProvider
        //to request ViewModel from the viewmodelProvider and it will create new instance for you in the first time
        // if any change in the configration happen the viewModelProvider will insure that your ViewModel will not delete and when the fragment create again it will link it to the previous created instance
        // After the Activity/Fragment finish and be deleted it will also delete the gameViewModel associated to it
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        gameViewModel.word.observe(viewLifecycleOwner, Observer { wordVal ->
            updateWordText(wordVal)
        })
        gameViewModel.score.observe(viewLifecycleOwner, Observer {score->
            updateScoreText(score.toString())

        })

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }

        return binding.root

    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(gameViewModel.score.value ?: 0)
        findNavController(this).navigate(action)
    }


    /** Methods for buttons presses **/

    private fun onSkip() {
        Log.i("GameFragment", "onSkip")
        gameViewModel.skip()
        if (gameViewModel.getListWord().isEmpty()) {
            gameFinished()
        } else {

        }
    }

    private fun onCorrect() {
        Log.i("GameFragment", "onCorrect")
        gameViewModel.correct()

        if (gameViewModel.getListWord().isEmpty()) {
            gameFinished()
        } else {
        }
    }

    /** Methods for updating the UI **/

    private fun updateWordText(word_val: String) {
        binding.wordText.text = word_val
    }

    private fun updateScoreText(score:String) {
        binding.scoreText.text = score
    }
}
