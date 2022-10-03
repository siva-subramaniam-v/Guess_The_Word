package com.example.guesstheword.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel : ScoreViewModel
    private lateinit var viewModelFactory : ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentScoreBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_score, container, false)

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory)[ScoreViewModel::class.java]

        /*viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.scoreDisplayText.text = newScore.toString()
        }*/

        binding.scoreViewModel = viewModel

        /*binding.playAgainButton.setOnClickListener {
            viewModel.onPlayAgain()
        }*/

        viewModel.eventPlayAgain.observe(viewLifecycleOwner) { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
                viewModel.onPlayAgainComplete()
            }
        }

        return binding.root
    }
}