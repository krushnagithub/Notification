package com.example.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notification.databinding.FragmentClinicWaitTimeServiceBinding


class ClinicWaitTimeServiceFragment : Fragment() {
    private lateinit var binding:FragmentClinicWaitTimeServiceBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentClinicWaitTimeServiceBinding.bind(inflater.inflate(R.layout.fragment_clinic_wait_time_service,null))
        return binding.root
    }
}