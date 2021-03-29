package com.example.retrofitsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.model.Post
import com.example.myfirstapp.repository.Repository
import com.example.retrofitsample.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java);
        viewModel.getAllPosts()
        viewModel.myResponse2.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }

            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })


//        submitButtonUserId.setOnClickListener {
//            val user = number.text.toString()
//            viewModel.getCustomPosts(Integer.parseInt(user))
//            viewModel.myResponse2.observe(this, Observer { resp ->
//                if (resp.isSuccessful) {
//                    var result = "All Titles for user Id " + user + ": \n"
//                    resp.body()?.forEach {
//                        result = result + it.title + "\n"
//                    }
//                    resultTextView.text = result
//
//                } else {
//                    resultTextView.text = resp.errorBody().toString()
//                    Log.d("Response", resp.errorBody().toString())
//                }
//            })
//        }
//        submitButton.setOnClickListener {
//            val num = number.text.toString()
//            viewModel.getPostByNumber(Integer.parseInt(num))
//            viewModel.myResponse1.observe(this, Observer { resp ->
//                if (resp.isSuccessful) {
//                    Log.d("Title", resp.body()?.title!!)
//                    resultTextView.text = resp.body()?.title!!
//                } else {
//                    Log.d("Response", resp.errorBody().toString())
//                }
//            })
//        }


//        Attempt 2
//        viewModel.getPost();
//        viewModel.myResponse.observe(this, Observer { resp ->
//            if (resp.isSuccessful) {
//                Log.d("UserId", resp.body()?.myUser.toString())
//                Log.d("Id", resp.body()?.id.toString())
//                Log.d("Body", resp.body()?.body!!)
//                titleText.text = resp.body()?.title!!
//                Log.d("Title", resp.body()?.title!!)
//            } else {
//                Log.d("Response", resp.errorBody().toString())
//                titleText.text = resp.code().toString()
//            }
//        })


        //Attempt 1
//        viewModel.getPost();
//        viewModel.myResponse.observe(this, Observer { resp ->
//            Log.d("UserId", resp.myUser.toString())
//            Log.d("Id", resp.id.toString())
//            Log.d("Body", resp.body)
//            titleText.text = resp.title
//            Log.d("Title", resp.title)
//        })
//
    }


    private fun setupRecyclerView() {
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}