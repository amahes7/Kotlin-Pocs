package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }
    val args: ThirdFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val count = args.countArg1
        view.findViewById<Button>(R.id.button_third).setOnClickListener {
            val status = args.firstpage
            if(status) findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
            else{
                val action = ThirdFragmentDirections.actionThirdFragmentToSecondFragment(count)
                findNavController().navigate(action)
            }
        }
        val countText = getString(R.string.factorial_heading, count)
        view.findViewById<TextView>(R.id.textview_header3).text = countText
        var factorial: Long = 1
        for (i in 1..count) {
            // factorial = factorial * i;
            factorial *= i.toLong()
        }
        view.findViewById<TextView>(R.id.textview_fact).text = factorial.toString()
    }
}