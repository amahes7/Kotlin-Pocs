package com.example.userregistrationapplication.fragments.error

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
import kotlinx.android.synthetic.main.list_fragment.view.*
import kotlinx.android.synthetic.main.update_fragment.*
import kotlinx.android.synthetic.main.update_fragment.view.*


class ErrorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.error_fragment, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_errorFragment_to_add_fragment)
        }

        return view
    }

}