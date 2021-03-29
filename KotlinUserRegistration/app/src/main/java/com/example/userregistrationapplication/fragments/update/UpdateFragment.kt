package com.example.userregistrationapplication.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.userregistrationapplication.R
import com.example.userregistrationapplication.model.User
import com.example.userregistrationapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.add_fragment.view.*
import kotlinx.android.synthetic.main.update_fragment.*
import kotlinx.android.synthetic.main.update_fragment.view.*


class UpdateFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.update_fragment, container, false)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_firstname_et.setText(args.currentUser.firstName)
        view.update_lastname_et.setText(args.currentUser.lastName)
        view.update_age_et.setText(args.currentUser.age.toString())

        view.update_button.setOnClickListener {
            updateUser()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateUser() {
        val fName = update_firstname_et.text.toString()
        val lName = update_lastname_et.text.toString()
        val age = update_age_et.text
        if (inputCheck(fName, lName, age)) {
            val updatedUser =
                User(args.currentUser.id, fName, lName, Integer.parseInt(age.toString()))
            userViewModel.updateUser(updatedUser)
            Toast.makeText(
                requireContext(),
                "Successfully Updated User with id: " + args.currentUser.id,
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.list_fragment)
        } else {
            Toast.makeText(requireContext(), "Fail!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(fname: String, lName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu) {
            deleteUser()
            Toast.makeText(
                requireContext(),
                "Successfully removed : ${args.currentUser.id}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.list_fragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        userViewModel.deleteUser(args.currentUser)
    }
}