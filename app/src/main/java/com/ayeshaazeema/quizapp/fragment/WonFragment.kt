package com.ayeshaazeema.quizapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ayeshaazeema.quizapp.R
import com.ayeshaazeema.quizapp.databinding.FragmentWonBinding

class WonFragment : Fragment() {

    private lateinit var wonBinding: FragmentWonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wonBinding = FragmentWonBinding.inflate(inflater, container, false)
        return wonBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wonBinding.btnContinue.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_wonFragment_to_welcomeFragment))
    }
}