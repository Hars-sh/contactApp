package com.example.nav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav.databinding.Fragment3Binding
import com.google.android.material.snackbar.Snackbar

class Fragment3 : Fragment() {
private var _binding: Fragment3Binding?=null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var contextView:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= Fragment3Binding.inflate(inflater, container, false)
    return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextView = view.findViewById(R.id.fragment_3)

        val dao = ProfileDB.getDB(requireActivity().applicationContext).ProfileDAO()
        val repository = Repository(dao)
        sharedViewModel =
            ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )

        binding.signUpbtn.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {
        if(binding.signfName.text.length>4&&binding.signlName.text.length>4&&
            binding.signnumber.text.length==10&&
            binding.signemail.text.length>5&&binding.signcity.text.length>3)
        {
            sharedViewModel.mailExist(binding.signemail.text.toString()).observe(viewLifecycleOwner)
            {
                Log.d("Exist",it.toString())
                if(it==0)
                {
                    sharedViewModel.insertUser(
                        Profile(
                            0,
                            binding.signfName.text.toString(),
                            binding.signlName.text.toString(),
                            binding.signnumber.text.toString(),
                            binding.signemail.text.toString(),
                            binding.signcity.text.toString()
                        )
                    )
                   fragmenttransist()
                }
                else
                {
                    Log.d("harsh", "USER ALREADY Exists")
                    Snackbar.make(contextView, "User Already Exists", Snackbar.LENGTH_SHORT)
                        .show()
                }}}
        else
        {  Log.d("harsh", "USER ccALREADY Exists")
            Snackbar.make(contextView, "Use correct style", Snackbar.LENGTH_SHORT)
                .show() }
    }

    private fun fragmenttransist() {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container,Fragment4())
            addToBackStack(null)
            commit()
        }
    }
}
