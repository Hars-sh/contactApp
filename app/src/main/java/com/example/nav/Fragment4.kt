package com.example.nav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav.databinding.Fragment4Binding
import com.google.android.material.snackbar.Snackbar

class Fragment4: Fragment() {

    private lateinit var contextView: View

    private var _binding: Fragment4Binding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextView = view.findViewById(R.id.editFrag)

        val dao = ProfileDB.getDB(requireActivity().applicationContext).ProfileDAO()
        val repository = Repository(dao)
        sharedViewModel =
            ViewModelProvider(requireActivity(), SharedViewModelFactory(repository)).get(
                SharedViewModel::class.java
            )
        getviaRoom()

        binding.saveBtn.setOnClickListener {
            changeProfile()
        }
    }

    private fun changeProfile() {

sharedViewModel.iid.observe(viewLifecycleOwner){
    it->

    if (binding.ufName.text.length > 2 &&
        binding.ulName.text.length > 2 &&
        binding.uPhone.text.length == 10 &&
        binding.uCity.text.length > 2
    ) {
        sharedViewModel.updateUser(
            Profile(
                it,
                binding.ufName.text.toString(),
                binding.ulName.text.toString(),
                binding.uPhone.text.toString(),
                binding.uEmail.text.toString(),
                binding.uCity.text.toString(),
            )
        )
        moveProfile()
    } else {
        Snackbar.make(contextView, "use correct formats", Snackbar.LENGTH_SHORT)
            .show()
    }
}
    }
        private fun moveProfile()  {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, Fragment1())
            activity?.supportFragmentManager?.popBackStack(Constants.ROOT_FRAG_TAG,0)
            addToBackStack(Constants.ROOT_FRAG_TAG)
            commit()
        }
    }

    private fun getviaRoom() {
        sharedViewModel.iid.observe(viewLifecycleOwner) { it ->

            Log.d("Id", it.toString())
            sharedViewModel.getUser(it).observe(viewLifecycleOwner) {
                binding.person = it
            }
        } }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}