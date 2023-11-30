package com.example.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.viewinterop.InteropView
import com.example.notification.databinding.FragmentDoctorAvailableServiceBinding
import com.example.notification.databinding.FragmentMyBackgroundServiceBinding


class DoctorAvailableServiceFragment : Fragment() {
    private lateinit var binding: FragmentDoctorAvailableServiceBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentDoctorAvailableServiceBinding.bind(inflater.inflate(R.layout.fragment_doctor_available_service,null))
        return binding.root
    }
}