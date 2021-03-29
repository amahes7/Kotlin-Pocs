package com.example.userregistrationapplication.fragments.list

import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.browser.customtabs.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userregistrationapplication.R
import com.example.userregistrationapplication.model.User
import com.example.userregistrationapplication.utils.RabbitCallback
import com.example.userregistrationapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.list_fragment.view.*

class ListFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    var builder = CustomTabsIntent.Builder()

    lateinit var serviceConnection: CustomTabsServiceConnection
    lateinit var client: CustomTabsClient
    lateinit var session: CustomTabsSession

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //custom chrome tab advanced config
        serviceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(
                name: ComponentName,
                mClient: CustomTabsClient
            ) {
                Log.d("Service", "Connected")
                client = mClient
                client.warmup(0L)
                val callback = RabbitCallback()
                session = mClient.newSession(callback)!!
                builder.setSession(session)

                val urlBundles = mutableListOf<Bundle>()
                val otherUrls: Bundle = bundleOf(
                    CustomTabsService.KEY_URL to Uri.parse("www.wikipedia.org")
                )
                urlBundles.add(otherUrls)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("Service", "Disconnected")
            }
        }
        CustomTabsClient.bindCustomTabsService(
            requireContext(),
            "com.android.chrome",
            serviceConnection
        )


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


// ALl Users using single API call by Flow
        userViewModel.getAllUsersByFlow.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

//Get Users by concurrent Flow Calls
//        userViewModel.concurrentResponse.observe(viewLifecycleOwner, Observer { user ->
//            adapter.setData(user)
//        })


// floating button action
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_list_fragment_to_add_fragment)
        }
//top user navigation
        view.tp_user_button.setOnClickListener {
            findNavController().navigate(R.id.action_list_fragment_to_topUserFragment)
        }
// concurrent calls from flow
        view.refresh_btn.setOnClickListener {
            inflater.inflate(R.layout.list_fragment, container, false)

        }

// basic custom tab implementation
        view.simple_tabs_button.setOnClickListener {
            var url = "https://www.google.com/"
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            var customTabsIntent: CustomTabsIntent = builder.build();
            customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
        }


// advanced custom tabs implementation
        view.advanced_tabs_button.setOnClickListener {
            //val url = "https://www.google.com"
            val url = "https://www.wikipedia.org"
            // Change tab toolbar color
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.black))

            // Toggle title in header toolbar
            builder.setShowTitle(false)

            // Add share option to more menu
            builder.addDefaultShareMenuItem()

            // Change close icon
            // Hide URL bar on scrolling
            builder.enableUrlBarHiding()

//            builder.setStartAnimations(requireContext(), R.anim.slide_in_right, R.anim.slide_out_left);
//            builder.setExitAnimations(requireContext(), R.anim.slide_in_left, R.anim.slide_out_right);

            val customTabsIntent: CustomTabsIntent = builder.build()
            customTabsIntent.launchUrl(requireActivity(), Uri.parse(url))
        }
        setHasOptionsMenu(true)



        return view;
    }

    private fun concurrentCalls() {

    }

    private fun insertData() {
        val user = User(0, "New User", "Last Name", Integer.parseInt(1.toString()))
        userViewModel.addUser(user)
        Toast.makeText(
            requireContext(),
            "Successfully Added ",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu) {
            deleteUser()
            Toast.makeText(
                requireContext(),
                "Successfully removed all Users",
                Toast.LENGTH_SHORT
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        userViewModel.deleteAllUsers()
    }

    override fun onStart() {
        super.onStart()
        CustomTabsClient.bindCustomTabsService(
            requireContext(),
            "com.android.chrome",
            serviceConnection
        )
    }

}


//TEST CODE
//Live data Implementation
//        userViewModel.getAllUsers.observe(viewLifecycleOwner, Observer { user ->
//            if (!user.isEmpty()) {
//                println("In not Null block" + user)
//            adapter.setData(user)
//            } else {
//                println("Null block" + user)
//                findNavController().navigate(R.id.action_list_fragment_to_errorFragment)
//            }
//        })

// Flow Implementation
//        userViewModel.getFlowUsers()
//        userViewModel.responseLiveData.observe(viewLifecycleOwner, Observer { user ->
//            if (!user.isEmpty()) {
//                println("In not Null block" + user)
//                adapter.setData(user)
//            } else {
//                println("Null block" + user)
//                findNavController().navigate(R.id.action_list_fragment_to_errorFragment)
//            }
//        })


//        LD & Flow Implementation combined
//        userViewModel.response.observe(viewLifecycleOwner, Observer { user ->
//            adapter.setData(user)
//        })

//        Flow Concurrent Calls Implementation using merge .
//        userViewModel.concurrentResponse.observe(viewLifecycleOwner, Observer { user ->
//            adapter.setData(user)
//        })

//        Flow Concurrent Calls Implementation using flatternmerge .
//        userViewModel.concurrentResponse.observe(viewLifecycleOwner, Observer { user ->
//            adapter.setData(user)
//        })