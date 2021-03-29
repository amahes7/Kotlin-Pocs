package com.example.userregistrationapplication.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.userregistrationapplication.R
import com.example.userregistrationapplication.model.User
import com.example.userregistrationapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.add_fragment.view.*


class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_fragment, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.add_tu_button.setOnClickListener {
            insertData();
        }
        return view
    }

    private fun insertData() {
        val fName = firstname_et_tu.text.toString()
        val lName = lastname_et_tu.text.toString()
        val age = age_et_tu.text
        if (inputCheck(fName, lName, age)) {
            val user = User(0, fName, lName, Integer.parseInt(age.toString()))
            userViewModel.addUser(user)
            Toast.makeText(
                requireContext(),
                "Successfully Added " + fName + "!!",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.list_fragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fname: String, lName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lName) && age.isEmpty())
    }

}