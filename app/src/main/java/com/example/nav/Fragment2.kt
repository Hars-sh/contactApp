package com.example.nav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Fragment2 : Fragment() {
    lateinit var myadapter: UserAdapter
    var itemm: ArrayList<user_data> = ArrayList()
    lateinit var linearLayoutManager: LinearLayoutManager
    private val BASE_URL: String ="https://dummyjson.com/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this.context)
        recyclerview.layoutManager=linearLayoutManager
        this.getMydata()
    }
    private fun getMydata() {
        val retrofitbuilder= Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val response =retrofitbuilder.getData()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        val items = response.body()?.users
                        if (items != null) {
                            for (i in 0 until items.count()) {
                                val id = items[i].id.toString()
                                val email="Email:".plus(items[i].email)
                                val name ="NAME:".plus(items[i].firstName.plus(items[i].lastName))
                                val number = "Mobile:".plus(items[i].phone)
                                val img=items[i].image
                                val model =
                                    user_data(
                                        name,number,id,email,img)
                                itemm.add(model)

                                myadapter = UserAdapter(requireContext(),itemm)
                                myadapter.notifyDataSetChanged()
                            }
                        }
                        recyclerview.adapter = myadapter
                    }
                    else {

                        Log.e("RETROFIT_ERROR", response.code().toString())

                    }}
            }}}