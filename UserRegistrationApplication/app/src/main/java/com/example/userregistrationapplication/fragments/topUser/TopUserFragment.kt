package com.example.userregistrationapplication.fragments.topUser

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.userregistrationapplication.R
import com.example.userregistrationapplication.model.User
import com.example.userregistrationapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.top_user_fragment.view.*
import kotlinx.android.synthetic.main.top_user_fragment.view.add_tu_button


class TopUserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.top_user_fragment, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



// first load screen
        initFieldsUsingSequentialFlows(userViewModel, view)

        //navigation back to main screen
        view.back_btn.setOnClickListener {
            findNavController().navigate(R.id.action_topUserFragment_to_list_fragment)
        }
//         event to add the new user
        view.add_tu_button.setOnClickListener {
            insertData();
        }

//        initFields(userViewModel, view)
//        initFieldsUsingFlow(userViewModel, view)
        return view
    }

    private fun initFieldsUsingSequentialFlows(userViewModel: UserViewModel, view: View?) {
        userViewModel.getTopUserWithSequentialFlows.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                view?.id_tv?.setText(user.id.toString())
                view?.fname_tv?.setText(user.firstName)
                view?.lname_tv?.setText(user.lastName)
                view?.age_tv?.setText(user.age.toString())
            }
        })

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
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fname: String, lName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lName) && age.isEmpty())
    }

}


// Trial Codes
//private fun initFieldsUsingFlow(userViewModel: UserViewModel, view: View?) {
//    userViewModel.getFlowTopUser.observe(viewLifecycleOwner, Observer { user ->
//        if (user != null) {
//            view?.id_tv?.setText(user.id.toString())
//            view?.fname_tv?.setText(user.firstName)
//            view?.lname_tv?.setText(user.lastName)
//            view?.age_tv?.setText(user.age.toString())
//        }
//    })
//
//}




//private fun initFields(userViewModel: UserViewModel, view: View?) {
//    userViewModel.getTopUserWithSequentialFlows.observe(viewLifecycleOwner, Observer { user ->
//        if (user != null) {
//            view?.id_tv?.setText(user.id.toString())
//            view?.fname_tv?.setText(user.firstName)
//            view?.lname_tv?.setText(user.lastName)
//            view?.age_tv?.setText(user.age.toString())
//        }
//    })
//
//}



