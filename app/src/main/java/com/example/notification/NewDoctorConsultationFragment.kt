package com.example.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notification.databinding.FragmentNewDoctorConsultationBinding


class NewDoctorConsultationFragment : Fragment() {
    private lateinit var binding:FragmentNewDoctorConsultationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNewDoctorConsultationBinding.bind(inflater.inflate(R.layout.fragment_new_doctor_consultation,null))
        return binding.root
    }
}