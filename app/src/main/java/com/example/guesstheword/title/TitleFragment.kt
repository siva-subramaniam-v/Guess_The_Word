package com.example.guesstheword.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_title, container, false
        )

        binding.playButton.setOnClickListener {
        findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
    }

        return binding.root
    }
}