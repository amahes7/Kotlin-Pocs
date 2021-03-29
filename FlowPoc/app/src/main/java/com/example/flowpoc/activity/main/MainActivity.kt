package com.example.flowpoc.activity.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowpoc.CoroutinesDispatcherProvider
import com.example.flowpoc.Factory.ViewModelFactory
import com.example.flowpoc.R
import com.example.flowpoc.activity.showToast
import com.example.flowpoc.model.ResponseObject
import com.example.flowpoc.recyclerview.UsersAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivity : DaggerAppCompatActivity() {
    val status = "200"

    private val mainActivityViewModel: MainActivityViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

//    @Inject
//    lateinit var usersDao: UsersDao

    @Inject
    lateinit var coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialUiState()
        showLoading()
//        mainActivityViewModel.repoCall()
//        observeViewState()
//        observeUsersInDatabase()
//        observeUsersFromAPI()
//        observeWrapperObject()
        observeWrapperObject2()
    }

//    private fun observeWrapperObject() {
//        println("=====================>observeWrapperObjectstart<===============================")
//        mainActivityViewModel.wrapper.observe(this, Observer { resp ->
//            if (resp.statusCode == "200")
//                showData(resp.data)
//            else
//                showError(resp.message)
//        })
//    }



    private fun observeWrapperObject2() {
        println("=====================>observeWrapperObjectstart<===============================")
        mainActivityViewModel.wrapper2.observe(this, Observer { resp ->
            if (resp.statusCode == "200")
                showData2(resp.data)
            else
                showError(resp.message)
        })
    }

//    private fun observeViewState() {
//        mainActivityViewModel.state.observe(this, Observer { state ->
//            when (state) {
//                is ResponseState.ShowLoading -> {
//                    initialUiState()
//                    showLoading()
//                }
//                is ResponseState.ShowError -> {
//                    showError(state.error)
//                }
//                is ResponseState.ErrorState -> {
//                    showErrorMessageState(state.message)
//                }
//            }
//        })
//    }


//    private fun observeUsersInDatabase() {
//        CoroutineScope(coroutinesDispatcherProvider.main).launch {
//            usersDao.getAllUsers()
//                .collect { users ->
//                    showData(users)
//                }
//        }
//    }

//    private fun observeUsersFromAPI() {
//        mainActivityViewModel.users.observe(this, Observer { userList ->
//            if (userList.isEmpty()) {
//                initialUiState()
//                showLoading()
//            } else {
//                showData(userList)
//            }
//        })
//    }


    private fun initialUiState() {
        progress_circular.visibility = View.GONE
        recyclerview.visibility = View.GONE
        adapter = UsersAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
    }

    private fun showLoading() {
        progress_circular.visibility = View.VISIBLE
    }

//    private fun showData(data: List<ResponseObject.Users>) {
//        removeProgressDialog()
//        recyclerview.visibility = View.VISIBLE
//        adapter.submitList(data)
//    }


    private fun showData2(data: List<ResponseObject.Users2>) {
        removeProgressDialog()
        recyclerview.visibility = View.VISIBLE
        adapter.submitList(data)
    }


//    private fun showError(error: Throwable) {
//        removeProgressDialog()
//        println("------------------------ERROR----------------------------------------------------")
//        showToast(error.message, Toast.LENGTH_LONG)
//    }

    private fun removeProgressDialog() {
        progress_circular.visibility = View.GONE

    }

    private fun showError(message: String) {
        removeProgressDialog()
        println("------------------------LOGICAL ERROR----------------------------------------------------")
        showToast(message, Toast.LENGTH_LONG)

    }
}
