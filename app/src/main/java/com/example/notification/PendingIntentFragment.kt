package com.example.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notification.databinding.FragmentPendingIntentBinding


class PendingIntentFragment : Fragment() {
    private lateinit var binding: FragmentPendingIntentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingIntentBinding.bind(
            inflater.inflate(
                R.layout.fragment_pending_intent,
                null
            )
        )
        return binding.root
    }
}
