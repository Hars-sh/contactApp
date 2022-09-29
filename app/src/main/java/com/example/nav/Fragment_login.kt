package com.example.nav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class Fragment_login : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var tokenManager: LogInStateManager

    private lateinit var loginName: EditText
    private lateinit var loginEmail: EditText
    private lateinit var loginBtn: Button
    private lateinit var contextView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBtn = view.findViewById(R.id.login_btn)
        loginName = view.findViewById(R.id.login_fName)
        loginEmail = view.findViewById(R.id.login_email)
        contextView = view.findViewById(R.id.login_frag)
        val dao=ProfileDB.getDB(requireActivity().applicationContext).ProfileDAO()
    val repository=Repository(dao)
        sharedViewModel=ViewModelProvider(requireActivity(),SharedViewModelFactory(repository)).get(
            SharedViewModel::class.java
        )
        loginBtn.setOnClickListener {
            logexit(it)
        }
    }
    private fun logexit(view:View)
    {
        val fname=loginName.text.toString()
        val email=loginEmail.text.toString()
        Log.d("firstname",fname)
        sharedViewModel.userExist(email,fname).observe(viewLifecycleOwner)
        { it ->
            if(it==1)
            {
                sharedViewModel.getId(email).observe(viewLifecycleOwner){
                    id ->sharedViewModel.iid.value=id
                    tokenManager = LogInStateManager(requireContext().applicationContext)
                    tokenManager.saveToken(id)

                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.container, Fragment1())

                        activity?.supportFragmentManager?.popBackStack(Constants.ROOT_FRAG_TAG,0)
                        addToBackStack(Constants.ROOT_FRAG_TAG)

                        commit()
                    }
                }

            } else {

                Snackbar.make(contextView, "User Does Not Exists", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

    }

}