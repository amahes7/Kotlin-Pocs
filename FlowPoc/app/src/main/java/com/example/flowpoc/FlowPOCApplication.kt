package com.example.flowpoc

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.flowpoc.di.component.DaggerAppComponent
class FlowPOCApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).baseUrl("https://reqres.in/").build()

    }

}