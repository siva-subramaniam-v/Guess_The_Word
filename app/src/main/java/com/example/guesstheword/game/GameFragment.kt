package com.example.guesstheword.game

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentGameBinding
import com.example.guesstheword.score.ScoreFragment

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_game, container, false
        )

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        Log.i("GameFragment", "ViewModelProvider called")

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this

        /*binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        binding.gotItButton.setOnClickListener {
            viewModel.onCorrect()
        }*/

        /*viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.currentScoreText.text = getString(R.string.current_score, newScore)
        }*/

        /*viewModel.word.observe(viewLifecycleOwner) { nextWord ->
            binding.wordDisplayText.text = nextWord
        }*/

        viewModel.eventGameFinish.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinishComplete()
            }

        }

        /*viewModel.currentTime.observe(viewLifecycleOwner) { currentTime ->
            binding.currentTimeText.text = DateUtils.formatElapsedTime(currentTime)
        }*/

        return binding.root
    }

    private fun gameFinished() {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToScoreFragment(
                viewModel.score.value ?: 0
            )
        )
    }
}