package com.example.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notification.databinding.FragmentMyBackgroundServiceBinding


class MyBackgroundServiceFragment : Fragment() {


    private lateinit var binding:FragmentMyBackgroundServiceBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyBackgroundServiceBinding.bind(inflater.inflate(R.layout.fragment_my_background_service,null))
        return binding.root
    }
}