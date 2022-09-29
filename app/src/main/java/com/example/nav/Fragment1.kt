package com.example.nav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav.databinding.Fragment1Binding

class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!


    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = ProfileDB.getDB(requireActivity().applicationContext).ProfileDAO()
        val repository = Repository(dao)
        sharedViewModel =
            ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )

        getFromRoom()

        binding.editBtn.setOnClickListener {
            fragmentChange(Fragment4())
        }
        binding.changeAcc.setOnClickListener {
            fragmentChange(Introduction())
        }
    }


    private fun getFromRoom() {
        sharedViewModel.iid.observe(viewLifecycleOwner) { it ->

            Log.d("Id", it.toString())
            sharedViewModel.getUser(it).observe(viewLifecycleOwner) {
                binding.profile = it
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fragmentChange(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
