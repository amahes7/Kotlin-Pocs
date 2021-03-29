package com.example.coroutinesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var flow = flow<String>{
            for(i in 1..10){
                emit("Hello World")
                delay(1000)
            }
        }

        lifecycleScope.launch{
            flow.buffer().collect {
                println(it)
                delay(2000)
            }
        }
    }
}