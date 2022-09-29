package com.example.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nav.databinding.FragmentIntroductionBinding


class Introduction : Fragment() {

    private var _binding: FragmentIntroductionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntroductionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ILogIn.setOnClickListener{
            fragmentChange(Fragment_login())
        }
        binding.ISignUp.setOnClickListener{
            fragmentChange(Fragment3())
        }
    }

    private fun fragmentChange(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, fragment )
            addToBackStack(null)
            commit()
        }
    }
}